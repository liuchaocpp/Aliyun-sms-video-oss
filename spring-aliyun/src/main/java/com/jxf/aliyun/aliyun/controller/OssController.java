package com.jxf.aliyun.aliyun.controller;

import com.jxf.aliyun.aliyun.service.OssService;
import com.jxf.aliyun.aliyun.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/oss")
@CrossOrigin
public class OssController {

    @Autowired
    private OssService ossService;

    @PostMapping("/upload")
    public R uploadPicture(MultipartFile file){
        System.out.println(file.getName());
        String url = ossService.uploadPicture(file);
        return R.ok().data("url",url);
    }
}
