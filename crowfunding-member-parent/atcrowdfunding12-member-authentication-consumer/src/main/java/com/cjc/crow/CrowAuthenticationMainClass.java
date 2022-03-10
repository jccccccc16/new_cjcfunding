package com.cjc.crow;

import com.cjc.crow.config.ShortMessageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


/**
 * Created by IntelliJ IDEA.
 * User: cjc
 * Date: 2020/11/4
 * Time: 10:44
 * To change this template use File | Settings | File Templates.
 **/
@EnableConfigurationProperties({ShortMessageProperties.class})
@EnableEurekaClient
@SpringBootApplication
@EnableFeignClients
public class CrowAuthenticationMainClass {

    public static void main(String[] args) {
        SpringApplication.run(CrowAuthenticationMainClass.class, args);
    }
}

