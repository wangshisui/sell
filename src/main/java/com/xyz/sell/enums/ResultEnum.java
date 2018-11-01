package com.xyz.sell.enums;

import lombok.Data;
import lombok.Getter;

/**
 * @Author:zhangyx
 * @Date:Created in 20:122018/10/29
 * @Modified By:
 */
@Getter
public enum ResultEnum {
    PRIDUCT_NOT_EXITS(10,"商品不存在"),
  PRODUCT_NOT_ERROR(20,"库存不足"),
    ;

    private Integer code;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private String message;
}
