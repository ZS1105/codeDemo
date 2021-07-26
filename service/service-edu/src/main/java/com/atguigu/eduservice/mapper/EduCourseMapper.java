package com.atguigu.eduservice.mapper;

import com.atguigu.eduservice.entity.course.CourseDetailFrontVo;
import com.atguigu.eduservice.entity.course.CoursePublishVo;
import com.atguigu.eduservice.entity.course.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author zs
 * @since 2021-07-12
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {
    CoursePublishVo getPublishCourseInfoById(String id);

    CourseDetailFrontVo getCourseDetailBycourseId(String courseId);

    Integer staticCourseNum(String day);

    Integer staticViewNum(String day);
}
