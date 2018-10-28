package com.xyz.sell.service;

import com.xyz.sell.dataobject.ProductCategory;

import java.util.List;
import java.util.Locale;

/**类目
 * @Author:zhangyx
 * @Date:Created in 15:392018/10/28
 * @Modified By:
 */
public interface CategoryService {

    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory save(ProductCategory productCategory);
}
