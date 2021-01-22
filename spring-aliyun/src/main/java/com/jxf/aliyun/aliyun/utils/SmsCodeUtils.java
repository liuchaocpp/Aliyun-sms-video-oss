package com.jxf.aliyun.aliyun.utils;


import java.util.Random;
import java.util.UUID;
import java.util.stream.IntStream;

public class SmsCodeUtils {

    public static String generateCode(){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            stringBuilder.append(new Random().nextInt(10));
        }
        return stringBuilder.toString();
    }
}

