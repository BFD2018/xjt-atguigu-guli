package com.xjt.edutest;

import com.aliyun.oss.OSSClient;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OSSTest {
    // Endpoint以杭州为例，其它Region请按实际情况填写。
    String endpoint = "oss-cn-shenzhen.aliyuncs.com";
    // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问号。
    String accessKeyId = "LTAI5tBDqisVxf2EhUeyXsVG";
    String accessKeySecret = "7EQtpsfPgZzOaAxGllfv9PTbMLdUK2";
    String bucketName = "xiong-test-srt";

    @Test
    public void testCreateBucket() {
        // 创建OSSClient实例。
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        boolean exists = ossClient.doesBucketExist(bucketName);
        System.out.println(exists);
        // 关闭OSSClient。
        ossClient.shutdown();
    }
}
