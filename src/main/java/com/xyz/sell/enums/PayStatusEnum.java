package com.xyz.sell.enums;

import lombok.Getter;

/**
 * @Author:zhangyx
 * @Date:Created in 21:462018/10/28
 * @Modified By:
 */
@Getter
public enum PayStatusEnum {
   WAIT(0,"未支付"),
    SUCCESS(1,"支付成功"),
    ;
    private Integer code;
    private String message;
    PayStatusEnum(Integer code,String message){
        this.code=code;
        this.message=message;
    }
}
