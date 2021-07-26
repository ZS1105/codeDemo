package com.atguigu.serviceVod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Author: shuai
 * Date: 2021/7/15
 * desc:
 */
public interface VideoService {
    String uploadVideo(MultipartFile file);

    void deleteVideo(String videoId);

    void removeVideoList(List<String> videoIdList);
}
