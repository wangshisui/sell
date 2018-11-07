package com.xyz.sell.controller;

import com.xyz.sell.dto.OrderDTO;
import com.xyz.sell.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * @Author:zhangyx
 * @Date:Created in 20:192018/11/3
 * @Modified By:
 */
@Controller
@RequestMapping("/seller/order")
public class SellOrderController {

    @Autowired
    private OrderService orderService;
    /**
     * 订单列表
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page",defaultValue = "1") Integer page
                              ,@RequestParam(value = "size",defaultValue = "10") Integer size
                                      , Map<String,Object> map){

          PageRequest pageRequest=new PageRequest(page-1,size);
          Page<OrderDTO> orderDTOPage=orderService.findList(pageRequest);
          map.put("orderDTOPage",orderDTOPage);
          return new ModelAndView("order/list",map);
    }
}
