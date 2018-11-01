package com.xyz.sell.exception;

import com.xyz.sell.enums.ResultEnum;

/**
 * @Author:zhangyx
 * @Date:Created in 20:112018/10/29
 * @Modified By:
 */
public class SellException extends RuntimeException {


    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();


    }


}
