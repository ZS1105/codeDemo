package com.atguigu.eduservice.controller;

import com.atguigu.commonutils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import org.springframework.web.bind.annotation.*;

/**
 * Author: shuai
 * Date: 2021/7/10
 * desc:
 */
@Api(description = "登录信息")
@RestController
@RequestMapping("/eduservice/user")
// @CrossOrigin  // 解决跨域问题
public class EduLoginController {
    @PostMapping("/login")
    public R login(){
        return R.ok().data("token","admin");
    }

    @GetMapping("/info")
    public R info(){
        return R.ok().data("roles","admin").data("name","admin").data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }
}
