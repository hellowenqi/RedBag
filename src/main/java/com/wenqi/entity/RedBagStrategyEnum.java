package com.wenqi.entity;

/**
 * RedBagStrategyEnum
 *
 * @author wuwenqi04
 * @classname：StrategyEnum
 * @date 2022/06/05
 */

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RedBagStrategyEnum {
    NORMAL(1, "普通红包", "NormalRedBagAllocateStrategy"),
    RANDOM(2, "随机红包", "RandomRedBagAllocateStrategy");
    private int value;
    private String msg;
    private String strategyName;
}
