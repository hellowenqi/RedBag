package com.wenqi.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 发红包配置详情
 *
 * @author wuwenqi
 * @date 2022-06-05
 */
@Data
public class DeleteRequest {
    /**
     * 主键
     */
    private Long id;
}