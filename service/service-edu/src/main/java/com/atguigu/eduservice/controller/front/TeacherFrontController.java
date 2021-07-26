package com.atguigu.eduservice.controller.front;

import com.atguigu.commonutils.R;
import com.atguigu.commonutils.vo.TeacherVo;
import com.atguigu.eduservice.entity.course.EduCourse;
import com.atguigu.eduservice.entity.teacher.EduTeacher;
import com.atguigu.eduservice.service.EduCourseService;
import com.atguigu.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Author: shuai
 * Date: 2021/7/21
 * desc:
 */
@Api(description = "前端讲师")
@RestController
@RequestMapping("/eduservice/teacherfront")
@CrossOrigin
public class TeacherFrontController {
    @Autowired
    private EduTeacherService teacherService;
    @Autowired
    private EduCourseService courseService;

    @GetMapping("/teachers")
    public R getTeachers(){
        QueryWrapper<EduTeacher> teacherQueryWrapper = new QueryWrapper<>();
        teacherQueryWrapper.orderByDesc("id");
        teacherQueryWrapper.last("limit 4");
        List<EduTeacher> teachers = teacherService.list(teacherQueryWrapper);

        return R.ok().data("teachers",teachers);
    }

    @ApiOperation(value = "分页讲师列表")
    @GetMapping(value = "/getTeacherPageList/{page}/{limit}")
    public R getTeacherPageList(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit){

        Page<EduTeacher> pageParam = new Page<>(page, limit);
        Map<String, Object> map = teacherService.getTeacherPageList(pageParam);
        return R.ok().data(map);
    }

    @ApiOperation(value = "根据讲师ID查询课程")
    @GetMapping(value = "/getTeacherDetailByTeacherId/{teacherId}")
    public R getTeacherDetailByTeacherId(
            @ApiParam(name = "teacherId", value = "讲师ID", required = true)
            @PathVariable String teacherId){

        //查询讲师信息
        EduTeacher teacher = teacherService.getById(teacherId);
        //根据讲师id查询这个讲师的课程列表
        List<EduCourse> courses = courseService.getCoursesByTeacherId(teacherId);
        return R.ok().data("teacher", teacher).data("courses", courses);
    }

    @ApiOperation(value = "根据讲师ID查询课程")
    @GetMapping(value = "/getTeacherInfoByTeacherId/{teacherId}")
    public TeacherVo getTeacherInfoByTeacherId(
            @ApiParam(name = "teacherId", value = "讲师ID", required = true)
            @PathVariable String teacherId){
        EduTeacher eduTeacher = teacherService.getById(teacherId);
        TeacherVo teacherVo = new TeacherVo();
        BeanUtils.copyProperties(eduTeacher,teacherVo);
        return teacherVo;
    }
}
