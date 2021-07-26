package com.atguigu.serviceVod.controller;

import com.atguigu.commonutils.R;
import com.atguigu.serviceVod.service.VideoService;
import com.atguigu.serviceVod.util.AliyunVodSDKUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Author: shuai
 * Date: 2021/7/15
 * desc:
 */
@Api(description="阿里云视频点播微服务")
@CrossOrigin //跨域
@RestController
@RequestMapping("/eduvod/video")
public class VideoController {
    @Autowired
    private VideoService videoService;

    @PostMapping("/upload")
    public R uploadVideo(
            @ApiParam(name = "file", value = "文件", required = true)
            @RequestParam("file") MultipartFile file) throws Exception {
        String videoId = videoService.uploadVideo(file);
        return R.ok().message("视频上传成功").data("item", videoId);
    }

    @DeleteMapping("/delete/{videoId}")
    public R removeVideo(@ApiParam(name = "videoId", value = "云端视频id", required = true)
            @PathVariable String videoId){
        videoService.deleteVideo(videoId);
        return R.ok();
    }

    @DeleteMapping("delete-batch")
    public R removeVideoList(
            @ApiParam(name = "videoIdList", value = "云端视频id", required = true)
            @RequestParam("videoIdList") List<String> videoIdList){

        videoService.removeVideoList(videoIdList);
        return R.ok().message("视频删除成功");
    }

    @GetMapping("getPlayAuth/{videoId}")
    public R getVideoPlayAuth(
            @ApiParam(name = "videoId", value = "云端视频id", required = true)
            @PathVariable("videoId") String videoId) {

        String videoPlayAuth = AliyunVodSDKUtils.getVideoPlayAuth(videoId);
        System.out.println("getVideoPlayAuth" + videoPlayAuth);
        //返回结果
        if (!StringUtils.isEmpty(videoPlayAuth)){
            return R.ok().message("获取凭证成功").data("videoPlayAuth", videoPlayAuth);
        } else {
            return R.error().message("获取凭证失败");
        }

    }
}
