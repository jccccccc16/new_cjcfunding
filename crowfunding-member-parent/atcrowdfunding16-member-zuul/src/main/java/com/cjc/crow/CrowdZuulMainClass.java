package com.cjc.crow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * Created by IntelliJ IDEA.
 * User: cjc
 * Date: 2020/11/4
 * Time: 22:13
 * To change this template use File | Settings | File Templates.
 **/

@EnableZuulProxy
@EnableEurekaClient
@SpringBootApplication
public class CrowdZuulMainClass {

    public static void main(String[] args) {
        SpringApplication.run(CrowdZuulMainClass.class, args);
    }

}
