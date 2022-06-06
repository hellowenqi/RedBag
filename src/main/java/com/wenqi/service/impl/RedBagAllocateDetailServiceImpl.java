package com.wenqi.service.impl;

import com.wenqi.controller.exception.ClientException;
import com.wenqi.controller.exception.ServerException;
import com.wenqi.entity.BaseResponse;
import com.wenqi.entity.RedBagAllocateDetail;
import com.wenqi.entity.RedBagAllocateDetailStatusEnum;
import com.wenqi.entity.RedBagConfig;
import com.wenqi.mapper.RedBagAllocateDetailMapper;
import com.wenqi.mapper.RedBagConfigMapper;
import com.wenqi.service.RedBagAllocateDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wenqi.utils.json.JsonUtils;
import com.wenqi.vo.GrabRedBagRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 红包分配明细 服务实现类
 * </p>
 *
 * @author wuwenqi
 * @since 2022-06-05
 */
@Service
public class RedBagAllocateDetailServiceImpl extends ServiceImpl<RedBagAllocateDetailMapper, RedBagAllocateDetail> implements RedBagAllocateDetailService {
    @Resource
    private RedBagAllocateDetailMapper redBagAllocateDetailMapper;
    @Resource
    private RedBagConfigMapper redBagConfigMapper;

    @Transactional
    public BaseResponse<String> grabOne(GrabRedBagRequest grabRedBagRequest) {

        Long dateTime = new Date().getTime();
        // 只能领一个红包性由唯一主键保证
        int grabNum = 0;
        try {
            grabNum = redBagAllocateDetailMapper.grabOne(grabRedBagRequest.getId(), grabRedBagRequest.getUserName(), dateTime);
        } catch (Exception ex) {
            throw new ServerException("只能抢一个红包哟！");
        }
        if (grabNum == 0) {
            throw new ServerException("未抢到红包");
        }
        List<RedBagAllocateDetail> redBagAllocateDetailList = redBagAllocateDetailMapper.selectByMap(new HashMap<String, Object>(){{
            put("red_bag_config_id", grabRedBagRequest.getId());
            put("receiver", grabRedBagRequest.getUserName());
        }});
        if (CollectionUtils.isEmpty(redBagAllocateDetailList)) {
            throw new ServerException("数据异常, 红包Id:" + grabRedBagRequest.getId());
        }
        RedBagAllocateDetail redBagAllocateDetail = redBagAllocateDetailList.get(0);

        redBagConfigMapper.decreaseOne(grabRedBagRequest.getId(), redBagAllocateDetail.getAmount(), dateTime);
        return BaseResponse.success(String.format("恭喜%s成功领取红包，金额=%d", grabRedBagRequest.getUserName(), redBagAllocateDetail.getAmount()));
    }
}
