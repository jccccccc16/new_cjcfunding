package com.cjc.funding.util.exception;

/**
 * Created by IntelliJ IDEA.
 * User: cjc
 * Date: 2020/9/21
 * Time: 22:08
 * To change this template use File | Settings | File Templates.
 *
 *
 * 没有登录时访问被保护资源时被拦截器拦截，抛出异常
 **/
public class AccessForbiddenException extends RuntimeException{

    public AccessForbiddenException() {
    }

    public AccessForbiddenException(String message) {
        super(message);
    }

    public AccessForbiddenException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccessForbiddenException(Throwable cause) {
        super(cause);
    }
}
