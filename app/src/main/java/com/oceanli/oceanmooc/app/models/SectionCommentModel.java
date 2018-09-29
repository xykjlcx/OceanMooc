package com.oceanli.oceanmooc.app.models;

import java.sql.Timestamp;

/**
 * Created by ocean on 2018/9/29
 * Author :  ocean
 * Email  :  348686686@qq.com
 */
public class SectionCommentModel {

    private Integer id;
    private String headImgUrl;
    private String content;
    private Timestamp time;

    public SectionCommentModel(Integer id, String headImgUrl, String content, Timestamp time) {
        this.id = id;
        this.headImgUrl = headImgUrl;
        this.content = content;
        this.time = time;
    }

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "SectionCommentModel{" +
                "id=" + id +
                ", headImgUrl='" + headImgUrl + '\'' +
                ", content='" + content + '\'' +
                ", time=" + time +
                '}';
    }
}
