package com.jxf.aliyun.aliyun.utils;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class R  {
    private Integer code;
    private boolean status;
    private String message;
    private Map<String, Object> data = new HashMap<>();

    private R(){};
    public static R ok() {
        R r = new R();
        r.code = 200;
        r.status = true;
        r.message = "success";
        return r;
    }

    public static R error() {
        R r = new R();
        r.code = 400;
        r.status = false;
        r.message = "error";
        return r;
    }

    public R message(String message) {
        this.message = message;
        return this;
    }

    public R code(Integer code) {
        this.code = code;
        return this;
    }

    public R data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    public R data(Map<String, Object> data) {
        this.data = data;
        return this;
    }
}