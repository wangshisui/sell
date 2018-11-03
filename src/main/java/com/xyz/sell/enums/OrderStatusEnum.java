package com.xyz.sell.enums;

import lombok.Getter;

/**
 * @Author:zhangyx
 * @Date:Created in 21:412018/10/28
 * @Modified By:
 */
@Getter
public enum OrderStatusEnum {

    NEW(0,"新订单"),
    FINISH(1,"已完结"),
    CANCEL(2,"已取消"),

    ;

    private Integer code;
    private String message;
    OrderStatusEnum(Integer code,String message){
        this.code=code;
        this.message=message;
    }
}
