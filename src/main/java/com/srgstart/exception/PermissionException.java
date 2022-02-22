package com.srgstart.exception;


/**
 * 自定义全局异常类
 *
 * @author srgstart
 */
public class PermissionException extends  RuntimeException {


    public PermissionException(String message) {
        super(message);
    }
}
