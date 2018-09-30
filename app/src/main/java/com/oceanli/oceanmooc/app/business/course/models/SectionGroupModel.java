package com.oceanli.oceanmooc.app.business.course.models;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by ocean on 2018/9/29
 * Author :  ocean
 * Email  :  348686686@qq.com
 */
public class SectionGroupModel extends AbstractExpandableItem<SectionChildModel> implements MultiItemEntity {
    private Integer id;
    private String sectionName;

    public SectionGroupModel() {
    }

    public SectionGroupModel(Integer id, String sectionName) {
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
    public int getLevel() {
        return 0;
    }

    @Override
    public int getItemType() {
        return 0;
    }
}
