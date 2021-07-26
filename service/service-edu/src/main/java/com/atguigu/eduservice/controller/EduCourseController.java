package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.commonutils.vo.CourseInfo;
import com.atguigu.eduservice.entity.course.CourseInfoVo;
import com.atguigu.eduservice.entity.course.CoursePublishVo;
import com.atguigu.eduservice.entity.course.CourseQuery;
import com.atguigu.eduservice.entity.course.EduCourse;
import com.atguigu.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.poi.ss.formula.functions.Count;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author zs
 * @since 2021-07-12
 */
@Api(description = "课程信息")
@RestController
@RequestMapping("/eduservice/course")
@CrossOrigin
public class EduCourseController {
    @Autowired
    private EduCourseService courseService;


    @PostMapping("/addCourseInfo")
    public R addCourseInfo(@RequestBody CourseInfoVo courseInfoForm){
        String courseId = courseService.addCourseInfo(courseInfoForm);

        if (!StringUtils.isEmpty(courseId)){
            return R.ok().data("courseId", courseId);
        } else {
            return R.error().message("保存失败");
        }
    }

    @ApiOperation(value = "根据ID查询课程")
    @GetMapping("/getCourseInfo/{id}")
    public R getCourseInfoById(
            @ApiParam(name = "id", value = "课程ID", required = true)
            @PathVariable String id){
        CourseInfoVo courseInfoForm = courseService.getCourseInfoFormById(id);
        return R.ok().data("item", courseInfoForm);
    }

    @ApiOperation(value = "更新课程")
    @PutMapping("/updateCourseInfo")
    public R updateCourseInfo(@ApiParam(name = "CourseInfoForm", value = "课程基本信息", required = true)
                                  @RequestBody CourseInfoVo courseInfoForm){
        courseService.updateCourseInfo(courseInfoForm);
        return R.ok();
    }

    @ApiOperation(value = "根据ID获取课程发布信息")
    @GetMapping("/getPublishCourseInfo/{id}")
    public R getCoursePublishVoById(
            @ApiParam(name = "id", value = "课程ID", required = true)
            @PathVariable String id){
        CoursePublishVo courseInfoForm = courseService.getPublishCourseInfoById(id);
        return R.ok().data("item", courseInfoForm);
    }

    @ApiOperation(value = "根据id发布课程")
    @GetMapping("/publishCourse/{id}")
    public R publishCourseInfo(
            @ApiParam(name = "id", value = "课程ID", required = true)
            @PathVariable String id){
         boolean res = courseService.PublishCourseInfoById(id);
        if (res){
            return R.ok();
        } else {
            return R.error().message("发布课程失败");
        }
    }

    @ApiOperation(value = "分页课程列表")
    @GetMapping("/pageCourse/{page}/{limit}")
    public R pageQuery(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            @ApiParam(name = "courseQuery", value = "查询对象", required = false)
             CourseQuery courseQuery){
        Page<EduCourse> pageParam = new Page<>(page, limit);
        courseService.pageQuery(pageParam, courseQuery);
        List<EduCourse> records = pageParam.getRecords();
        long total = pageParam.getTotal();
        return R.ok().data("total", total).data("rows", records);
    }

    @ApiOperation(value = "根据ID删除课程")
    @DeleteMapping("/deleteCourse/{id}")
    public R removeCourseById(
            @ApiParam(name = "id", value = "课程ID", required = true)
            @PathVariable String id){
        boolean result = courseService.removeCourseById(id);
        if(result){
            return R.ok();
        }else{
            return R.error().message("删除失败");
        }
    }

    //根据课程id查询课程信息
    @GetMapping("getCourseInfoOrder/{courseId}")
    public CourseInfo getCourseInfoOrder(@PathVariable String courseId){
        CourseInfoVo courseInfoVo = courseService.getCourseInfoFormById(courseId);
        CourseInfo courseInfo = new CourseInfo();
        BeanUtils.copyProperties(courseInfoVo,courseInfo);

        return courseInfo;
    }

    @GetMapping("staticCourseNum/{day}")
    public R staticCourseNum(@PathVariable String day){
        Integer count = courseService.staticCourseNum(day);
        return R.ok().data("count", count);
    }

    @GetMapping("staticViewNum/{day}")
    public R staticViewNum(@PathVariable String day){
        Integer count = courseService.staticViewNum(day);
        return R.ok().data("count", count);
    }
}

