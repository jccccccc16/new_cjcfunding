package com.cjc.crowd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Created by IntelliJ IDEA.
 * User: cjc
 * Date: 2020/10/28
 * Time: 19:14
 * To change this template use File | Settings | File Templates.
 **/
@EnableEurekaServer
@SpringBootApplication
public class CrowdEurekaMainClass {
    public static void main(String[] args) {
        SpringApplication.run(CrowdEurekaMainClass.class,args);
    }
}
