package com.wenqi.vo;

import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("red_bag_config")
public class RedBagConfigRequest implements Serializable {
    /**
     * 主键，删除时使用
     */
    private Long id;
    /**
     * 红包类型：1 普通红包 2 拼手气红包
     */
    @NotNull(message = "红包类型不能为空")
    private Integer type;

    /**
     * 红包总个数
     */
    @NotNull(message = "红包总个数不能为空")
    private Integer totalNum;

    /**
     * 总金额，单位分
     */
    @NotNull(message = "红包总额不能为空")
    private Integer totalAmount;

    /**
     * 创建人
     */
    @NotNull(message = "创建人不能为空")
    private String creator;

    /**
     * 红包过期时间
     */

    @NotNull(message = "红包过期时间不能为空")
    private Long expireTime;
}