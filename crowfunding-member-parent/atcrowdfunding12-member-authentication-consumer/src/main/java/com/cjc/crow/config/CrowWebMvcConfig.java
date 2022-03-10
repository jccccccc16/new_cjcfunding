package com.cjc.crow.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by IntelliJ IDEA.
 * User: cjc
 * Date: 2020/11/5
 * Time: 21:55
 * To change this template use File | Settings | File Templates.
 **/
@Configuration
public class CrowWebMvcConfig implements WebMvcConfigurer {


    public void addViewControllers(ViewControllerRegistry registry) {


        // 注册页面映射
        // url
        String regUrlPath = "/auth/member/to/reg/page.html";
        String regViewName  ="member-reg";
        registry.addViewController(regUrlPath).setViewName(regViewName);

        // 登陆页面映射

        String loginUrlPath = "/auth/member/to/login/page.html";

        String loginViewName = "member-login";

        registry.addViewController(loginUrlPath).setViewName(loginViewName);

        String memberUrlPath = "/auth/member/to/center.html";
        String memberViewName = "member-center";
        // 用户页面
        registry.addViewController(memberUrlPath).setViewName(memberViewName);


        // 我的众筹
        registry.addViewController("/member/my/crowd").setViewName("member_crowd");



    }
}
