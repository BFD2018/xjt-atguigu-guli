package com.xjt.aliyunoss.service.impl;

import cn.hutool.core.date.DateTime;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.CreateBucketRequest;
import com.aliyun.oss.model.ObjectMetadata;
import com.xjt.aliyunoss.service.OssService;
import com.xjt.aliyunoss.utils.ConstantPropertiesUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
public class OssServiceImpl implements OssService {

    @Override
    public String uploadFileAvatar(MultipartFile file) {
        String endpoint = ConstantPropertiesUtils.END_POIND;
        String accessKeyId = ConstantPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtils.BUCKET_NAME;
        String fileDir = ConstantPropertiesUtils.FILE_DIR;

        try {
            // 创建OSS实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            //获取上传文件输入流
            InputStream inputStream = file.getInputStream();
            //获取文件名称
            String originalFilename = file.getOriginalFilename();
            //文件重命名
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            // yuy76t5rew01.jpg
            String newFileName = uuid + "-" + originalFilename;

            //2 把文件按照日期进行分类
            //获取当前日期
            //   2019/11/12
            String datePath = new DateTime().toString("yyyy/MM/dd");
            //拼接
            //  2019/11/12/ewtqr313401.jpg
            // 文件上传的路径地址
            newFileName = fileDir + "/" + datePath + "/" + newFileName;

            /**
             * 下面两行代码是重点坑：
             * 现在阿里云OSS 默认图片上传ContentType是image/jpeg
             * 也就是说，获取图片链接后，图片是下载链接，而并非在线浏览链接，
             * 因此，这里在上传的时候要解决ContentType的问题，将其改为image/jpg
             */
            ObjectMetadata meta = new ObjectMetadata();
            meta.setContentType("image/jpg");

            if(!ossClient.doesBucketExist(bucketName)){
                ossClient.createBucket(bucketName);
                CreateBucketRequest bucketRequest = new CreateBucketRequest(bucketName);
                bucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
                ossClient.createBucket(bucketRequest);
            }
            //文件上传至阿里云OSS
            ossClient.putObject(bucketName, newFileName, inputStream, meta);

            // 关闭OSSClient。
            ossClient.shutdown();

            //把上传之后文件路径返回
            //需要把上传到阿里云oss路径手动拼接出来
            //  https://edu-guli-1010.oss-cn-beijing.aliyuncs.com/01.jpg
            String url = "http://" + bucketName + "." + endpoint + "/" + newFileName;
            return url;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
