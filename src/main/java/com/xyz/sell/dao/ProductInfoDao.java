package com.xyz.sell.dao;

import com.xyz.sell.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**商品名称
 * @Author:zhangyx
 * @Date:Created in 15:582018/10/28
 * @Modified By:
 */

public interface ProductInfoDao extends JpaRepository<ProductInfo,String> {

    List<ProductInfo> findByProductStatus(Integer productStatus);
}
