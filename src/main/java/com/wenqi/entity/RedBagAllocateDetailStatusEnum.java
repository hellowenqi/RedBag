package com.wenqi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RedBagAllocateDetailStatusEnum {
    NOT_ALLOCATE(1, "未领取"),
    ALLOCATED (2, "已领取");

    private int value;
    private String message;
}
