package com.xyz.sell.service.impl;

import com.xyz.sell.dao.ProductInfoDao;
import com.xyz.sell.dataobject.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @Author:zhangyx
 * @Date:Created in 16:272018/10/28
 * @Modified By:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoServiceImplTest {
    @Autowired
    private ProductInfoServiceImpl productInfoService;
    @Test
    public void findOne() throws Exception {
        ProductInfo result=productInfoService.findOne("1111");
        System.out.println(result.toString());
    }

    @Test
    public void findUpAll() throws Exception {
    }

    @Test
    public void findAll() throws Exception {
        PageRequest pageRequest=new PageRequest(0,2);
        Page<ProductInfo> productInfoPage =productInfoService.findAll(pageRequest);

    }

    @Test
    public void save() throws Exception {
    }

}