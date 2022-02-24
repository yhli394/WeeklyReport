package com.liyuehong.weeklyreport.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.*;
import org.apache.ibatis.annotations.Update;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Date;
public class Article {
    @ApiModelProperty(value = "文章的id（第一次提交不用传id过来）")
    private Integer id;
    @ApiModelProperty(value = "文章的标题")
    private String title;
    @JsonIgnore
    private Date editDate;

    @ApiModelProperty(value = "文章发表时间（不用传过来，后端做解析）")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date publishDate;

    @ApiModelProperty(value = "用户的id")
    private Integer uid;
    @JsonIgnore
    private Integer state;

    @ApiModelProperty(value = "文章的内容")
    private String content;

    private User author;

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", editDate=" + editDate +
                ", publishDate=" + publishDate +
                ", uid=" + uid +
                ", state=" + state +
                ", content='" + content + '\'' +
                ", author=" + author +
                '}';
    }

    public User getAuthor() {
        return author==null?null:author;
    }

    public void setAuthor(User author) {
        this.author = author==null?null:author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getEditDate() {
        return editDate;
    }

    public void setEditDate(Date editDate) {
        this.editDate = editDate;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

}