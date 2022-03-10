package com.cjc.funding.mvc.interceptor;

import com.cjc.funding.entity.Admin;
import com.cjc.funding.util.constant.CrowConstant;
import com.cjc.funding.util.exception.AccessForbiddenException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by IntelliJ IDEA.
 * User: cjc
 * Date: 2020/9/21
 * Time: 17:35
 * To change this template use File | Settings | File Templates.
 * <p>
 * 登录的拦截器，对一些资源的访问，拦截没有登录的用户
 **/
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 1.获取admin
        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute(CrowConstant.ATTR_NAME_LOGIN_ADMIN);
        // 2.判断admin是否为空
        if (admin == null) {
            // 3.为空抛出异常
            throw new AccessForbiddenException(CrowConstant.MESSAGE_ACCESS_FORBIDDEN);
        }

        // 4.不为空，返回true放行
        return true;
    }
}
