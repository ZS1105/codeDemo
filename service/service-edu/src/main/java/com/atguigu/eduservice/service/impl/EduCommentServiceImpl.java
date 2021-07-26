package com.atguigu.eduservice.service.impl;

import com.alibaba.fastjson.JSON;
import com.atguigu.commonutils.R;
import com.atguigu.commonutils.vo.UserInfo;
import com.atguigu.eduservice.client.UcenterClient;
import com.atguigu.eduservice.entity.comment.EduComment;
import com.atguigu.eduservice.entity.comment.LoginInfoVo;
import com.atguigu.eduservice.mapper.EduCommentMapper;
import com.atguigu.eduservice.service.EduCommentService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 评论 服务实现类
 * </p>
 *
 * @author zs
 * @since 2021-07-12
 */
@Service
public class EduCommentServiceImpl extends ServiceImpl<EduCommentMapper, EduComment> implements EduCommentService {
    @Autowired
    private UcenterClient ucenterClient;

    @Override
    public Map<String, Object> getCommentPage(Long page, Long limit, String courseId) {
        Page<EduComment> pageParam = new Page<>(page, limit);
        QueryWrapper<EduComment> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        baseMapper.selectPage(pageParam,wrapper);

        List<EduComment> commentList = pageParam.getRecords();
        Map<String, Object> map = new HashMap<>();
        map.put("items", commentList);
        map.put("current", pageParam.getCurrent());
        map.put("pages", pageParam.getPages());
        map.put("size", pageParam.getSize());
        map.put("total", pageParam.getTotal());
        map.put("hasNext", pageParam.hasNext());
        map.put("hasPrevious", pageParam.hasPrevious());

        return map;
    }

    @Override
    public boolean addComment(EduComment comment, String memberId) {
        UserInfo userInfo = ucenterClient.getMemberInfo(memberId);

        comment.setMemberId(userInfo.getId());
        comment.setNickname(userInfo.getNickname());
        comment.setAvatar(userInfo.getAvatar());
        int lines = baseMapper.insert(comment);
        if (lines > 0){
            return true;
        }
        return false;
    }
}
