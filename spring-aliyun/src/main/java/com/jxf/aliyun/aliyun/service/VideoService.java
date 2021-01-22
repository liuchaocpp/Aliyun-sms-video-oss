package com.jxf.aliyun.aliyun.service;

import org.springframework.web.multipart.MultipartFile;

public interface VideoService {
    String uploadVideo(MultipartFile file);

    void getPlayInfo(String id);

    String getAuthInfo(String id);

    void delete(String id);
}
