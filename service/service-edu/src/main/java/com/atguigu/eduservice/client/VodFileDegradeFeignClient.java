package com.atguigu.eduservice.client;

import com.atguigu.commonutils.R;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Author: shuai
 * Date: 2021/7/16
 * desc:
 */
@Component
public class VodFileDegradeFeignClient implements VodClient {
    @Override
    public R removeVideo(String videoId) {
        return R.error().message("time out");
    }
    @Override
    public R removeVideoList(List<String> videoIdList) {
        return R.error().message("time out");
    }
}