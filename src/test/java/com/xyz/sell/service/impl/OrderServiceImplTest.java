package com.xyz.sell.service.impl;

import com.xyz.sell.dataobject.OrderDetail;
import com.xyz.sell.dto.CartDto;
import com.xyz.sell.dto.OrderDTO;
import com.xyz.sell.enums.OrderStatusEnum;
import com.xyz.sell.enums.PayStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author:zhangyx
 * @Date:Created in 20:562018/10/29
 * @Modified By:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImplTest {

    @Autowired
    private OrderServiceImpl orderService;
    private final String BUYER_OPENID="7777";
    @Test
    public void create() throws Exception {

        OrderDTO orderDTO=new OrderDTO();
        orderDTO.setBuyerName("小王");
        orderDTO.setBuyerAddress("杭州");
        orderDTO.setBuyerPhone("17865645271");
        orderDTO.setOrderStatus(1);
        orderDTO.setBuyerOpenid(BUYER_OPENID);

        List<OrderDetail> cartDtoList=new ArrayList<>();
        OrderDetail o1=new OrderDetail();
         o1.setProductId("1111");
         o1.setProductQuantity(2);
         cartDtoList.add(o1);

        orderDTO.setOrderDetailList(cartDtoList);
        OrderDTO result=orderService.create(orderDTO);
        System.out.println(result+"创建订单-----------------------------------------------------");

    }

    @Test
    public void findOne() throws Exception {
        String orderID="1541219225811973362";
    OrderDTO result=orderService.findOne(orderID);
        System.out.println(result+"-------------------------------------");
        Assert.assertNotEquals(orderID,result.getOrderId());

    }

    @Test
    public void findList() throws Exception {
        PageRequest pageRequest=new PageRequest(0,2);

       Page<OrderDTO> orderDTOPage=orderService.findList(BUYER_OPENID,pageRequest);
       Assert.assertNotEquals(0,orderDTOPage.getTotalElements());
    }

    @Test
    public void cancel() throws Exception {
        String orderID="1541217531910858711";
        OrderDTO orderDTO=orderService.findOne(orderID);
        OrderDTO resu=orderService.cancel(orderDTO);
        System.out.println(OrderStatusEnum.CANCEL.getCode()+"----");
        System.out.println(resu.getOrderStatus()+"---");
        Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(),resu.getOrderStatus());
    }

    @Test
    public void finish() throws Exception {
        String orderID="1541217531910858711";
        OrderDTO orderDTO=orderService.findOne(orderID);
        OrderDTO resu=orderService.finish(orderDTO);
        System.out.println(OrderStatusEnum.FINISH.getCode()+"----");
        System.out.println(resu.getOrderStatus()+"---");
        Assert.assertEquals(OrderStatusEnum.FINISH.getCode(),resu.getOrderStatus());
    }

    @Test
    public void paid() throws Exception {
        String orderID="1541217531910858711";
        OrderDTO orderDTO=orderService.findOne(orderID);
        OrderDTO resu=orderService.paid(orderDTO);
        System.out.println(PayStatusEnum.SUCCESS.getCode()+"----");
        System.out.println(resu.getPayStatus()+"---");
       Assert.assertEquals(PayStatusEnum.SUCCESS.getCode(),resu.getPayStatus());
    }
    @Test
    public void list() throws Exception{
        PageRequest pageRequest=new PageRequest(0,2);
        Page<OrderDTO> orderDTOPage=orderService.findList(pageRequest);
    }
}