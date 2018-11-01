package com.xyz.sell.utils;

import java.util.Random;

/**
 * @Author:zhangyx
 * @Date:Created in 20:272018/10/29
 * @Modified By:
 */
public class KeyUtils {

    //生成唯一的主键

    public static synchronized String getRandomKey(){
        Random random=new Random();
       Integer num= random.nextInt(900000)+100000;

       return  System.currentTimeMillis()+String.valueOf(num);
    }
}
