package com.xjt.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xjt.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xjt.eduservice.entity.frontvo.CourseFrontVo;
import com.xjt.eduservice.entity.frontvo.CourseWebVo;
import com.xjt.eduservice.entity.vo.CourseInfoVo;
import com.xjt.eduservice.entity.vo.CoursePublishVo;

import java.util.Map;

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

    Map<String, Object> getCourseFrontList(Page<EduCourse> pageCourse, CourseFrontVo courseFrontVo);

    CourseWebVo getBaseCourseInfo(String courseId);
}
