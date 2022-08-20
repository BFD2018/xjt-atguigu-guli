package com.xjt.eduservice.entity.chapter;

import lombok.Data;

@Data
public class VideoVo {

    private String id;      //视频存储在数据库中的id

    private String title;       //视频名

    private String videoSourceId;//视频存储在阿里云VOD中的id
}
