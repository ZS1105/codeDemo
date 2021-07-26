package com.atguigu.ucenterservice.service;

import com.atguigu.ucenterservice.entity.UcenterMember;
import com.atguigu.ucenterservice.entity.Vo.LoginInfoVo ;
import com.atguigu.ucenterservice.entity.Vo.LoginVo;
import com.atguigu.ucenterservice.entity.Vo.RegisterVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author zs
 * @since 2021-07-19
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    String login(LoginVo loginVo);

    void register(RegisterVo registerVo);

    LoginInfoVo getLoginInfo(String memberId);

    UcenterMember getByOpenid(String openid);

    Integer staticRegisterNumByDay(String day);

    Integer staticLoginrNum(String day);
}
