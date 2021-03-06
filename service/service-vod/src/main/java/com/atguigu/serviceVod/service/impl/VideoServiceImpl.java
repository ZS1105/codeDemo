package com.atguigu.serviceVod.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.DeleteVideoResponse;
import com.atguigu.commonutils.ConstantUtil;
import com.atguigu.serviceVod.service.VideoService;
import com.atguigu.serviceVod.util.AliyunVodSDKUtils;
import com.atguigu.servicebase.exception.GuliException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Author: shuai
 * Date: 2021/7/15
 * desc:
 */
@Service
public class VideoServiceImpl implements VideoService {
    @Override
    public String uploadVideo(MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            String originalFilename = file.getOriginalFilename();
            String title = originalFilename.substring(0, originalFilename.lastIndexOf("."));

            UploadStreamRequest request = new UploadStreamRequest(
                    ConstantUtil.ACCESS_KEY_ID,
                    ConstantUtil.ACCESS_KEY_SECRET,
                    title, originalFilename, inputStream);

            UploadVideoImpl uploader = new UploadVideoImpl();
            System.out.println("===============");
            UploadStreamResponse response = uploader.uploadStream(request);

            //如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。
            // 其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因

            String videoId = response.getVideoId();
            System.out.println(videoId);
            if (!response.isSuccess()) {
                String errorMessage = "阿里云上传错误：" + "code：" + response.getCode();
                if(StringUtils.isEmpty(videoId)){
                    throw new GuliException(20001, errorMessage);
                }
            }

            return videoId;
        } catch (IOException e) {
            throw new GuliException(20001, "guli vod 服务上传失败");
        }
    }

    @Override
    public void deleteVideo(String videoId) {
        AliyunVodSDKUtils.deleteVideo(videoId);
    }

    @Override
    public void removeVideoList(List<String> videoIdList) {
        String strId = org.apache.commons.lang.StringUtils.join(videoIdList.toArray(),",");
        AliyunVodSDKUtils.deleteVideo(strId);
    }


}
