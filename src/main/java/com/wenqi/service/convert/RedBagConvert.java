package com.wenqi.service.convert;

import com.wenqi.entity.RedBagAllocateDetail;
import com.wenqi.entity.RedBagAllocateDetailStatusEnum;
import com.wenqi.entity.RedBagConfig;
import com.wenqi.vo.RedBagAllocateDetailResponse;
import com.wenqi.vo.RedBagConfigRequest;
import com.wenqi.vo.RedBagResponse;
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
            detail.setReceiver(null);
            detail.setReceiveTime(0L);
            detail.setRedBagConfigId(redBagConfig.getId());
            detail.setStatus(RedBagAllocateDetailStatusEnum.NOT_ALLOCATE.getValue());
            res.add(detail);
        }

        return res;
    }


    public static RedBagResponse toRedBagConfigResponse(RedBagConfig redBagConfig, List<RedBagAllocateDetail> redBagAllocateDetailList) {
        RedBagResponse redBagResponse = new RedBagResponse();
        redBagResponse.setCreator(redBagConfig.getCreator());
        redBagResponse.setExpireTime(new Date(redBagConfig.getExpireTime().longValue()));
        redBagResponse.setCreateTime(new Date(redBagConfig.getCreateTime().longValue()));
        redBagResponse.setUpdateTime(new Date(redBagConfig.getUpdateTime().longValue()));
        redBagResponse.setId(redBagConfig.getId());
        redBagResponse.setTotalAmount(redBagConfig.getTotalAmount());
        redBagResponse.setUsedAmount(redBagConfig.getUsedAmount());
        redBagResponse.setTotalNum(redBagConfig.getTotalNum());
        redBagResponse.setUsedNum(redBagConfig.getUsedNum());
        List<RedBagAllocateDetailResponse> detailResponseList = new ArrayList<>();
        redBagResponse.setRedBagAllocateDetailResponseList(detailResponseList);
        if (!CollectionUtils.isEmpty(redBagAllocateDetailList)) {
            redBagAllocateDetailList.forEach(item -> {
                RedBagAllocateDetailResponse detailResponse = new RedBagAllocateDetailResponse();
                detailResponse.setAmount(item.getAmount());
                detailResponse.setId(item.getId());
                detailResponse.setReceiver(item.getReceiver());
                detailResponse.setReceiveTime(new Date(item.getReceiveTime().longValue()));
                detailResponseList.add(detailResponse);
            });
        }

        return redBagResponse;
    }

}
