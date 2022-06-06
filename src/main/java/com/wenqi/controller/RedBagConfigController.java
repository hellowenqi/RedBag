package com.wenqi.controller;


import com.mysql.cj.xdevapi.Client;
import com.wenqi.controller.exception.ClientException;
import com.wenqi.controller.exception.ServerException;
import com.wenqi.entity.BaseResponse;
import com.wenqi.entity.RedBagAllocateDetail;
import com.wenqi.entity.RedBagAllocateDetailStatusEnum;
import com.wenqi.entity.RedBagConfig;
import com.wenqi.service.RedBagAllocateDetailService;
import com.wenqi.service.RedBagAllocateFactory;
import com.wenqi.service.RedBagAllocateI;
import com.wenqi.service.RedBagConfigService;
import com.wenqi.service.convert.RedBagConvert;
import com.wenqi.vo.DeleteRequest;
import com.wenqi.vo.GrabRedBagRequest;
import com.wenqi.vo.RedBagConfigRequest;
import com.wenqi.vo.RedBagResponse;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 发红包配置详情 前端控制器
 * </p>
 *
 * @author wuwenqi
 * @since 2022-06-05
 */
@RestController
@RequestMapping("/red-bag-config")
public class RedBagConfigController {
    @Resource
    private RedBagAllocateFactory redBagAllocateFactory;
    @Resource
    private RedBagConfigService redBagConfigService;
    @Resource
    private RedBagAllocateDetailService redBagAllocateDetailService;

    @PostMapping("/add")
    @Transactional
    public BaseResponse<Long> add(@RequestBody @Valid RedBagConfigRequest redBagConfigRequest) {

        // 生成红包list
        RedBagAllocateI redBagAllocateI = redBagAllocateFactory.getRedBagAllocateStrategy(redBagConfigRequest.getType());
        List<Integer> amountList =  redBagAllocateI.allocate(redBagConfigRequest.getTotalAmount(), redBagConfigRequest.getTotalNum());
        RedBagConfig redBagConfig = RedBagConvert.toRedBagConfig(redBagConfigRequest);
        redBagConfigService.save(redBagConfig);
        List<RedBagAllocateDetail> redBagAllocateDetailList = RedBagConvert.toRedBagAllocateDetailList(amountList, redBagConfig);
        redBagAllocateDetailService.saveBatch(redBagAllocateDetailList);

        return BaseResponse.success(redBagConfig.getId());
    }

    @PostMapping("/delete")
    public BaseResponse<String> delete(@RequestBody @Valid DeleteRequest deleteRequest) {
        
        redBagConfigService.removeById(deleteRequest.getId());
        redBagAllocateDetailService.removeByMap(new HashMap<String, Object>() {{
           put("red_bag_config_id", deleteRequest.getId());
        }});

        return BaseResponse.success("删除成功");
    }

    @GetMapping("/query")
    public BaseResponse<RedBagResponse> query(@RequestParam("id") Long id) {
        RedBagConfig redBagConfig = redBagConfigService.getById(id);
        if (null == redBagConfig) {
            throw new ClientException("红包不存在");
        }
        List<RedBagAllocateDetail> redBagAllocateDetailList = redBagAllocateDetailService.listByMap(new HashMap<String, Object>() {{
            put("red_bag_config_id", id);
            put("status", RedBagAllocateDetailStatusEnum.ALLOCATED.getValue());
        }});

        return BaseResponse.success(RedBagConvert.toRedBagConfigResponse(redBagConfig, redBagAllocateDetailList));
    }

    @PostMapping("/grab")
    public BaseResponse<String> grab(@RequestBody @Valid GrabRedBagRequest grabRedBagRequest) {
        // 先简单查询，快速失败
        RedBagConfig redBagConfig = redBagConfigService.getById(grabRedBagRequest.getId());
        if (null == redBagConfig) {
            throw new ClientException("红包不存在");
        }
        if (redBagConfig.getUsedNum() >= redBagConfig.getTotalNum() || redBagConfig.getUsedAmount() >= redBagConfig.getTotalAmount()) {
            throw new ServerException("红包已领完");
        }
        if (redBagConfig.getExpireTime() < new Date().getTime()) {
            throw new ServerException("红包已过期，不能领取了");
        }

        return redBagAllocateDetailService.grabOne(grabRedBagRequest);
    }


    @PostMapping("/check")
    public BaseResponse<RedBagResponse> check(@RequestParam("id") Long id) {

        throw new ClientException("暂不支持，敬请期待！");
    }



}
