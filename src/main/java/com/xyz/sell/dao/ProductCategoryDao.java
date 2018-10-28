package com.xyz.sell.dao;

import com.xyz.sell.dataobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author:zhangyx
 * @Date:Created in 13:332018/10/28
 * @Modified By:
 */
public interface ProductCategoryDao extends JpaRepository<ProductCategory,Integer> {

    //查类目
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

}
