package com.xyz.sell.utils;

import com.xyz.sell.vo.ResultVo;

/**
 * @Author:zhangyx
 * @Date:Created in 21:292018/10/28
 * @Modified By:
 */
public class ResultVoUtils {
    //1.成功
        public static ResultVo success(Object object){
            ResultVo resultVo=new ResultVo();
            resultVo.setData(object);
            resultVo.setCode(0);
            resultVo.setMsg("成功");
            return resultVo;
        }
        //2.什么都不传
         public static ResultVo success(){
            return success();
         }
         //3.错误的

          public static ResultVo error(Integer code,String msg){
             ResultVo resultVo=new ResultVo();
             resultVo.setMsg(msg);
             resultVo.setCode(code);
             return resultVo;
          }
}
