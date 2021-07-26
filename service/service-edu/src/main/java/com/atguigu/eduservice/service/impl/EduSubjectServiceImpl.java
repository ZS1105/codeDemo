package com.atguigu.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.atguigu.eduservice.entity.subject.EduSubject;
import com.atguigu.eduservice.entity.subject.SubjectData;
import com.atguigu.eduservice.entity.subject.OneSubject;
import com.atguigu.eduservice.entity.subject.TwoSubject;
import com.atguigu.eduservice.listener.SubjectExcelListener;
import com.atguigu.eduservice.mapper.EduSubjectMapper;
import com.atguigu.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author zs
 * @since 2021-07-12
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Override
    public void saveSubject(MultipartFile file, EduSubjectService subjectService) {
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
            EasyExcel.read(inputStream, SubjectData.class, new SubjectExcelListener(subjectService)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<OneSubject> getAllSubject() {
        QueryWrapper<EduSubject> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("parent_id", "0");
        List<EduSubject> oneSubjects = baseMapper.selectList(wrapper1);

        QueryWrapper<EduSubject> wrapper2 = new QueryWrapper<>();
        wrapper2.ne("parent_id", "0");
        List<EduSubject> twoSubjects = baseMapper.selectList(wrapper2);

        List<OneSubject> subjects = new ArrayList<>();
        for (EduSubject oneSubject : oneSubjects) {
            OneSubject one= new OneSubject();
            one.setId(oneSubject.getId());
            one.setTitle(oneSubject.getTitle());
            // BeanUtils.copyProperties(oneSubject,one);

            List<TwoSubject> subjects2 = new ArrayList<>();
            for (EduSubject twoSubject : twoSubjects) {
                if (twoSubject.getParentId().equals(one.getId())){
                    TwoSubject two = new TwoSubject();
                    BeanUtils.copyProperties(twoSubject, two);
                    subjects2.add(two);
                }
            }

            one.setChildren(subjects2);

            subjects.add(one);
        }

        return subjects;
    }
}
