package com.wenqi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StatusEnum {
    SUCCESS(0, "成功"),
    PARAM_ERROR(400, "参数错误"),
    INTERNAL_SERVER_ERROR(500, "系统错误");

    private int code;
    private String message;
}
