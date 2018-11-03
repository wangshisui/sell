package com.xyz.sell.service;

import com.xyz.sell.dto.OrderDTO;

/**
 * @Author:zhangyx
 * @Date:Created in 15:572018/11/3
 * @Modified By:
 */
public interface BuyerService {

    //查询一个订单
     OrderDTO findOrderOne(String openid,String orderId);
    //取消订单
    OrderDTO cancelOrder(String openid,String orderId);
}
