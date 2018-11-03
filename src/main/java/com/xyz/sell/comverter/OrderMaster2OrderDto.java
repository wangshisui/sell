package com.xyz.sell.comverter;

import com.xyz.sell.dataobject.OrderMaster;
import com.xyz.sell.dto.OrderDTO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:zhangyx
 * @Date:Created in 12:382018/11/3
 * @Modified By:
 */
public class OrderMaster2OrderDto {

    public static OrderDTO convert(OrderMaster orderMaster){
        OrderDTO orderDTO=new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
    return orderDTO;
    }

    public  static List<OrderDTO> convert(List<OrderMaster> orderMasterList){
        List<OrderDTO> orderDTOList=new ArrayList<>();
        BeanUtils.copyProperties(orderMasterList,orderDTOList);
        return orderDTOList;
    }
}
