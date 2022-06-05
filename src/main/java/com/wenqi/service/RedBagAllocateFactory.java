package com.wenqi.service;

import com.wenqi.controller.exception.ClientException;
import com.wenqi.entity.RedBagStrategyEnum;
import com.wenqi.utils.EnumUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * RedBagAllocateFactory
 *
 * @author wuwenqi04
 * @classname：RedBagAllocateFactory
 * @date 2022/06/05
 */

@Service
public class RedBagAllocateFactory {
    @Resource
    private Map<String, RedBagAllocateI> redBagAllocateStrategyMap;

    public RedBagAllocateI getRedBagAllocateStrategy(Integer type) {
        try {
            RedBagStrategyEnum redBagStrategyEnum = EnumUtils.castValueToEnum(type, RedBagStrategyEnum.class);
            String strategyName = redBagStrategyEnum.getStrategyName();
            if (null != redBagAllocateStrategyMap && redBagAllocateStrategyMap.containsKey(strategyName)) {

                return redBagAllocateStrategyMap.get(strategyName);
            }
        } catch (Exception ex) {
            throw new ClientException("不存对应的策略,type=" + type);
        }

        throw new ClientException("不存对应的策略,type=" + type);
    }
}
