package com.xyz.sell.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 * @Author:zhangyx
 * @Date:Created in 14:392018/11/3
 * @Modified By:
 */
@Data
public class OrderForm {
    //买家姓名
    @NotEmpty(message = "姓名必填")
    private String name;

    //买家手机号
    @NotEmpty(message = "手机号必填")
    private String phone;

    //买家地址
    @NotEmpty(message = "地址必填")
    private String address;

    //opeID
    @NotNull(message = "openid不能为空")
    private String openid;

    //message
    @NotEmpty(message = "message不为空")
    private String message;

    //购物车
    @NotEmpty(message = "不能为空")
    private String items;

}
