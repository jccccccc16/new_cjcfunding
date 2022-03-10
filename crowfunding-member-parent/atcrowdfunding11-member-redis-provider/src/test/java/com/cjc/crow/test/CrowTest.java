package com.cjc.crow.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by IntelliJ IDEA.
 * User: cjc
 * Date: 2020/11/1
 * Time: 20:23
 * To change this template use File | Settings | File Templates.
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class CrowTest {

    private Logger logger = LoggerFactory.getLogger(CrowTest.class);

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testRedis(){
        System.out.println(redisTemplate);
        ValueOperations<String,String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set("apple" +
                "","string");
        String string = valueOperations.get("apple");
        logger.info(string);

    }

    @Test
    public void testGetFromRedis(){

        ValueOperations<String,String> valueOperations = redisTemplate.opsForValue();
        String s = valueOperations.get("red");
        logger.info(s);

    }

}
