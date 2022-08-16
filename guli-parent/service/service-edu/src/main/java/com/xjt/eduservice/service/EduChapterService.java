package com.xjt.eduservice.service;

import com.xjt.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xjt.eduservice.entity.chapter.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author javaXiong
 * @since 2022-08-15
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterVo> getChapterVideoByCourseId(String courseId);

    boolean deleteChapter(String chapterId);

    void removeChapterByCourseId(String courseId);
}
