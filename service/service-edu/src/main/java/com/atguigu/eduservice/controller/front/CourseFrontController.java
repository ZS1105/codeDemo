package com.atguigu.eduservice.controller.front;

import com.atguigu.commonutils.JwtUtils;
import com.atguigu.commonutils.R;
import com.atguigu.eduservice.client.OrderClient;
import com.atguigu.eduservice.entity.chapter.ChapterVo;
import com.atguigu.eduservice.entity.course.CourseDetailFrontVo;
import com.atguigu.eduservice.entity.course.CourseFrontVo;
import com.atguigu.eduservice.entity.course.EduCourse;
import com.atguigu.eduservice.service.EduChapterService;
import com.atguigu.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Author: shuai
 * Date: 2021/7/21
 * desc:
 */
@Api(description = "前端课程")
@RestController
@RequestMapping("/eduservice/coursefront")
// @CrossOrigin
public class CourseFrontController {
    @Autowired
    private EduCourseService courseService;
    @Autowired
    private EduChapterService chapterService;
    @Autowired
    private OrderClient orderClient;

    @GetMapping("/courses")
    public R getCourses(){
        QueryWrapper<EduCourse> courseQueryWrapper = new QueryWrapper<>();
        courseQueryWrapper.orderByDesc("id");
        courseQueryWrapper.last("limit 8");
        List<EduCourse> courses = courseService.list(courseQueryWrapper);

        return R.ok().data("courses",courses);
    }

    @ApiOperation(value = "分页课程列表")
    @PostMapping(value = "/getCourseInfoPage/{page}/{limit}")
    public R getCourseInfoPage(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            @ApiParam(name = "courseQuery", value = "查询对象", required = false)
            @RequestBody(required = false) CourseFrontVo courseQuery){

        Page<EduCourse> pageParam = new Page<>(page, limit);
        Map<String, Object> map = courseService.getCourseInfoPage(pageParam, courseQuery);
        return R.ok().data(map);
    }

    @ApiOperation(value = "根据ID查询课程")
    @GetMapping(value = "/getCourseDetail/{courseId}")
    public R getCourseDetailBycourseId(
            @ApiParam(name = "courseId", value = "课程ID", required = true)
            @PathVariable String courseId,
            HttpServletRequest request) {
        //查询课程信息和讲师信息
        CourseDetailFrontVo courseDetailFrontVo = courseService.getCourseDetailBycourseId(courseId);
        //查询当前课程的章节信息
        List<ChapterVo> chapterVoList = chapterService.chapterList(courseId);
        //远程调用，判断课程是否被购买
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        boolean buyCourse = orderClient.isBuyCourse(courseId,memberId);
        return R.ok().data("course", courseDetailFrontVo).data("chapters", chapterVoList).data("isBuyCourse",buyCourse);
    }
}
