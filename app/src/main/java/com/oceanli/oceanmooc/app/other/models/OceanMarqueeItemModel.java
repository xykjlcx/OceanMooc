package com.oceanli.oceanmooc.app.other.models;

/**
 * Created by ocean on 2018/9/26
 * Author :  ocean
 * Email  :  348686686@qq.com
 */
public class OceanMarqueeItemModel {

    private Integer img;
    private String titleOne;
    private String titleTwo;

    public OceanMarqueeItemModel() {
    }

    public OceanMarqueeItemModel(Integer img, String titleOne, String titleTwo) {
        this.img = img;
        this.titleOne = titleOne;
        this.titleTwo = titleTwo;
    }

    public Integer getImg() {
        return img;
    }

    public void setImg(Integer img) {
        this.img = img;
    }

    public String getTitleOne() {
        return titleOne;
    }

    public void setTitleOne(String titleOne) {
        this.titleOne = titleOne;
    }

    public String getTitleTwo() {
        return titleTwo;
    }

    public void setTitleTwo(String titleTwo) {
        this.titleTwo = titleTwo;
    }
}
