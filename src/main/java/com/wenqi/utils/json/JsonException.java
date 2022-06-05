package com.wenqi.utils.json;

/**
 * Created by wuwenqi04 on 2020/5/14.
 */
public class JsonException extends RuntimeException {
    public JsonException(String message) {
        super(message);
    }

    public JsonException(String message, Exception e) {
        super(message, e);
    }
}
