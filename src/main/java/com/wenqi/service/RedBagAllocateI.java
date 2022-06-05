package com.wenqi.service;

import java.util.List;

public interface RedBagAllocateI {
    /**
     * 红包分配策略
     * @param totalAmount 总金额
     * @param count 数量
     * @return
     */
    List<Integer> allocate(Integer totalAmount, Integer count);
}
