package com.wenqi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BaseResponse<T> {
    /**
     *  状态0表示请求成功，1表示请求失败
     */
    private Integer status;
    /**
     * 描述
     */
    private String des;
    /**
     * 数据
     */
    private T data;

    public static <T> BaseResponse<T> success(T data) {

        return new BaseResponse<>(StatusEnum.SUCCESS.getCode(), StatusEnum.SUCCESS.getMessage(), data);
    }

    public static <T> BaseResponse<T> failure(int statusCode, String msg) {

        return new BaseResponse<>(statusCode, msg, null);
    }

}
