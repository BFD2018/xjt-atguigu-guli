package com.xjt.eduservice.service;

import com.xjt.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xjt.eduservice.entity.subject.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author javaXiong
 * @since 2022-08-15
 */
public interface EduSubjectService extends IService<EduSubject> {

    void saveSubject(MultipartFile file, EduSubjectService subjectService);

    //课程分类列表（树形）
    List<OneSubject> getAllOneTwoSubject();
}
