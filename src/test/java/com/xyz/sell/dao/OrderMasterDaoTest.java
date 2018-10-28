package com.xyz.sell.dao;

import com.xyz.sell.dataobject.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @Author:zhangyx
 * @Date:Created in 22:022018/10/28
 * @Modified By:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterDaoTest {
    @Autowired
    private OrderMasterDao orderMasterDao;

    @Test
    public void saveTest(){
        OrderMaster orderMaster=new OrderMaster();
        orderMaster.setOrderId("4444");
        orderMaster.setBuyerOpenid("123432");
        orderMaster.setBuyerName("刘小心");
        orderMaster.setBuyerPhone("17353424292");
        orderMaster.setBuyerAddress("杭州");
        orderMaster.setOrderAmount(new BigDecimal(245.1));
        OrderMaster result=orderMasterDao.save(orderMaster);
        Assert.assertNotNull(result);
    }
    @Test
    public void findByBuyerOpenId() throws Exception {
    String OPENID="123";
        PageRequest pageRequest=new PageRequest(0,2);
        Page<OrderMaster> result=orderMasterDao.findByBuyerOpenid(OPENID,pageRequest);
    }

}