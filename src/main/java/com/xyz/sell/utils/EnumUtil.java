package com.xyz.sell.utils;

import com.xyz.sell.enums.CodeEnum;

/**
 * @Author:zhangyx
 * @Date:Created in 21:442018/11/8
 * @Modified By:
 */
public class EnumUtil {

    public static <T extends CodeEnum> T getByCode(Integer code,Class<T> enumClass){
        for (T each:enumClass.getEnumConstants()){
            if (code.equals(each.getCode())){
                return each;
            }
        }
        return null;
    }
}
