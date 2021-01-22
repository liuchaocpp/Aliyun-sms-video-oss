package com.jxf.aliyun.aliyun.controller;

import com.jxf.aliyun.aliyun.service.VideoService;
import com.jxf.aliyun.aliyun.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/video")
@CrossOrigin
public class VideoController {

    @Autowired
    private VideoService videoService;

    @PostMapping("/upload")
    public R vodUpload(MultipartFile file){
        String id = videoService.uploadVideo(file);
        return R.ok().data("id",id);
    }

    @DeleteMapping("{id}")
    public R delete(@PathVariable String id){
        videoService.delete(id);
        return R.ok();
    }

    @GetMapping("/oss/{id}")
    public R get(@PathVariable String id){
        videoService.getPlayInfo(id);
        return R.ok();
    }

    @GetMapping("/auth/{id}")
    public R getAuth(@PathVariable String id){
        String authInfo = videoService.getAuthInfo(id);
        return R.ok().data("auth",authInfo);
    }
}
