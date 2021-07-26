package com.atguigu.eduservice.service;

import com.atguigu.eduservice.entity.chapter.EduVideo;
import com.atguigu.eduservice.entity.chapter.VideoInfoVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author zs
 * @since 2021-07-12
 */
public interface EduVideoService extends IService<EduVideo> {

    boolean existVideoByChapterId(String id);

    void saveVideoInfo(VideoInfoVo videoInfoForm);

    VideoInfoVo getVideoInfoById(String id);

    void updateVideoInfoById(VideoInfoVo videoInfoForm);

    boolean deleteVideoInfoById(String id);

    boolean removeByCourseId(String courseId);

}
