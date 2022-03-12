package com.cjc.crow.config;

import com.cjc.crow.constant.AccessPassResources;
import com.cjc.crow.constant.CrowdConstant;
import com.cjc.crow.entity.MemberLoginVO;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: cjc
 * Date: 2020/11/8
 * Time: 16:42
 * To change this template use File | Settings | File Templates.
 **/
@Component
public class CrowdAccessFilter extends ZuulFilter {

    private Logger logger = LoggerFactory.getLogger(CrowdAccessFilter.class);

    public String filterType() {

        return "pre";
    }

    public int filterOrder() {
        return 0;
    }

    /***
     * 返回true表示过滤
     * 返回false放行
     * @return
     */
    public boolean shouldFilter() {

        RequestContext requestContext = RequestContext.getCurrentContext();

        HttpServletRequest request = requestContext.getRequest();

        String servletPath = request.getServletPath();

        boolean contains = AccessPassResources.PASS_RES_SET.contains(servletPath);

        // 判断该地址是否可以直接放行
        if(contains){
            return false;
        }

        // 判断是否是静态资源
        // 如果工具方法返回true，取反返回false，表示是静态资源，可以放行
        // 反之，不放行
        return !AccessPassResources.judgeCurrentServletPathWetherStaticResource(servletPath);


    }

    /**
     * 过滤方法
     * @return
     * @throws ZuulException
     */
    public Object run() throws ZuulException {



        RequestContext requestContext = RequestContext.getCurrentContext();

        HttpServletRequest request = requestContext.getRequest();

        HttpSession session = request.getSession();

        Object loginMember = session.getAttribute(CrowdConstant.ATTR_NAME_LOGIN_MEMBER);

        // 判断loginMember是否为空

        if(loginMember==null){
        // 跳转到登录页面
            logger.info("session中loginMember为空");
            HttpServletResponse response = requestContext.getResponse();

            session.setAttribute(CrowdConstant.ATTR_NAME_MESSAGE,CrowdConstant.MESSAGE_ACCESS_FORBIDEN);

            // 重定向到登录页面

            try {
                response.sendRedirect("/auth/member/to/login/page.html");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


        return null;
    }
}
