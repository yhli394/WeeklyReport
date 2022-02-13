package com.liyuehong.weeklyreport.model;

import io.swagger.models.auth.In;

/**
 * @author yhli3
 * @classname Image
 * @Date 2022/1/13 15:46
 */
public class Image {
    private String data;

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Image(Integer id) {
        this.id = id;
    }

    public Image() {
    }

    public Image(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
