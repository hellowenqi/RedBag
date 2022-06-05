package com.wenqi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import lombok.Data;

/**
 * 红包分配明细
 *
 * @author wuwenqi
 * @date 2022-06-05
 */
@Data
@TableName("red_bag_allocate_detail")
public class RedBagAllocateDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 单个红包金额，单位分
     */
    @TableField("amount")
    private Integer amount;

    /**
     * 红包配置Id
     */
    @TableField("red_bag_config_id")
    private Long redBagConfigId;

    /**
     * 领取状态：1 未领取 2 已领取
     */
    @TableField("status")
    private Integer status;

    /**
     * 领取人
     */
    @TableField("receiver")
    private String receiver;

    /**
     * 领取时间
     */
    @TableField("receive_time")
    private Long receiveTime;

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