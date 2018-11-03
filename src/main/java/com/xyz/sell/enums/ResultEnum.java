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
    ORDER_NOT_EXIST(30,"订单不存在"),
    ORDERDETAIL_NOT_EXIST(40,"订单详情不存在"),
    ORDER_STATUS_ERROR(50,"订单状态不正确！！！！"),
    ORDER_CANCEL_FAIL(60,"订单取消失败！！！"),
    ORDER_NOT_PRODUCT(70,"取消订单时，订单中无商品"),
    ORDER_FINISH_FAIL(80,"订单完结失败"),
    PAY_STATUS_FAIL(90,"支付状态不正确！！"),
    ORDER_PAY_STSTUSUPDATE_FAIL(100,"支付状态更新失败"),
  FROM_FALSE(101,"表单校验错误"),
  ITEMS_NOT_JSON(102,"表单中的items不是json格式"),
  ITEMS_NOT_EMPTY(103,"购物车不能为空"),
  OPENID_NOT_ONLY(104,"查询的订单id不一致")
    ;

    private Integer code;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private String message;
}
