package com.xyz.sell;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author:zhangyx
 * @Date:Created in 12:302018/10/28
 * @Modified By:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LoggerTest {
    private final Logger logger= LoggerFactory.getLogger(LoggerTest.class);
    @Test
    public  void test1(){
     logger.info("哈哈哈");
    }
}
