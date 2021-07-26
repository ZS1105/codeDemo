package com.atguigu.serviceVod.util;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.DeleteVideoResponse;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.atguigu.commonutils.ConstantUtil;
import com.atguigu.servicebase.exception.GuliException;

/**
 * Author: shuai
 * Date: 2021/7/15
 * desc:
 */
public class AliyunVodSDKUtils {
    public static DefaultAcsClient initVodClient(String accessKeyId, String accessKey){
        String regionId = "cn-shanghai"; // 点播服务接入区域
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKey);
        DefaultAcsClient client = new DefaultAcsClient(profile);
        return client;
    }

    public static String getVideoPlayAuth(String videoId){
        //初始化客户端、请求对象和相应对象
        DefaultAcsClient client = AliyunVodSDKUtils.initVodClient(ConstantUtil.ACCESS_KEY_ID, ConstantUtil.ACCESS_KEY_SECRET);
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        GetVideoPlayAuthResponse response ;
        String playAuth = "";
        try {
            //设置请求参数
            request.setVideoId(videoId);
            //获取请求响应
            response = client.getAcsResponse(request);
            //输出请求结果
            //播放凭证
            playAuth = response.getPlayAuth();

        } catch (Exception e) {
            System.out.print("ErrorMessage : " + e.getLocalizedMessage());
        }

        return playAuth;
    }

    public static void deleteVideo(String videoId){
        try{
            DefaultAcsClient client = initVodClient(ConstantUtil.ACCESS_KEY_ID, ConstantUtil.ACCESS_KEY_SECRET);
            DeleteVideoRequest request = new DeleteVideoRequest();
            request.setVideoIds(videoId);
            DeleteVideoResponse response = client.getAcsResponse(request);
            System.out.print("RequestId = " + response.getRequestId() + "\n");
        }catch (ClientException e){
            throw new GuliException(20001, "视频删除失败");
        }
    }

}
