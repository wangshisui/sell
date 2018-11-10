package com.xyz.sell.controller;

import com.xyz.sell.dataobject.ProductInfo;
import com.xyz.sell.dto.OrderDTO;
import com.xyz.sell.exception.SellException;
import com.xyz.sell.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**卖家端商品
 * @Author:zhangyx
 * @Date:Created in 16:062018/11/10
 * @Modified By:
 */
@Controller
@RequestMapping("/seller/product")
public class SellerProductController {
    @Autowired
    private ProductInfoService productInfoService;

    //商品列表
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page",defaultValue = "1") Integer page
            ,@RequestParam(value = "size",defaultValue = "3") Integer size
            , Map<String,Object> map){

        PageRequest pageRequest=new PageRequest(page-1,size);
        Page<ProductInfo> productInfoPage=productInfoService.findAll(pageRequest);

        map.put("productInfoPage",productInfoPage);
        map.put("currentPage",page);
        map.put("size",size);
        return new ModelAndView("/order/product/list",map);
    }

    //商品上架
    @GetMapping("/on_sale")
    public ModelAndView onSale(@RequestParam("productId") String productId,Map<String,Object> map){
        try {
            productInfoService.onSale(productId);
        }catch (SellException e){
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/product/list");
            return new ModelAndView("/order/common/error",map);
        }
        map.put("msg","商品上架成功");
        map.put("url","/sell/seller/product/list");
      return new ModelAndView("/order/common/success",map);

    }

    //商品下架
    @GetMapping("/off_sale")
    public ModelAndView offSale(@RequestParam("productId") String productId,Map<String,Object> map){
        try {
            productInfoService.offSale(productId);
        }catch (SellException e){
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/product/list");
            return new ModelAndView("/order/common/error",map);
        }
        map.put("msg","商品下架成功");
        map.put("url","/sell/seller/product/list");
        return new ModelAndView("/order/common/success",map);

    }
}
