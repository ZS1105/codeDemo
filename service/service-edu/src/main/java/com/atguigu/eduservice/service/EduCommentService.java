package com.atguigu.eduservice.service;

import com.atguigu.eduservice.entity.comment.EduComment;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 * 评论 服务类
 * </p>
 *
 * @author zs
 * @since 2021-07-12
 */
public interface EduCommentService extends IService<EduComment> {

    Map<String, Object> getCommentPage(Long page, Long limit, String courseId);

    public boolean addComment(EduComment comment, String memberId);
}
