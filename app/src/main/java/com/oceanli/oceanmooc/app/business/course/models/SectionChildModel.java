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
    private String sectionName;
    private boolean isSelected;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public SectionChildModel() {
    }

    public SectionChildModel(Integer id, String sectionName) {
        this.id = id;
        this.sectionName = sectionName;
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
