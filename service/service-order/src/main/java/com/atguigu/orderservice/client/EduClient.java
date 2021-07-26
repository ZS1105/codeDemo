package com.atguigu.orderservice.client;

import com.atguigu.commonutils.R;
import com.atguigu.commonutils.vo.CourseInfo;
import com.atguigu.commonutils.vo.TeacherVo;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Author: shuai
 * Date: 2021/7/22
 * desc:
 */
@FeignClient("service-edu")
@Component
public interface EduClient {
    //根据课程id查询课程信息
    @GetMapping("/eduservice/course/getCourseInfoOrder/{courseId}")
    public CourseInfo getCourseInfoOrder(@PathVariable String courseId);

    @GetMapping(value = "/eduservice/teacherfront/getTeacherInfoByTeacherId/{teacherId}")
    public TeacherVo getTeacherInfoByTeacherId(
            @ApiParam(name = "teacherId", value = "讲师ID", required = true)
            @PathVariable("teacherId") String teacherId);
}
