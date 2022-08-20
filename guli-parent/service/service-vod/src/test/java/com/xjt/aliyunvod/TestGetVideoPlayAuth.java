package com.xjt.aliyunvod;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import org.junit.Test;

public class TestGetVideoPlayAuth {
    @Test
    public void testGetVidelPlayAuth01(){
        DefaultProfile profile = DefaultProfile.getProfile("cn-shanghai", "LTAI5t7xY1zP7omT2bkWCpdw", "7EQtpsfPgZzOaAxGllfv9PTbMLdUK2");

        IAcsClient client = new DefaultAcsClient(profile);

        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        request.setVideoId("d1f28695281848fcb15e1aa393a6bf27");

        try {
            GetVideoPlayAuthResponse response = client.getAcsResponse(request);
            System.out.println(JSON.toJSONString(response));
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            System.out.println("ErrCode:" + e.getErrCode());
            System.out.println("ErrMsg:" + e.getErrMsg());
            System.out.println("RequestId:" + e.getRequestId());
        }
    }

}
