package com.wenqi.mapper;

import com.wenqi.entity.RedBagConfig;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * 发红包配置详情 Mapper 接口
 *
 * @author wuwenqi
 * @since 2022-06-05
 */
@Mapper
public interface RedBagConfigMapper extends BaseMapper<RedBagConfig> {
    @Select(value = "select * t from red_bag_config where id = #{id} for update")
    RedBagConfig getByIdFroUpdate(@Param("id") Long id);

    @Update(value = "update red_bag_config set used_num = used_num + 1, used_amount = used_amount + #{amount}, update_time = #{dateTime} where id = #{id} and #{dateTime} < expire_time")
    int decreaseOne(@Param("id") Long id, @Param("amount") Integer amount,  @Param("dateTime") Long dateTime);
}
