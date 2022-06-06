package com.wenqi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import lombok.Data;

/**
 * 发红包配置详情
 *
 * @author wuwenqi
 * @date 2022-06-05
 */
@Data
@TableName("red_bag_config")
public class RedBagConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 红包类型：1 普通红包 2 拼手气红包
     */
    @TableField("type")
    private Integer type;

    /**
     * 红包总个数
     */
    @TableField("total_num")
    private Integer totalNum;

    /**
     * 红包已经发放的个数
     */
    @TableField("used_num")
    private Integer usedNum;

    /**
     * 总金额，单位分
     */
    @TableField("total_amount")
    private Integer totalAmount;

    /**
     * 已经使用的金额，单位分
     */
    @TableField("used_amount")
    private Integer usedAmount;

    /**
     * 创建人
     */
    @TableField("creator")
    private String creator;

    /**
     * 红包过期时间
     */
    @TableField("expire_time")
    private Long expireTime;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Long createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private Long updateTime;


}