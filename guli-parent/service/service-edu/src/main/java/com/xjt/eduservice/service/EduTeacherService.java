package com.xjt.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xjt.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author JavaXiong
 * @since 2022-08-13
 */
public interface EduTeacherService extends IService<EduTeacher> {

    Map<String, Object> getTeacherFrontList(Page<EduTeacher> pageTeacher);

}
