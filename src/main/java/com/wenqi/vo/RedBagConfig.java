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
@TableName("red_bag_config")
public class RedBagConfig implements Serializable {

    private static final long serialVersionUID = 1L;

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
    private Long expireTime;
}