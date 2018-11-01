package com.xyz.sell.service.impl;

import com.xyz.sell.dataobject.OrderDetail;
import com.xyz.sell.dto.CartDto;
import com.xyz.sell.dto.OrderDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
    private final String BUYER_OPENID="110110";
    @Test
    public void create() throws Exception {

        OrderDTO orderDTO=new OrderDTO();
        orderDTO.setBuyerName("小张");
        orderDTO.setBuyerAddress("青岛");
        orderDTO.setBuyerPhone("2323121212");
        orderDTO.setOrderStatus(1);
        orderDTO.setPayStatus(1);
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
    }

    @Test
    public void findList() throws Exception {
    }

    @Test
    public void cancel() throws Exception {
    }

    @Test
    public void finish() throws Exception {
    }

    @Test
    public void paid() throws Exception {
    }

}