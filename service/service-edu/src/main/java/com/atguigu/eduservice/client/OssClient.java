package com.atguigu.eduservice.client;

import com.atguigu.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Author: shuai
 * Date: 2021/7/16
 * desc:
 */
@FeignClient("service-oss")
@Component
public interface OssClient {
    @PostMapping("/eduoss/fileoss/delete")
    R deleteOssFile(@RequestParam("objName") String objName);
}
