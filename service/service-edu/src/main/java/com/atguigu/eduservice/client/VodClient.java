package com.atguigu.eduservice.client;

import com.atguigu.commonutils.R;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Author: shuai
 * Date: 2021/7/16
 * desc:
 */
@FeignClient(name = "service-vod",fallback = VodFileDegradeFeignClient.class)
@Component
public interface VodClient {
    @DeleteMapping(value = "/eduvod/video/delete/{videoId}")
    R removeVideo(@PathVariable("videoId") String videoId);

    @DeleteMapping("/eduvod/video/delete-batch")
    R removeVideoList(@RequestParam("videoIdList") List<String> videoIdList);
}
