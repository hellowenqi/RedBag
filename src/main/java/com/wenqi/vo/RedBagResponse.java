package com.wenqi.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 发红包领取详情
 *
 * @author wuwenqi
 * @date 2022-06-05
 */
@Data
public class RedBagResponse implements Serializable {

    /**
     * 主键
     */
    private Long id;

    /**
     * 红包类型：1 普通红包 2 拼手气红包
     */
    private Integer type;

    /**
     * 红包总个数
     */
    private Integer totalNum;

    /**
     * 红包已经发放的个数
     */
    private Integer usedNum;

    /**
     * 总金额，单位分
     */
    private Integer totalAmount;

    /**
     * 已经使用的金额，单位分
     */
    private Integer usedAmount;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 红包过期时间
     */
    private Date expireTime;


    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 领取明细
     */
    private List<RedBagAllocateDetailResponse> redBagAllocateDetailResponseList;
}