package com.atguigu.orderservice.client;

import com.atguigu.commonutils.vo.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Author: shuai
 * Date: 2021/7/22
 * desc:
 */
@FeignClient("service-ucenter")
@Component
public interface UcenterClient {
    @GetMapping("/ucenterservice/member//getMemberInfo/{id}")
    public UserInfo getMemberInfo(@PathVariable("id") String memberId);
}
