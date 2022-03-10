package com.cjc.funding.mvc.conifg;

import com.cjc.funding.util.constant.CrowConstant;
import com.cjc.funding.util.exception.LoginAccountAlreadyExistException;
import com.cjc.funding.util.utils.CrowUtils;
import com.cjc.funding.util.exception.LoginFailedException;
import com.cjc.funding.util.utils.ResultEntity;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



/**
 * 异常处理器
 */
@ControllerAdvice
public class FundingExceptionResolver {



    @ExceptionHandler(LoginAccountAlreadyExistException.class)
    public ModelAndView resolverLoginAccountAlreadyExistException(HttpServletRequest request,
                                                                  HttpServletResponse response,
                                                                  LoginAccountAlreadyExistException exception){
        // 1.ajax返回异常
        commonExceptionResolver(null,request,response,exception);
        return null;
    }

    /**
     * 空指针异常
     * @param request
     * @param exception
     * @param response
     * @return
     * @throws IOException
     */
    @ExceptionHandler(NullPointerException.class)
    public ModelAndView resolverNullPointException(HttpServletRequest request, ArithmeticException exception, HttpServletResponse response) {
        String viewName="system-error";
        return commonExceptionResolver(viewName,request, response, exception);
    }

    /**
     * 数学异常
     * @return
     */
    @ExceptionHandler(ArithmeticException.class)
    public ModelAndView resolverMathException(HttpServletRequest request, HttpServletResponse response,NullPointerException exception){
        String viewName = "system-error";
        return commonExceptionResolver(viewName,request, response, exception);
    }


    @ExceptionHandler(LoginFailedException.class)
    public ModelAndView resolverLoginFailedException(HttpServletRequest request, HttpServletResponse response,LoginFailedException exception){
        String viewName="admin-login";
        return commonExceptionResolver(viewName,request, response, exception);
    }


    /**
     *
     * @param viewName   设置要跳转的视图
     * @param request
     * @param response
     * @param exception
     * @return
     */
    private ModelAndView commonExceptionResolver(String viewName,HttpServletRequest request,HttpServletResponse response,Exception exception){
        //判断请求是什么类型
        boolean judgeRequestType = CrowUtils.judgeRequestType(request);
        //如果是json

        if(judgeRequestType){
            //输出将错误信息封装为ResultEntity
            String message = exception.getMessage();
            ResultEntity<Object> failed = ResultEntity.failed(message);
            //使用GJson转换为json字符串
            Gson gson = new Gson();
            String failedJson = gson.toJson(failed);
            //写出json数据
            try {
                // 设置编码
                response.setContentType("application/json;charset=utf-8");
                response.setCharacterEncoding("utf-8");
                response.getWriter().write(failedJson);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //返回null的话，框架就会知道我们返回的是json数据
            return null;
        }else{
            //如果是普通请求
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject(CrowConstant.ATTR_NAME_EXCEPTION,exception);
            //返回视图为system-error，数据为exception的modelAndView
            modelAndView.setViewName(viewName);
            return modelAndView;
        }
    }


}
