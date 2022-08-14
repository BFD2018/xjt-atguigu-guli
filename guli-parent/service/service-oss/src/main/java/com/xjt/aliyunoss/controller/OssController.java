package com.xjt.aliyunoss.controller;

import com.xjt.aliyunoss.service.OssService;
import com.xjt.commonutils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Api("阿里云OSS对象存储")
@RestController
@RequestMapping("/eduoss/fileoss")
@CrossOrigin
public class OssController {
    @Autowired
    private OssService ossService;

    //上传头像的方法
    @ApiOperation(value = "文件上传")
    @PostMapping
    public R uploadOssFile(@ApiParam(name = "file", value = "文件", required = true) MultipartFile file){
        String url = ossService.uploadFileAvatar(file);

        return R.ok().data("url",url);
    }

}
