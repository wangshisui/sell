package com.xyz.sell.vo;

import lombok.Data;

/**
 * @Author:zhangyx
 * @Date:Created in 16:512018/10/28
 * @Modified By:
 */
@Data
public class ResultVo<T> {

    private Integer code;

    private String msg;

    private T data;
}
