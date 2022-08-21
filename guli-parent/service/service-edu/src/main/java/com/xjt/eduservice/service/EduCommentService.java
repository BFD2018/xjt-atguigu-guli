package com.xjt.eduservice.service;

import com.xjt.eduservice.entity.EduComment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 评论 服务类
 * </p>
 *
 * @author javaXiong
 * @since 2022-08-20
 */
public interface EduCommentService extends IService<EduComment> {

    List<EduComment> queryCommentsByCourseId(String cid);
}
