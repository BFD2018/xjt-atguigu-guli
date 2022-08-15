package com.xjt.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xjt.commonutils.R;
import com.xjt.eduservice.entity.EduTeacher;
import com.xjt.eduservice.entity.vo.TeacherQuery;
import com.xjt.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author JavaXiong
 * @since 2022-08-13
 */
@Api("讲师管理")
@CrossOrigin
@RestController
@RequestMapping("/eduservice/teacher")
public class EduTeacherController {
    @Autowired
    private EduTeacherService teacherService;

    //获取所有老师信息
    @ApiOperation(value = "所有讲师列表")
    @GetMapping("/findAll")
    public R getAllTeacher(){
        List<EduTeacher> list = teacherService.list();

        return R.ok().data("teacherList",list);
    }

    //2 逻辑删除讲师的方法
    @ApiOperation(value = "逻辑删除讲师")
    @DeleteMapping("/{id}")
    public R removeTeacher(@ApiParam(name = "id", value = "讲师ID", required = true)
                           @PathVariable("id") String id) {
        boolean b = teacherService.removeById(id);
        if(b){
            return R.ok();
        }else{
            return R.error();
        }
    }

    //3 分页查询讲师的方法
    //current 当前页
    //limit 每页记录数
    @GetMapping("/page/{current}/{limit}")
    public R pageListTeacher(@PathVariable long current,
                             @PathVariable long limit){
        Page<EduTeacher> teacherPage = new Page<>(current, limit);
        Page<EduTeacher> page = teacherService.page(teacherPage);
        return R.ok().data("page",page);
    }

    //4 条件查询带分页的方法(把查询条件封装成一个bean)
    @PostMapping("/pageTeacherCondition/{current}/{limit}")
    public R pageConditionQuery(@PathVariable("current") long current,@PathVariable("limit") long limit,
                                @RequestBody(required = false) TeacherQuery teacherQuery){
        Page<EduTeacher> pagination = new Page<>(current, limit);
        //构建mybatis-plus的查询条件
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        if(teacherQuery != null){
            String name = teacherQuery.getName();
            Integer level = teacherQuery.getLevel();
            String begin = teacherQuery.getBegin();
            String end = teacherQuery.getEnd();
            //判断条件值是否为空，如果不为空拼接条件
            if(!StringUtils.isEmpty(name)) {
                //构建条件
                wrapper.like("name",name);
            }
            if(!StringUtils.isEmpty(level)) {
                wrapper.eq("level",level);
            }
            if(!StringUtils.isEmpty(begin)) {
                wrapper.ge("gmt_create",begin);
            }
            if(!StringUtils.isEmpty(end)) {
                wrapper.le("gmt_create",end);
            }
        }

        Page<EduTeacher> page = teacherService.page(pagination, wrapper);

        return R.ok().data("total",page.getTotal()).data("rows",page.getRecords());
    }


    //5 添加讲师
    @ApiOperation(value = "新增讲师")
    @PostMapping("/addTeacher")
    public R addTeacher(@ApiParam(name = "teacher", value = "讲师对象", required = true) @RequestBody EduTeacher teacher){
        teacherService.save(teacher);

        return R.ok();
    }

    //6 根据讲师id进行查询
    @ApiOperation(value = "根据ID查询讲师")
    @GetMapping("/getTeacher/{id}")
    public R queryTeacherById(@ApiParam(name = "id", value = "讲师ID", required = true) @PathVariable("id") String id){
        EduTeacher teacher = teacherService.getById(id);

        return R.ok().data("teacher",teacher);
    }

    //7 修改讲师
    @ApiOperation(value = "根据ID修改讲师")
    @PutMapping("/{id}")
    public R updateById(@ApiParam(name = "id", value = "讲师ID", required = true) @PathVariable String id,
                        @ApiParam(name = "teacher", value = "讲师对象", required = true) @RequestBody EduTeacher teacher){
        teacher.setId(id);
        teacherService.updateById(teacher);

        return R.ok();

    }

    //8 讲师修改功能
    @PostMapping("/updateTeacher")
    public R updateTeacher(@RequestBody EduTeacher eduTeacher) {
        boolean flag = teacherService.updateById(eduTeacher);
        if(flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

}

