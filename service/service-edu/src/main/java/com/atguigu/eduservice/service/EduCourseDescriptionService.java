package com.atguigu.eduservice.service;

import com.atguigu.eduservice.entity.course.EduCourseDescription;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程简介 服务类
 * </p>
 *
 * @author zs
 * @since 2021-07-12
 */
public interface EduCourseDescriptionService extends IService<EduCourseDescription> {


    void deleteByCourseId(String courseId);
}
