package com.cjc.crow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Created by IntelliJ IDEA.
 * User: cjc
 * Date: 2021/1/25
 * Time: 13:44
 * To change this template use File | Settings | File Templates.
 **/




@EnableFeignClients
@SpringBootApplication
public class OrderMainClass {
    public static void main(String[] args) {
        SpringApplication.run(OrderMainClass.class,args);
    }
}
