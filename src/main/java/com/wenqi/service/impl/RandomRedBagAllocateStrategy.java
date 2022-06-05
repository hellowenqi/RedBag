package com.wenqi.service.impl;

import com.wenqi.controller.exception.ClientException;
import com.wenqi.service.RedBagAllocateI;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 普通红包
 *
 * @author wuwenqi04
 * @classname：RandomRedBagAllocateStrategy
 * @date 2022/06/05
 */

@Service("RandomRedBagAllocateStrategy")
public class RandomRedBagAllocateStrategy implements RedBagAllocateI {

    @Override
    public List<Integer> allocate(Integer totalAmount, Integer count) {
        List<Integer> res = new ArrayList<>();
        // 不足一分钱
        if (count > totalAmount) {
            throw new ClientException("单个红包金额不能小于1分钱");
        }

        // 随机生成count - 1 个整数，处于 1-totalAmount之间
        Set<Integer> randomIntegerSet = new HashSet<>();
        while (randomIntegerSet.size() < count - 1) {
            int random = (int)Math.round(Math.random() * totalAmount);
            if (random <=0 || random >= totalAmount) {
                continue;
            }

            randomIntegerSet.add(random);
        }

        List<Integer> randomList = new ArrayList<>(randomIntegerSet);
        Collections.sort(randomList);
        int formerInteger = 0;
        for (int i = 0; i < randomList.size(); i++) {
            res.add(randomList.get(i) - formerInteger);
            formerInteger = randomList.get(i);
        }

        // 最后一个
        res.add(totalAmount - formerInteger);

        return  res;
    }
}
