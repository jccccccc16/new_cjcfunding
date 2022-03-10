package com.cjc.crow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
/**
 * Created by IntelliJ IDEA.
 * User: cjc
 * Date: 2020/10/28
 * Time: 19:14
 * To change this template use File | Settings | File Templates.
 **/
@EnableEurekaClient
@SpringBootApplication
public class CrowdRedisMainClass {
    public static void main(String[] args) {
        SpringApplication.run(CrowdRedisMainClass.class,args);
    }
}
