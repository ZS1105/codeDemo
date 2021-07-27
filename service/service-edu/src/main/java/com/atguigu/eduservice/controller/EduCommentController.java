package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.JwtUtils;
import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.comment.EduComment;
import com.atguigu.eduservice.service.EduCommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 * 评论 前端控制器
 * </p>
 *
 * @author zs
 * @since 2021-07-12
 */
@Api(description = "评论")
@RestController
@RequestMapping("/eduservice/comment")
// @CrossOrigin
public class EduCommentController {
    @Autowired
    private EduCommentService commentService;


    //根据课程id查询评论列表
    @ApiOperation(value = "评论分页列表")
    @GetMapping("/getCommentPage/{page}/{limit}")
    public R getCommentPage(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            @ApiParam(name = "courseQuery", value = "查询对象", required = false)
                    String courseId) {
        Map<String, Object> map = commentService.getCommentPage(page,limit,courseId);

        return R.ok().data(map);
    }

    @ApiOperation(value = "添加评论")
    @PostMapping("/addComment")
    public R addComment(@RequestBody EduComment comment, HttpServletRequest request) {
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        boolean b = commentService.addComment(comment, memberId);
        if (b){
            return R.ok();
        }
        return R.error();
    }
}

