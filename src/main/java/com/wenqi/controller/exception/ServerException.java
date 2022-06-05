package com.wenqi.controller.exception;

/**
 * ServerException
 *
 * @author wuwenqi04
 * @classname：ServerException
 * @date 2022/01/14
 */
public class ServerException extends RuntimeException {
    public ServerException(String message) {
        super(message);
    }
    public ServerException(String message, Throwable ex) {
        super(message, ex);
    }
}
