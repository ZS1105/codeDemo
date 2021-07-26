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
@FeignClient("service-edu")
public interface EduClient {
    @GetMapping("/eduservice/course/staticCourseNum/{day}")
    public R staticCourseNum(@PathVariable String day);

    @GetMapping("/eduservice/course/staticViewNum/{day}")
    public R staticViewNum(@PathVariable String day);
}
