package com.xjt.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xjt.eduservice.entity.EduComment;
import com.xjt.eduservice.mapper.EduCommentMapper;
import com.xjt.eduservice.service.EduCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 评论 服务实现类
 * </p>
 *
 * @author javaXiong
 * @since 2022-08-20
 */
@Service
public class EduCommentServiceImpl extends ServiceImpl<EduCommentMapper, EduComment> implements EduCommentService {
    @Autowired
    private EduCommentMapper eduCommentMapper;

    @Override
    public List<EduComment> queryCommentsByCourseId(String cid) {
        QueryWrapper<EduComment> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",cid);
        List<EduComment> eduComments = eduCommentMapper.selectList(wrapper);


        return eduComments;

    }
}
