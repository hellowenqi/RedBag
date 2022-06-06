package com.wenqi.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 发红包配置详情
 *
 * @author wuwenqi
 * @date 2022-06-05
 */
@Data
public class GrabRedBagRequest {
    /**
     * 红包Id
     */
    @NotNull(message = "红包Id不能为空")
    private Long id;
    /**
     * 用户名
     */
    @NotNull(message = "用户不能为空")
    private String userName;
}