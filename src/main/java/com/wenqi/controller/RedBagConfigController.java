package com.wenqi.controller;


import com.wenqi.entity.BaseResponse;
import com.wenqi.entity.RedBagAllocateDetail;
import com.wenqi.entity.RedBagConfig;
import com.wenqi.service.RedBagAllocateDetailService;
import com.wenqi.service.RedBagAllocateFactory;
import com.wenqi.service.RedBagAllocateI;
import com.wenqi.service.RedBagConfigService;
import com.wenqi.service.convert.RedBagConvert;
import com.wenqi.vo.RedBagConfigRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
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
    @Transactional
    public BaseResponse<String> delete(@RequestBody @Valid RedBagConfigRequest redBagConfigRequest) {
        
        redBagConfigService.removeById(redBagConfigRequest.getId());
        redBagAllocateDetailService.removeByMap(new HashMap<String, Object>() {{
           put("red_bag_config_id", redBagConfigRequest.getId());
        }});

        return BaseResponse.success("删除成功");
    }
}
