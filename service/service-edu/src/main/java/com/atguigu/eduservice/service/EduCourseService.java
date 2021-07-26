package com.atguigu.eduservice.service;

import com.atguigu.commonutils.vo.CourseInfo;
import com.atguigu.eduservice.entity.course.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author zs
 * @since 2021-07-12
 */
public interface EduCourseService extends IService<EduCourse> {

    String addCourseInfo(CourseInfoVo courseInfoForm);
    CourseInfoVo getCourseInfoFormById(String id);
    void updateCourseInfo(CourseInfoVo courseInfoForm);
    CoursePublishVo getPublishCourseInfoById(String id);

    boolean PublishCourseInfoById(String id);

    void pageQuery(Page<EduCourse> pageParam, CourseQuery courseQuery);

    boolean removeCourseById(String id);

    List<EduCourse> getCoursesByTeacherId(String id);

    Map<String, Object> getCourseInfoPage(Page<EduCourse> pageParam, CourseFrontVo courseQuery);

    CourseDetailFrontVo getCourseDetailBycourseId(String courseId);


    Integer staticCourseNum(String day);

    Integer staticViewNum(String day);

}
