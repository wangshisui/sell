package com.xyz.sell.comverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xyz.sell.dataobject.OrderDetail;
import com.xyz.sell.dto.OrderDTO;
import com.xyz.sell.enums.ResultEnum;
import com.xyz.sell.exception.SellException;
import com.xyz.sell.form.OrderForm;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:zhangyx
 * @Date:Created in 14:582018/11/3
 * @Modified By:
 */
public class OrderForm2OrderDto {

    public static OrderDTO convert(OrderForm orderForm){
        Gson gson=new Gson();
        OrderDTO orderDTO=new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());

        List<OrderDetail> orderDetailList=new ArrayList<>();
        try{
            orderDetailList= gson.fromJson(orderForm.getItems()
                    ,new TypeToken<List<OrderDetail>>(){}.getType());
        }catch (Exception e){
            System.out.println("您的购物车不是json格式");
            throw new SellException(ResultEnum.ITEMS_NOT_JSON);
        }
        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;


    }
}
