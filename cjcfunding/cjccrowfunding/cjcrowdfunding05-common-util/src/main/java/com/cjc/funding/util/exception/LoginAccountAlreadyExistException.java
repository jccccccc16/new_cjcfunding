package com.cjc.funding.util.exception;

/**
 * Created by IntelliJ IDEA.
 * User: cjc
 * Date: 2020/9/24
 * Time: 22:06
 * To change this template use File | Settings | File Templates.
 **/
public class LoginAccountAlreadyExistException extends RuntimeException {

    public LoginAccountAlreadyExistException(String message) {
        super(message);
    }

    public LoginAccountAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginAccountAlreadyExistException(Throwable cause) {
        super(cause);
    }
}
