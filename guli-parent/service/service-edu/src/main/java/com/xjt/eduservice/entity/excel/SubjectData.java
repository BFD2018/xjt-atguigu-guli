package com.xjt.eduservice.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class SubjectData {
    //分类表第1列（一级分类）
    @ExcelProperty(index = 0)
    private String oneSubjectName;

    //分类表第2列（二级分类）
    @ExcelProperty(index = 1)
    private String twoSubjectName;
}

