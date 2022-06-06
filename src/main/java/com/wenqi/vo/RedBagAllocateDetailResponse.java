package com.wenqi.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 红包分配明细
 *
 * @author wuwenqi
 * @date 2022-06-06
 */
@Data
public class RedBagAllocateDetailResponse {

    /**
     * 主键
     */
    private Long id;

    /**
     * 单个红包金额，单位分
     */
    private Integer amount;

    /**
     * 领取人
     */
    private String receiver;

    /**
     * 领取时间
     */
    private Date receiveTime;
}