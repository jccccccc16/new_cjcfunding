package com.cjc.funding.mvc.controller;

import com.cjc.funding.util.utils.CrowUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;


@Controller
public class HelloController {


    Logger logger = LoggerFactory.getLogger(HelloController.class);

    @RequestMapping("/hello.html")
    public String hello(HttpServletRequest request){
        boolean judgeRequestType = CrowUtils.judgeRequestType(request);
        int i=1/0;
        logger.info("judgeRequestType"+judgeRequestType);
        return "hello";
    }


}
