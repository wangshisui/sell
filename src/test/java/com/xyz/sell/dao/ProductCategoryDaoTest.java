package com.xyz.sell.dao;

import com.xyz.sell.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;


/**
 * @Author:zhangyx
 * @Date:Created in 13:362018/10/28
 * @Modified By:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryDaoTest {

    @Autowired
    private  ProductCategoryDao productCategoryDao;
    @Test
    public void findOneTest(){
        ProductCategory productCategory=productCategoryDao.findOne(1);
        System.out.println(productCategory.toString());
    }

    @Test
    @Transactional
    public void saveTest(){
        ProductCategory productCategory=new ProductCategory();
      productCategory.setCategoryId(3);
      productCategory.setCategoryName("热销榜");
      productCategory.setCategoryType(4);
      ProductCategory result=productCategoryDao.save(productCategory);
        Assert.assertNotNull(result);
    }

    @Test
    public  void findByCategoryTypeIn(){
        List<Integer> list= Arrays.asList(1,2,3);
        List<ProductCategory> result=productCategoryDao.findByCategoryTypeIn(list);
        Assert.assertNotEquals(0,result.size());
    }
}