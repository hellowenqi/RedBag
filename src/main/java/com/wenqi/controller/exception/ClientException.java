package com.wenqi.controller.exception;

/**
 * ClientException
 *
 * @author wuwenqi04
 * @classname：ClientException
 * @date 2022/01/14
 */
public class ClientException extends RuntimeException {
    public ClientException (String msg) {
        super(msg);
    }
    public ClientException (String msg, Throwable cause) {
        super(msg, cause);
    }
}
