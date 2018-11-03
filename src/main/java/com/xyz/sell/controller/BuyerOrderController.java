package com.xyz.sell.controller;

import com.google.gson.Gson;
import com.xyz.sell.comverter.OrderForm2OrderDto;
import com.xyz.sell.comverter.OrderMaster2OrderDto;
import com.xyz.sell.dto.OrderDTO;
import com.xyz.sell.enums.ResultEnum;
import com.xyz.sell.exception.SellException;
import com.xyz.sell.form.OrderForm;
import com.xyz.sell.service.BuyerService;
import com.xyz.sell.service.OrderService;
import com.xyz.sell.utils.ResultVoUtils;
import com.xyz.sell.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:zhangyx
 * @Date:Created in 14:362018/11/3
 * @Modified By:
 */
@RequestMapping("/buyer/order")
@RestController
public class BuyerOrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private BuyerService buyerService;
    //1.创建订单
    @PostMapping("/create")
public ResultVo<Map<String,String>> create(@Valid OrderForm orderForm, BindingResult bindingResult){

    //先判断表单校验是否错误
    if(bindingResult.hasErrors()){
        System.out.println("表单校验错误");
        throw new SellException(ResultEnum.FROM_FALSE.getCode()
                ,bindingResult.getFieldError().getDefaultMessage());
    }
    OrderForm2OrderDto orderForm2OrderDto=new OrderForm2OrderDto();
    OrderDTO orderDTO=orderForm2OrderDto.convert(orderForm);
    if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
        System.out.println("购物车不能为空");
        throw new SellException(ResultEnum.ITEMS_NOT_EMPTY);
    }

   OrderDTO orderResult= orderService.create(orderDTO);
  Map<String,String> map=new HashMap<>();
    map.put("orderId",orderDTO.getOrderId());
  return ResultVoUtils.success();

}

    //2.订单列表
    @GetMapping("/list")
    public ResultVo<List<OrderDTO>> list(@RequestParam("openid") String openid
                       ,@RequestParam(value = "page",defaultValue = "0") Integer page
                        ,@RequestParam(value = "size",defaultValue = "10")Integer size ){
      if(StringUtils.isEmpty(openid)){
          System.out.println("订单不能为空");
          throw  new SellException(ResultEnum.ORDER_NOT_EXIST);
      }
        PageRequest pageRequest=new PageRequest(page,size);
     Page<OrderDTO> orderDTOPage= orderService.findList(openid,pageRequest);

     //能够返回前端需要的page数据
   // return ResultVoUtils.success(orderDTOPage.getTotalPages());
        return ResultVoUtils.success(orderDTOPage.getContent());
    }

    //3.订单详情
   @GetMapping("/detail")
    public ResultVo<OrderDTO> detail(@RequestParam("openid")String openid,@RequestParam("orderId")String orderId){

       /* //Todo不安全的写法
        OrderDTO orderDTO=orderService.findOne(orderId);
       */
       OrderDTO orderDTO=buyerService.findOrderOne(openid,orderId);
       return ResultVoUtils.success(orderDTO);
    }


    //4.取消订单
    @PostMapping("/cancel")
    public ResultVo<OrderDTO> cancel(@RequestParam("openid")String openid,@RequestParam("orderId")String orderId){

       /* //Todo不安全的写法
        OrderDTO orderDTO=orderService.findOne(orderId);
       orderService.cancel(orderDTO);*/
       buyerService.cancelOrder(openid,orderId);
        return ResultVoUtils.success();
    }
}
