package com.wenqi.service;

import com.wenqi.entity.BaseResponse;
import com.wenqi.entity.RedBagAllocateDetail;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wenqi.vo.GrabRedBagRequest;

/**
 * <p>
 * 红包分配明细 服务类
 * </p>
 *
 * @author wuwenqi
 * @since 2022-06-05
 */
public interface RedBagAllocateDetailService extends IService<RedBagAllocateDetail> {
     BaseResponse<String> grabOne(GrabRedBagRequest grabRedBagRequest);
}
