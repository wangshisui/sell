package com.xyz.sell.dao;

import com.xyz.sell.dataobject.OrderDetail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @Author:zhangyx
 * @Date:Created in 22:592018/10/28
 * @Modified By:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailDaoTest {

    @Autowired
    private OrderDetailDao orderDetailDao;

    @Test
    public void saveTest(){
        OrderDetail orderDetail=new OrderDetail();
        orderDetail.setDetailId("123");
        orderDetail.setOrderId("2222");
        orderDetail.setProductIcon("xxxxxxxx");
        orderDetail.setProductName("烤鸭");
        orderDetail.setProductId("1111");
        orderDetail.setProductPrice(new BigDecimal(33.3));
        orderDetail.setProductQuantity(11111);
        orderDetailDao.save(orderDetail);
    }
    @Test
    public void findByOrderId() throws Exception {


    }

}