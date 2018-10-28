package com.xyz.sell.controller;

import com.xyz.sell.dataobject.ProductCategory;
import com.xyz.sell.dataobject.ProductInfo;
import com.xyz.sell.service.CategoryService;
import com.xyz.sell.service.ProductInfoService;
import com.xyz.sell.utils.ResultVoUtils;
import com.xyz.sell.vo.ProductInfoVo;
import com.xyz.sell.vo.ProductVo;
import com.xyz.sell.vo.ResultVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author:zhangyx
 * @Date:Created in 16:482018/10/28
 * @Modified By:
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {
     //1.查商品详情
    @Autowired
    private ProductInfoService productInfoService;
    //2.查类目
    @Autowired
    private CategoryService categoryService;


     @GetMapping("/list")
    public ResultVo list(){
         //1.查询所有上架的商品
         List<ProductInfo> productInfoList=productInfoService.findUpAll();

         //2.查询商品类目
        List<Integer> categoryTypeList=new ArrayList<>();
             for(ProductInfo productInfo:productInfoList){
                 categoryTypeList.add(productInfo.getCategoryType());
   }
       List<ProductCategory> productCategoryList=categoryService.findByCategoryTypeIn(categoryTypeList);
         //3.数据拼接

         //1.先拼装productvo
         List<ProductVo> productVos=new ArrayList<>();
         for(ProductCategory productCategory:productCategoryList){
             ProductVo productVo=new ProductVo();
             productVo.setCategoryType(productCategory.getCategoryType());
             productVo.setCategoryName(productCategory.getCategoryName());

             //2.遍历商品详情
             List<ProductInfoVo> productInfoVoList=new ArrayList<>();
             for(ProductInfo productInfo:productInfoList){
                 if(productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                     ProductInfoVo productInfoVo=new ProductInfoVo();
                     BeanUtils.copyProperties(productInfo,productInfoVo);
                     productInfoVoList.add(productInfoVo);
                 }
             }
                productVo.setProductInfoVoList(productInfoVoList);
             productVos.add(productVo);
         }


         ResultVo resultVo=new ResultVo();
       /*  ProductVo productVo=new ProductVo();
         ProductInfoVo productInfoVo=new ProductInfoVo();
         productVo.setProductInfoVoList(Arrays.asList(productInfoVo));
         resultVo.setData(Arrays.asList(productVo));*/
    /*   resultVo.setData(productVos);
         resultVo.setCode(0);
         resultVo.setMsg("成功");


         return resultVo;*/
       return ResultVoUtils.success(productVos);
    }


}
