package com.cjc.crow.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by IntelliJ IDEA.
 * User: cjc
 * Date: 2020/11/13
 * Time: 16:49
 * To change this template use File | Settings | File Templates.
 **/
@Configuration
public class CrowdWebMvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {

        registry.addViewController("/agree/protocol/page.html").setViewName("project-agree");

        registry.addViewController("/launch/protocol/page.html").setViewName("project-launch");

        registry.addViewController("/return/protocol/page.html").setViewName("project-return");

        registry.addViewController("/confirm/protocol/page.html").setViewName("project-confirm");

        registry.addViewController("/create/success.html").setViewName("project-success");

    }
}
