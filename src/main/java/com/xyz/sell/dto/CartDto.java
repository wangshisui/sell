package com.xyz.sell.dto;

import lombok.Data;

import javax.persistence.Entity;

/**购物车
 * @Author:zhangyx
 * @Date:Created in 20:402018/10/29
 * @Modified By:
 */
@Data
public class CartDto {

    private String productId;
    private Integer productQuantity;

    public CartDto(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }


}
