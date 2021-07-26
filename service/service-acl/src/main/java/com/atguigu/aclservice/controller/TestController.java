package com.atguigu.aclservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: shuai
 * Date: 2021/7/26
 * desc:
 */
@RestController
@RequestMapping("/xx")
public class TestController {
    @GetMapping("yy")
    public String test(){
        System.out.println("xx");
        return "ok";
    }
}
