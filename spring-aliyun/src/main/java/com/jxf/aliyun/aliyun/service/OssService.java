package com.jxf.aliyun.aliyun.service;

import org.springframework.web.multipart.MultipartFile;

public interface OssService {
    String uploadPicture(MultipartFile file);
}
