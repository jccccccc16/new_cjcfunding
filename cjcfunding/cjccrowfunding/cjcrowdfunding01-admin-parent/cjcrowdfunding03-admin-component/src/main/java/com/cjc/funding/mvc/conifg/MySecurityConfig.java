package com.cjc.funding.mvc.conifg;

import org.aspectj.weaver.ast.And;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by IntelliJ IDEA.
 * User: cjc
 * Date: 2020/10/17
 * Time: 17:39
 * To change this template use File | Settings | File Templates.
 **/
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private Mp5PassEncoder mp5PassEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()  // 对请求进行授权
                .antMatchers("/admin/to/login/page.html")
                .permitAll()
                .antMatchers("/admin/do/doLogin.html")
                .permitAll()
                .antMatchers("/bootstrap/**") // 放开所有静态资源允许未登录访问
                .permitAll()
                .antMatchers("/css/**")
                .permitAll()
                .antMatchers("/fonts/**")
                .permitAll()
                .antMatchers("/funding/**")
                .permitAll()
                .antMatchers("/img/**")
                .permitAll()
                .antMatchers("/jquery/**")
                .permitAll()
                .antMatchers("/layer/**")
                .permitAll()
                .antMatchers("/script/**")
                .permitAll()
                .antMatchers("/ztree/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin() // 登录相关的设置
                .usernameParameter("loginAcct")
                .passwordParameter("userPswd")
                .permitAll()
                .loginPage("/admin/to/login/page.html") // 设置登录页
                .loginProcessingUrl("/admin/do/doLogin.html") // 处理登录请求的地址
                .defaultSuccessUrl("/admin/to/main/page.html") // 登录成功前往的页面
                .and()
                .csrf()  // 管理csrf功能
                .disable()
                .logout()
                .logoutUrl("/admin/do/doLogout.html")
                .logoutSuccessUrl("/admin/to/login/page.html");

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(mp5PassEncoder);
    }
}
