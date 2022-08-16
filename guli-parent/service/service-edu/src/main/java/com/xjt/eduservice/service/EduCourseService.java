package com.xjt.eduservice.service;

import com.xjt.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xjt.eduservice.entity.vo.CourseInfoVo;
import com.xjt.eduservice.entity.vo.CoursePublishVo;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author javaXiong
 * @since 2022-08-15
 */
public interface EduCourseService extends IService<EduCourse> {

    String saveCourseInfo(CourseInfoVo courseInfoVo);

    CourseInfoVo getCourseInfo(String courseId);

    void updateCourseInfo(CourseInfoVo courseInfoVo);

    CoursePublishVo publishCourseInfo(String id);

    void removeCourse(String courseId);
}
