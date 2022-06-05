package com.wenqi.service.impl;

import com.wenqi.utils.json.JsonUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class RandomRedBagAllocateStrategyTest {
    @Test
    public void testRandom() {

        RandomRedBagAllocateStrategy randomRedBagAllocateStrategy = new RandomRedBagAllocateStrategy();
        List<Integer> res;
        Integer count = 100;
        while (--count >= 0) {
            res = randomRedBagAllocateStrategy.allocate(100, 10);
            Assert.assertEquals(100, res.stream().mapToInt(Integer::new).sum());
        }
    }

}