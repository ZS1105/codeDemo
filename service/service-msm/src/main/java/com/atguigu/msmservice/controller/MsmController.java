package com.atguigu.msmservice.controller;

import com.atguigu.commonutils.R;
import com.atguigu.commonutils.RandomUtil;
import com.atguigu.msmservice.service.MsmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Author: shuai
 * Date: 2021/7/19
 * desc:
 */
@RestController
@RequestMapping("/msmservice/msm")
// @CrossOrigin
public class MsmController {
    @Autowired
    private MsmService msmService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @GetMapping(value = "/send/{phone}")
    public R sendCode(@PathVariable String phone) {
        String code = redisTemplate.opsForValue().get(phone);
        if(!StringUtils.isEmpty(code)) return R.ok();

        code = RandomUtil.getSixBitRandom();
        Map<String,Object> param = new HashMap<>();
        param.put("code", code);

        boolean isSend = msmService.send(phone, param);
        if(isSend) {
            redisTemplate.opsForValue().set(phone, code,10, TimeUnit.MINUTES);
            return R.ok();
        } else {
            return R.error().message("发送短信失败");
        }
    }
}
