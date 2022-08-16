package com.xjt.aliyunvod.service;

import org.springframework.web.multipart.MultipartFile;

public interface VodService {
    String uploadVideoAly(MultipartFile file);
}
