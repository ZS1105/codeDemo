package com.atguigu.staservice.client;

import com.atguigu.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Author: shuai
 * Date: 2021/7/23
 * desc:
 */
@Component
@FeignClient("service-ucenter")
public interface UcenterClient {
    @GetMapping(value = "/ucenterservice/member/staticRegisterNum/{day}")
    public R staticRegisterNumByDay(@PathVariable("day") String day);

    @GetMapping(value = "/ucenterservice/member/staticLoginrNum/{day}")
    public R staticLoginrNum(@PathVariable("day") String day);
}
