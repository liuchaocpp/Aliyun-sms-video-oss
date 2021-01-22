package com.jxf.aliyun.aliyun.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.*;
import com.jxf.aliyun.aliyun.service.VideoService;
import com.jxf.aliyun.aliyun.utils.InitVodClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static com.jxf.aliyun.aliyun.utils.InitVodClient.initVodClient;


@Service
public class VideoServiceImpl implements VideoService {

    @Value("${sms.aliyun.accessKeyId}")
    private  String accessKeyId;

    @Value("${sms.aliyun.accessSecret}")
    private String accessSecret;


    @Override
    public String uploadVideo(MultipartFile file) {
        InputStream inputStream=null;
        String id =null;
        try {
            inputStream = file.getInputStream();
            String filename = file.getOriginalFilename();
            String title=filename.substring(0,filename.lastIndexOf("."));
            UploadStreamRequest request = new UploadStreamRequest(accessKeyId, accessSecret, title, filename, inputStream);
            //设置组 可以加密
            request.setTemplateGroupId("c54a3d0b92872d37e718bf8c9cc53a73");
            UploadVideoImpl uploadVideo = new UploadVideoImpl();
            UploadStreamResponse response = uploadVideo.uploadStream(request);

            if(response.isSuccess()){
                id= response.getVideoId();
            }else {
                id= response.getVideoId();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return id;
    }

    @Override
    public void getPlayInfo(String id){
        System.out.println(id);
        DefaultAcsClient client = InitVodClient.initVodClient(accessKeyId, accessSecret);
        GetPlayInfoResponse response = new GetPlayInfoResponse();
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        request.setVideoId(id);
        try {
            response=client.getAcsResponse(request);
            List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
            for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
                //获取播放URL
                System.out.println(playInfo.getPlayURL());
            }
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getAuthInfo(String id){
        System.out.println(id);
        DefaultAcsClient client = InitVodClient.initVodClient(accessKeyId, accessSecret);
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        GetVideoPlayAuthResponse response = new GetVideoPlayAuthResponse();
        request.setVideoId(id);
        try {
            response=client.getAcsResponse(request);
            System.out.println(response.getPlayAuth());

        } catch (ClientException e) {
            e.printStackTrace();
        }
        return response.getPlayAuth();
    }

    @Override
    public void delete(String id){
        DefaultAcsClient client = initVodClient(accessKeyId, accessSecret);
        DeleteVideoRequest request = new DeleteVideoRequest();
        // 可以批量删除 传入list,将List转给以","分割的字符串
        request.setVideoIds(id);
        DeleteVideoResponse response = new DeleteVideoResponse();
        try {
            response=client.getAcsResponse(request);
        } catch (ClientException e) {
            e.printStackTrace();
        }

    }
}
