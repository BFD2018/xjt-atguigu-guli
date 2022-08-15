package com.xjt.eduservice.entity.chapter;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ChapterVo {

    private String id;

    private String title;

    //表示小节(一个章节有多个小节的视频)
    private List<VideoVo> children = new ArrayList<>();
}
