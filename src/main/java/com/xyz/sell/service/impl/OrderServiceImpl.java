package com.xyz.sell.service.impl;

import com.xyz.sell.comverter.OrderMaster2OrderDto;
import com.xyz.sell.dao.OrderDetailDao;
import com.xyz.sell.dao.OrderMasterDao;
import com.xyz.sell.dataobject.OrderDetail;
import com.xyz.sell.dataobject.OrderMaster;
import com.xyz.sell.dataobject.ProductInfo;
import com.xyz.sell.dto.CartDto;
import com.xyz.sell.dto.OrderDTO;
import com.xyz.sell.enums.OrderStatusEnum;
import com.xyz.sell.enums.PayStatusEnum;
import com.xyz.sell.enums.ResultEnum;
import com.xyz.sell.exception.SellException;
import com.xyz.sell.service.OrderService;
import com.xyz.sell.service.ProductInfoService;
import com.xyz.sell.utils.KeyUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author:zhangyx
 * @Date:Created in 20:072018/10/29
 * @Modified By:
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private OrderDetailDao orderDetailDao;

    @Autowired
    private OrderMasterDao orderMasterDao;
    @Autowired
    private OrderService orderService;
    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {

        BigDecimal orderAmount=new BigDecimal(BigInteger.ZERO);
        List<CartDto> cartDtoList=new ArrayList();
        //订单创建的时候生成id
        String orderId= KeyUtils.getRandomKey();
        //1.查询商品的数量
            for(OrderDetail orderDetail:orderDTO.getOrderDetailList()){
                ProductInfo productInfo=productInfoService.findOne(orderDetail.getProductId());
                if(productInfo==null){
                    throw  new SellException(ResultEnum.PRIDUCT_NOT_EXITS);
                }
                //2.计算订单的总价
                  orderAmount=productInfo.getProductPrice().multiply
                          (new BigDecimal(orderDetail.getProductQuantity()))
                          .add(orderAmount);

                //获取订单id
                //获取订单详情id
                BeanUtils.copyProperties(productInfo,orderDetail);
                orderDetail.setOrderId(orderId);
                orderDetail.setDetailId(KeyUtils.getRandomKey());
                 //将商品的信息，复制到订单详情中

                orderDetailDao.save(orderDetail);
                CartDto cartDto=new CartDto(orderDetail.getProductId(),
                                         orderDetail.getProductQuantity());
                cartDtoList.add(cartDto);
            }
        //3.写入订单数据库
        OrderMaster orderMaster=new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO,orderMaster);

        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        //写入数据库
        orderMasterDao.save(orderMaster);

        //4.扣库存
        productInfoService.decreaseStock(cartDtoList);
        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {
       OrderMaster orderMaster=orderMasterDao.findOne(orderId);
       if(orderMaster==null){
           //查询失败，订单异常
           throw  new SellException(ResultEnum.ORDER_NOT_EXIST);
       }


       List<OrderDetail> orderDetailList=orderDetailDao.findByOrderId(orderId);
       if(CollectionUtils.isEmpty(orderDetailList)){
           throw  new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
       }
       OrderDTO orderDTO=new OrderDTO();
       BeanUtils.copyProperties(orderMaster,orderDTO);
       orderDTO.setOrderDetailList(orderDetailList);


        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        //调用dao查出一个page对象
        Page<OrderMaster> orderMasterPage=orderMasterDao.findByBuyerOpenid(buyerOpenid,pageable);
        //使用一个转换器将OrderMaster 转成orderDto
        List<OrderDTO> orderDTOList=OrderMaster2OrderDto.convert(orderMasterPage.getContent());
        Page<OrderDTO> orderDTOPage=new PageImpl<OrderDTO>(orderDTOList,pageable,orderMasterPage.getTotalPages());

        return orderDTOPage;
    }

    @Override
    @Transactional
    public OrderDTO cancel(OrderDTO orderDTO) {
       OrderMaster orderMaster=new OrderMaster();

       //判断订单状态
        if(!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            System.out.println("订单状态不对");
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改订单状态
        orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        BeanUtils.copyProperties(orderDTO,orderMaster);
        //保存一下
        OrderMaster updateRes=orderMasterDao.save(orderMaster);
        if (updateRes==null){
            System.out.println("订单取消失败");
            throw new SellException(ResultEnum.ORDER_CANCEL_FAIL);
        }
        //返回库存
        if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            System.out.println("取消订单时，订单中没有商品");
            throw  new SellException(ResultEnum.ORDER_NOT_PRODUCT);
        }

        List<CartDto> cartDTOList = orderDTO.getOrderDetailList().stream()
                .map(e -> new CartDto(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());

        productInfoService.increaseStock(cartDTOList);

        //如果已经付款则需要退款
        if(orderDTO.getPayStatus().equals(PayStatusEnum.SUCCESS)){
            System.out.println("退款");
        }
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO finish(OrderDTO orderDTO) {

        if(!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            System.out.println("订单状态不正确+___________-----------");
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改订单状态
        orderDTO.setOrderStatus(OrderStatusEnum.FINISH.getCode());
        OrderMaster orderMaster=new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
       OrderMaster updateResu= orderMasterDao.save(orderMaster);
       if(updateResu==null){
           System.out.println("更新失败");
           throw new SellException(ResultEnum.ORDER_FINISH_FAIL);
       }

        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO paid(OrderDTO orderDTO) {

        //判断订单状态
        if(!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            System.out.println("订单状态不正确+___________-----------");
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //判断支付状态
        if(!orderDTO.getPayStatus().equals(PayStatusEnum.WAIT.getCode())){
            System.out.println("支付状态有问题！！");
            throw new SellException(ResultEnum.PAY_STATUS_FAIL);
        }

        //修改支付状态
        orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        OrderMaster orderMaster=new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        OrderMaster updateResu= orderMasterDao.save(orderMaster);
        if(updateResu==null){
            System.out.println("支付状态更新更新失败");
            throw new SellException(ResultEnum.ORDER_PAY_STSTUSUPDATE_FAIL);
        }

        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(Pageable pageable) {
        Page<OrderMaster> orderMasterOage=orderMasterDao.findAll(pageable);
        //使用一个转换器将OrderMaster 转成orderDto
        List<OrderDTO> orderDTOList=OrderMaster2OrderDto.convert(orderMasterOage.getContent());
        Page<OrderDTO> orderDTOPage=new PageImpl<OrderDTO>(orderDTOList,pageable,orderMasterOage.getTotalPages());



        return orderDTOPage;
    }
}
