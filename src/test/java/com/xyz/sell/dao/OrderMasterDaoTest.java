package com.xyz.sell.dao;

import com.xyz.sell.comverter.OrderMaster2OrderDto;
import com.xyz.sell.dataobject.OrderMaster;
import com.xyz.sell.dto.OrderDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

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
        orderMaster.setOrderId("555");
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
     @Test
    public void findList( ) {
        PageRequest pageable=new PageRequest(0,3);
        Page<OrderMaster> orderMasterOage=orderMasterDao.findAll(pageable);
        //使用一个转换器将OrderMaster 转成orderDto
        List<OrderDTO> orderDTOList= OrderMaster2OrderDto.convert(orderMasterOage.getContent());
        Page<OrderDTO> orderDTOPage=new PageImpl<OrderDTO>(orderDTOList,pageable,orderMasterOage.getTotalPages());


    }

}