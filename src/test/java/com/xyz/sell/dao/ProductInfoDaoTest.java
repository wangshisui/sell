package com.xyz.sell.dao;

import com.xyz.sell.dataobject.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author:zhangyx
 * @Date:Created in 16:002018/10/28
 * @Modified By:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoDaoTest {
    @Autowired
    private ProductInfoDao productInfoDao;

    @Test
    public void saveTest(){
        ProductInfo productInfo=new ProductInfo();
        productInfo.setProductId("1111");
        productInfo.setProductName("鸡翅");
        productInfo.setProductPrice(new BigDecimal(11.1));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("真他妈好吃");
        productInfo.setProductStatus(0);
        productInfo.setProductIcon("XXXXXXX");
        productInfo.setCategoryType(2);
        ProductInfo result=productInfoDao.save(productInfo);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByProductStatus() throws Exception {
        List<ProductInfo> list=productInfoDao.findByProductStatus(0);
        Assert.assertNotEquals(0,list.size());
    }


}