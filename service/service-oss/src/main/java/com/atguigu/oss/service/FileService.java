package com.atguigu.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Author: shuai
 * Date: 2021/7/11
 * desc:
 */

public interface FileService {
    String uploadAvatar(MultipartFile file);

    void deleteAvatar(String id);
}
