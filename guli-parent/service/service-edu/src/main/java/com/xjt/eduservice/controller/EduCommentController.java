package com.xjt.eduservice.controller;


import com.xjt.commonutils.R;
import com.xjt.eduservice.entity.EduComment;
import com.xjt.eduservice.service.EduCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 评论 前端控制器
 * </p>
 *
 * @author javaXiong
 * @since 2022-08-20
 */
@CrossOrigin
@RestController
@RequestMapping("/eduservice/comment")
public class EduCommentController {
    @Autowired
    private EduCommentService eduCommentService;

    @GetMapping("/getCommentById/{cid}")
    public R getCourseCommentsByCourseId(@PathVariable("cid") String cid){
        List<EduComment> commentList = eduCommentService.queryCommentsByCourseId(cid);

        return R.ok().data("commentList",commentList);
    }

    @PostMapping("/addComment")
    public R addCourserComment(@RequestBody EduComment eduComment){
        System.out.println("eduComment==========>");
        System.out.println(eduComment);
        boolean save = eduCommentService.save(eduComment);
        if(save){
            return R.ok();
        }else{
            return R.error();
        }
    }
}

