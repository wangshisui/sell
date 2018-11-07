package com.xyz.sell.service.impl;

import com.xyz.sell.dto.OrderDTO;
import com.xyz.sell.enums.ResultEnum;
import com.xyz.sell.exception.SellException;
import com.xyz.sell.service.BuyerService;
import com.xyz.sell.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author:zhangyx
 * @Date:Created in 15:592018/11/3
 * @Modified By:
 */
@Service
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private OrderService orderService;
    @Override
    public OrderDTO findOrderOne(String openid, String orderId) {
        OrderDTO orderDTO=orderService.findOne(orderId);
        if(orderDTO==null){
            return null;
        }
        if(!orderDTO.getBuyerOpenid().equalsIgnoreCase(openid)){
            System.out.println("查询的订单不一致");
            throw new SellException(ResultEnum.OPENID_NOT_ONLY);
        }
        return orderDTO;
    }

    @Override
    public OrderDTO cancelOrder(String openid, String orderId) {

        OrderDTO orderDTO=orderService.findOne(orderId);
        if(orderDTO==null){
            System.out.println("取消订单时在这个不能为空");
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        if(!orderDTO.getBuyerOpenid().equalsIgnoreCase(openid)){
            System.out.println("查询的订单不一致");
            throw new SellException(ResultEnum.OPENID_NOT_ONLY);
        }
        return orderService.cancel(orderDTO);
    }
}
