package com.xyz.sell.controller;

import com.xyz.sell.dto.OrderDTO;
import com.xyz.sell.enums.ResultEnum;
import com.xyz.sell.exception.SellException;
import com.xyz.sell.service.OrderService;
import org.aspectj.weaver.ast.Or;
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
                              ,@RequestParam(value = "size",defaultValue = "3") Integer size
                                      , Map<String,Object> map){

          PageRequest pageRequest=new PageRequest(page-1,size);
          Page<OrderDTO> orderDTOPage=orderService.findList(pageRequest);
        System.out.println(orderDTOPage.getTotalPages());
          map.put("orderDTOPage",orderDTOPage);
          map.put("currentPage",page);
          map.put("size",size);
          return new ModelAndView("order/list",map);
    }

    //取消订单
    @GetMapping("/cancel")
    public ModelAndView cancel(@RequestParam("orderId") String orderId
                             ,Map<String,Object> map){
        //先查询一次订单 查出来orderDto
        try {
            OrderDTO orderDTO=orderService.findOne(orderId);
            orderService.cancel(orderDTO);
        }catch (SellException e){
            System.out.println("查询不到这个订单！！");
            map.put("msg", ResultEnum.ORDER_NOT_EXIST);
            map.put("url","/sell/seller/order/list");
            return new ModelAndView("order/common/error",map);
        }
        map.put("msg","订单取消成功");
        map.put("url","/sell/seller/order/list");

        return new ModelAndView("/order/common/success");
    }

    //订单详情页面
    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam("orderId")String orderId
                                 ,Map<String,Object> map){
        OrderDTO orderDTO=new OrderDTO();
        try {
            orderDTO=orderService.findOne(orderId);
        }catch (SellException e){
        System.out.println("订单详情查询失败！");
        map.put("msg",e.getMessage());
        map.put("url","/sell/seller/order/list");
        }
        map.put("orderDTO",orderDTO);
        return new ModelAndView("/order/detail",map);
    }

    //完结订单
    @GetMapping("/finish")
    public ModelAndView finish(@RequestParam("orderId") String orderId,Map<String,Object> map){
        //先查询一次订单 查出来orderDto
        try {
            OrderDTO orderDTO=orderService.findOne(orderId);
            orderService.finish(orderDTO);
        }catch (SellException e){
            System.out.println("查询不到这个订单！！");
            map.put("msg", ResultEnum.ORDER_NOT_EXIST);
            map.put("url","/sell/seller/order/list");
            return new ModelAndView("order/common/error",map);
        }
        map.put("msg","订单取消成功");
        map.put("url","/sell/seller/order/list");

        return new ModelAndView("/order/common/success");
    }
}
