package com.atguigu.ucenterservice.controller;


import com.atguigu.commonutils.JwtUtils;
import com.atguigu.commonutils.vo.UserInfo;
import com.atguigu.ucenterservice.entity.Vo.LoginInfoVo ;
import com.atguigu.commonutils.R;
import com.atguigu.servicebase.exception.GuliException;
import com.atguigu.ucenterservice.entity.Vo.LoginVo;
import com.atguigu.ucenterservice.entity.Vo.RegisterVo;
import com.atguigu.ucenterservice.service.UcenterMemberService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author zs
 * @since 2021-07-19
 */
@RestController
@RequestMapping("/ucenterservice/member")
// @CrossOrigin
public class UcenterMemberController {
    @Autowired
    private UcenterMemberService memberService;

    @ApiOperation(value = "会员登录")
    @PostMapping("/login")
    public R login(@RequestBody LoginVo loginVo) {
        String token = memberService.login(loginVo);
        return R.ok().data("token", token);
    }

    @ApiOperation(value = "会员注册")
    @PostMapping("/register")
    public R register(@RequestBody RegisterVo registerVo){
        memberService.register(registerVo);
        return R.ok();
    }

    @ApiOperation(value = "根据token获取登录信息")
    @GetMapping("/getLoginInfo")
    public R getLoginInfo(HttpServletRequest request){
        try {
            String memberId = JwtUtils.getMemberIdByJwtToken(request);
            System.out.println(memberId);
            LoginInfoVo loginInfoVo = memberService.getLoginInfo(memberId);
            return R.ok().data("item", loginInfoVo);
        }catch (Exception e){
            e.printStackTrace();
            throw new GuliException(20001,"获取信息失败");
        }
    }

    @ApiOperation(value = "根据id获取h用户信息")
    @GetMapping("/getMemberInfo/{id}")
    public UserInfo getMemberInfo(@PathVariable("id") String memberId){
        LoginInfoVo loginInfoVo = memberService.getLoginInfo(memberId);
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(loginInfoVo,userInfo);
        return userInfo;
    }

    @ApiOperation(value = "根据day获取统计信息")
    @GetMapping(value = "staticRegisterNum/{day}")
    public R staticRegisterNumByDay(@PathVariable String day){
        Integer count = memberService.staticRegisterNumByDay(day);
        return R.ok().data("count", count);
    }

    @ApiOperation(value = "根据day获取统计信息")
    @GetMapping(value = "staticLoginrNum/{day}")
    public R staticLoginrNum(@PathVariable String day){
        Integer count = memberService.staticLoginrNum(day);
        return R.ok().data("count", count);
    }
}

