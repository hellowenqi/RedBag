package com.wenqi.service.impl;

import com.wenqi.utils.json.JsonUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;


public class NormalRedBagAllocateStrategyTest {

    @Test
    public void allocate() {
        NormalRedBagAllocateStrategy normalRedBagAllocateStrategy = new NormalRedBagAllocateStrategy();
        List<Integer> res = normalRedBagAllocateStrategy.allocate(10, 9);
        Assert.assertEquals(1, (int)res.get(0));
        Assert.assertEquals(2, (int)res.get(res.size() - 1));
    }
}