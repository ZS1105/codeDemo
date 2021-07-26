package com.atguigu.oss.controller;

import com.atguigu.commonutils.R;
import com.atguigu.oss.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Author: shuai
 * Date: 2021/7/11
 * desc:
 */

@RestController
@CrossOrigin
@RequestMapping("/eduoss/fileoss")
public class FileController {
    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public R uploadOssFile(@RequestParam("file") MultipartFile files){
        String url = fileService.uploadAvatar(files);
        return R.ok().message("文件上传成功").data("url",url);
    }

    @PostMapping("/delete")
    public R deleteOssFile(@RequestParam("objName") String objName){
        fileService.deleteAvatar(objName);
        return R.ok();
    }
}
