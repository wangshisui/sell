package com.xyz.sell.service.impl;

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
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

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
                          (new BigDecimal(orderDetail.getProductQuantity())).add(orderAmount);

                //获取订单id
                //获取订单详情id
                BeanUtils.copyProperties(productInfo,orderDetail);
                orderDetail.setOrderId(orderId);
                orderDetail.setDetailId(orderId);
                 //将商品的信息，复制到订单详情中

                orderDetailDao.save(orderDetail);
                CartDto cartDto=new CartDto(orderDetail.getProductId(),orderDetail.getProductQuantity());
                cartDtoList.add(cartDto);
            }
        //3.写入订单数据库
        OrderMaster orderMaster=new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setOrderStatus(PayStatusEnum.WAIT.getCode());


        orderMasterDao.save(orderMaster);

        //4.扣库存
        productInfoService.decreaseStock(cartDtoList);
        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        return null;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        return null;
    }

    @Override
    public OrderDTO cancel(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO paid(OrderDTO orderDTO) {
        return null;
    }
}
