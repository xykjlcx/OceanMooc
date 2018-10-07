package com.oceanli.oceanmooc.app.business.course.models;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.oceanli.oceanmooc.app.business.course.adapter.CourseSectionExpandableAdapter;

/**
 * Created by ocean on 2018/9/29
 * Author :  ocean
 * Email  :  348686686@qq.com
 */
public class SectionChildModel implements MultiItemEntity {
    private Integer id;
    private boolean isSelected;
    private int courseId;
    private int parentId;
    private String sectionName;
    private String duration;
    private String videoUrl;

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public SectionChildModel() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }


    @Override
    public int getItemType() {
        return CourseSectionExpandableAdapter.TYPE_CHILD;
    }
}
