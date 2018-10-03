package com.oceanli.oceanmooc.app.business.course.models;

import java.sql.Timestamp;

/**
 * Created by ocean on 2018/9/29 Author :  ocean Email  :  348686686@qq.com
 */
public class SectionCommentModel {
    private Integer id;
    private String headImgUrl;
    private String userName;
    private String content;
    private String time;

    public SectionCommentModel() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "SectionCommentModel{" +
                "id=" + id +
                ", headImgUrl='" + headImgUrl + '\'' +
                ", userName='" + userName + '\'' +
                ", content='" + content + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
