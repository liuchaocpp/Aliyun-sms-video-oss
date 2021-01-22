package com.jxf.aliyun.aliyun.controller;

import com.jxf.aliyun.aliyun.service.SmsService;
import com.jxf.aliyun.aliyun.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sms")
@CrossOrigin
public class SmsController {

    @Autowired
    private SmsService smsService;

    @GetMapping("/{phone}")
    public R smsCode(@PathVariable String phone){
        smsService.sendMessage(phone);
        return R.ok();
    }

}
