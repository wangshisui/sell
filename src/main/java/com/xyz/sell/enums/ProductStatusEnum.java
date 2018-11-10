package com.xyz.sell.enums;

import com.xyz.sell.utils.EnumUtil;
import lombok.Getter;

/**
 * @Author:zhangyx
 * @Date:Created in 16:202018/10/28
 * @Modified By:
 */
@Getter
public enum ProductStatusEnum implements CodeEnum {
    UP(0,"在架"),
    DOWN(1,"下架");

    private Integer code;

    private String message;

    ProductStatusEnum(Integer code,String message) {
        this.code = code;
        this.message=message;
    }
}
