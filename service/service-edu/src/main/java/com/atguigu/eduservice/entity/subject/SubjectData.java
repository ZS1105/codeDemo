package com.atguigu.eduservice.entity.subject;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * Author: shuai
 * Date: 2021/7/12
 * desc:
 */
@Data
public class SubjectData {
    @ExcelProperty(index = 0)
    private String oneSubjectName;

    @ExcelProperty(index = 1)
    private String twoSubjectName;
}
