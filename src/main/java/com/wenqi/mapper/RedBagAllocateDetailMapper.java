package com.wenqi.mapper;

import com.wenqi.entity.RedBagAllocateDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 * 红包分配明细 Mapper 接口
 * </p>
 *
 * @author wuwenqi
 * @since 2022-06-05
 */
@Mapper
public interface RedBagAllocateDetailMapper extends BaseMapper<RedBagAllocateDetail> {
    @Update(value = "update red_bag_allocate_detail set status = 2, receiver = #{receiver}, receive_time = #{dateTime}, update_time = #{dateTime} where red_bag_config_id = #{redBagConfigId} and status = 1 limit 1")
    int grabOne(@Param("redBagConfigId") Long redBagConfigId, @Param("receiver") String receiver, @Param("dateTime") Long dateTime);
}
