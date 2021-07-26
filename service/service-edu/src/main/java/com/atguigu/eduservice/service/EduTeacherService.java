package com.atguigu.eduservice.service;

import com.atguigu.eduservice.entity.teacher.EduTeacher;
import com.atguigu.eduservice.entity.teacher.TeacherQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author zs
 * @since 2021-07-07
 */
public interface EduTeacherService extends IService<EduTeacher> {

    void pageQuery(Page<EduTeacher> pageParam, TeacherQuery teacherQuery);

    Map<String, Object> getTeacherPageList(Page<EduTeacher> pageParam);
}
