package com.jxf.aliyun.aliyun.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.jxf.aliyun.aliyun.service.OssService;
import com.jxf.aliyun.aliyun.utils.CustomException;
import com.jxf.aliyun.aliyun.utils.DateUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class OssServiceImpl implements OssService {

    @Value("${sms.aliyun.accessKeyId}")
    private  String accessKeyId;

    @Value("${sms.aliyun.accessSecret}")
    private String accessSecret;

    @Value("${sms.aliyun.endpoint}")
    private  String endpoint;

    @Value("${sms.aliyun.bucketName}")
    private String bucketName;

    @Override
    public String uploadPicture(MultipartFile file) {

        //通过工具类中的属性 返回oss对象
        OSS oss = new OSSClientBuilder().build(endpoint,accessKeyId,accessSecret);
        // 通过时间戳 防止图片重名，导致图片覆盖

        String dateString = DateUtils.format(new Date());
        long time = new Date().getTime();
        String url=null;
        String fileName=dateString + "/" + time + file.getOriginalFilename();

        System.out.println(fileName);
        //获取文件流
        try(InputStream in = file.getInputStream()) {
            //上传图片
            oss.putObject(bucketName,fileName, in);
            //通过阿里云控制台查看，获得上传的图片地址，自己拼接地址，返回
            //https://edu-liuchao.oss-cn-chengdu.aliyuncs.com/2020/08/29/15986964850883.jpg
            url="https://"+bucketName+"."+endpoint+"/"+fileName;
        } catch (IOException e) {
            e.printStackTrace();
            //出现异常，通过自定义异常处理
            throw new CustomException(20001,"文件上传失败");
        }
        return url;
    }
}
