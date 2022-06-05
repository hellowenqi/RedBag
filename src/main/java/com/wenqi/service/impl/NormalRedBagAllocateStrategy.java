package com.wenqi.service.impl;

import com.wenqi.controller.exception.ClientException;
import com.wenqi.service.RedBagAllocateI;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 普通红包
 *
 * @author wuwenqi04
 * @classname：NormalRedBagAllocateStrategy
 * @date 2022/06/05
 */

@Service("NormalRedBagAllocateStrategy")
public class NormalRedBagAllocateStrategy implements RedBagAllocateI {

    @Override
    public List<Integer> allocate(Integer totalAmount, Integer count) {
        List<Integer> res = new ArrayList<>();
        // 不足一分钱
        if (count > totalAmount) {
            throw new ClientException("单个红包金额不能小于1分钱");
        }
        Integer singleAmount = Math.round((float) totalAmount / (float)count);
        for (int i = 0; i < count - 1 ; i++) {
            res.add(singleAmount);
        }

        // 最后一个单独计算
        res.add(totalAmount - singleAmount * (count - 1));

        return  res;
    }
}
