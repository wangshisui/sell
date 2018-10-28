package com.xyz.sell.service.impl;

import com.xyz.sell.dao.ProductCategoryDao;
import com.xyz.sell.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @Author:zhangyx
 * @Date:Created in 15:482018/10/28
 * @Modified By:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest {

    @Autowired
    private ProductCategoryDao productCategoryDao;
    @Test
    public void findOne() throws Exception {
        ProductCategory result=productCategoryDao.findOne(new Integer(1));
        Assert.assertEquals(new Integer(1),result.getCategoryId());
    }

    @Test
    public void findAll() throws Exception {
    }

    @Test
    public void findByCategoryTypeIn() throws Exception {
    }

    @Test
    public void save() throws Exception {
    }

}