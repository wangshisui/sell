package com.xyz.sell.service.impl;

import com.xyz.sell.dao.ProductInfoDao;
import com.xyz.sell.dataobject.ProductInfo;
import com.xyz.sell.dto.CartDto;
import com.xyz.sell.enums.ProductStatusEnum;
import com.xyz.sell.enums.ResultEnum;
import com.xyz.sell.exception.SellException;
import com.xyz.sell.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:zhangyx
 * @Date:Created in 16:172018/10/28
 * @Modified By:
 */
@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    private ProductInfoDao productInfoDao;
    @Override
    public ProductInfo findOne(String productId) {
        return productInfoDao.findOne(productId);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoDao.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return productInfoDao.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return productInfoDao.save(productInfo);
    }

    @Override
    public void increaseStock(List<CartDto> cartDtoList) {

    }

    @Override
    public void decreaseStock(List<CartDto> cartDtoList) {

          for(CartDto cartDto:cartDtoList){
            ProductInfo productInfo=  productInfoDao.findOne(cartDto.getProductId());
            if(productInfo==null){
                throw new SellException(ResultEnum.PRIDUCT_NOT_EXITS);
            }
            Integer result=productInfo.getProductStock()-cartDto.getProductQuantity();
              if(result<0){
                  throw new SellException(ResultEnum.PRODUCT_NOT_ERROR);
              }
              productInfo.setProductStock(result);
          }
    }
}
