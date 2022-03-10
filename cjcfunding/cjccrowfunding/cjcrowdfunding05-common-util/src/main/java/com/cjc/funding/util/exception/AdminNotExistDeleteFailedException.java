package com.cjc.funding.util.exception;

/**
 * Created by IntelliJ IDEA.
 * User: cjc
 * Date: 2020/9/23
 * Time: 17:08
 * To change this template use File | Settings | File Templates.
 **/
public class AdminNotExistDeleteFailedException extends RuntimeException {
    public AdminNotExistDeleteFailedException() {
    }

    public AdminNotExistDeleteFailedException(String message) {
        super(message);
    }

    public AdminNotExistDeleteFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public AdminNotExistDeleteFailedException(Throwable cause) {
        super(cause);
    }
}
