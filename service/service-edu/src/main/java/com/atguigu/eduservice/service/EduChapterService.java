package com.atguigu.eduservice.service;

import com.atguigu.eduservice.entity.chapter.EduChapter;
import com.atguigu.eduservice.entity.chapter.ChapterVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author zs
 * @since 2021-07-12
 */
public interface EduChapterService extends IService<EduChapter> {
    List<ChapterVo> chapterList(String courseId);

    boolean removeChapterById(String id);

    boolean removeByCourseId(String courseId);
}
