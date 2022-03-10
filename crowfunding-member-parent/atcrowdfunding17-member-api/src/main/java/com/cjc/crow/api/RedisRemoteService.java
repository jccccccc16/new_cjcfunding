package com.cjc.crow.api;

import com.cjc.crow.util.ResultEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.concurrent.TimeUnit;

/**
 * Created by IntelliJ IDEA.
 * User: cjc
 * Date: 2020/11/2
 * Time: 20:40
 * To change this template use File | Settings | File Templates.
 **/
@FeignClient("crow-redis")
public interface RedisRemoteService {

    @RequestMapping("/set/redis/key/value/remote")
    public ResultEntity<String> setRedisKeyValueRemote(
            @RequestParam("key") String key,
            @RequestParam("value") String value
    );

    @RequestMapping("/set/redis/key/value/remote/with/timeout")
    public ResultEntity<String> setRedisKeyValueRemoteWithTimeout(
            @RequestParam("key") String key,
            @RequestParam("value") String value,
            @RequestParam("time") long time,
            @RequestParam("timeUnit") TimeUnit timeUnit
    );

    @RequestMapping("/get/redis/string/by/key")
    public ResultEntity<String> getRedisStringByKeyRemote(
            @RequestParam("key") String eky
    );

    @RequestMapping("/remove/redis/key/remote")
    public ResultEntity<String> removeRedisKeyRemote(
            @RequestParam("key") String key
    );

}
