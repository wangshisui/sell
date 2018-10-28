package com.xyz.sell.service;

import com.xyz.sell.dataobject.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**商品
 * @Author:zhangyx
 * @Date:Created in 16:122018/10/28
 * @Modified By:
 */
public interface ProductInfoService {

    ProductInfo findOne(String productId);

    /**
     * 查询所有在架的商品
     */
    List<ProductInfo> findUpAll();
//管理端
    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

    //加库存

    //减库存



}
