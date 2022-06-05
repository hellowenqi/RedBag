package com.wenqi.service.convert;

import com.wenqi.entity.RedBagAllocateDetail;
import com.wenqi.entity.RedBagAllocateDetailStatusEnum;
import com.wenqi.entity.RedBagConfig;
import com.wenqi.vo.RedBagConfigRequest;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * RedbagConvert
 *
 * @author wuwenqi04
 * @classname：RedbagConvert
 * @date 2022/06/05
 */

public class RedBagConvert {
    public static RedBagConfig toRedBagConfig(RedBagConfigRequest request) {
        RedBagConfig redBagConfig = new RedBagConfig();
        Long date = (new Date()).getTime();
        redBagConfig.setCreator(request.getCreator());
        redBagConfig.setTotalAmount(request.getTotalAmount());
        redBagConfig.setTotalNum(request.getTotalNum());
        redBagConfig.setUsedAmount(0);
        redBagConfig.setUsedNum(0);
        redBagConfig.setType(request.getType());
        redBagConfig.setExpireTime(request.getExpireTime());
        redBagConfig.setCreateTime(date);
        redBagConfig.setUpdateTime(date);

        return redBagConfig;
    }

    public static List<RedBagAllocateDetail> toRedBagAllocateDetailList (List<Integer> amountList, RedBagConfig redBagConfig) {
        List<RedBagAllocateDetail> res = new ArrayList<>();
        if (CollectionUtils.isEmpty(amountList)) {
            return res;
        }
        for (Integer singleAmount: amountList) {
            RedBagAllocateDetail detail = new RedBagAllocateDetail();
            detail.setAmount(singleAmount);
            detail.setCreateTime(redBagConfig.getCreateTime());
            detail.setUpdateTime(redBagConfig.getCreateTime());
            detail.setReceiver("");
            detail.setReceiveTime(0L);
            detail.setRedBagConfigId(redBagConfig.getId());
            detail.setStatus(RedBagAllocateDetailStatusEnum.NOT_ALLOCATE.getValue());
            res.add(detail);
        }

        return res;
    }

}
