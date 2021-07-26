package com.atguigu.ucenterservice.mapper;

import com.atguigu.ucenterservice.entity.UcenterMember;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author zs
 * @since 2021-07-19
 */
public interface UcenterMemberMapper extends BaseMapper<UcenterMember> {

    Integer getRegisterNumByDay(String day);

    Integer staticLoginrNum(String day);
}
