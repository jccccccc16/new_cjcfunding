package com.cjc.crow.handle;

import com.cjc.crow.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.concurrent.TimeUnit;

/**
 * Created by IntelliJ IDEA.
 * User: cjc
 * Date: 2020/11/2
 * Time: 21:18
 * To change this template use File | Settings | File Templates.
 **/
@RestController
public class RdisHandler {

    @Autowired
    private StringRedisTemplate redisTemplate;


    @RequestMapping("/set/redis/key/value/remote")
    public ResultEntity<String> setRedisKeyValueRemote(
            @RequestParam("key") String key,
            @RequestParam("value") String value
    ) {

        ValueOperations<String, String> stringStringValueOperations = redisTemplate.opsForValue();
        try {

            stringStringValueOperations.set(key, value);

            return ResultEntity.successWithoutData();

        } catch (Exception exception) {
            exception.printStackTrace();

            return ResultEntity.failed(exception.getMessage());
        }

    }

    @RequestMapping("/set/redis/key/value/remote/with/timeout")
    public ResultEntity<String> setRedisKeyValueRemoteWithTimeout(
            @RequestParam("key") String key,
            @RequestParam("value") String value,
            @RequestParam("time") long time,
            @RequestParam("timeUnit") TimeUnit timeUnit
    ){
        ValueOperations<String, String> stringStringValueOperations = redisTemplate.opsForValue();
        try {

            stringStringValueOperations.set(key,value,time,timeUnit);

            return ResultEntity.successWithoutData();

        } catch (Exception exception) {
            exception.printStackTrace();

            return ResultEntity.failed(exception.getMessage());
        }

    }

    @RequestMapping("/get/redis/string/by/key")
    public ResultEntity<String> getRedisStringByKeyRemote(
            @RequestParam("key") String key
    ){
        ValueOperations<String, String> stringStringValueOperations = redisTemplate.opsForValue();
        try {

            String value = stringStringValueOperations.get(key);

            return ResultEntity.successWithData(value);

        } catch (Exception exception) {
            exception.printStackTrace();

            return ResultEntity.failed(exception.getMessage());
        }
    }

    @RequestMapping("/remove/redis/key/remote")
    public ResultEntity<String> removeRedisKeyRemote(
            @RequestParam("key") String key
    ){

        ValueOperations<String, String> stringStringValueOperations = redisTemplate.opsForValue();
        try {

            redisTemplate.delete(key);

            return ResultEntity.successWithoutData();

        } catch (Exception exception) {
            exception.printStackTrace();

            return ResultEntity.failed(exception.getMessage());
        }

    }


}
