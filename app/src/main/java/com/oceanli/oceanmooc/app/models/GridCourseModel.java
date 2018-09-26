package com.oceanli.oceanmooc.app.models;

/**
 * Created by ocean on 2018/9/26
 * Author :  ocean
 * Email  :  348686686@qq.com
 */
public class GridCourseModel {

    private Integer id;
    private String courseName;
    private String courseDesc;
    private String price;


    public GridCourseModel(Integer id, String courseName, String courseDesc, String price) {
        this.id = id;
        this.courseName = courseName;
        this.courseDesc = courseDesc;
        this.price = price;
    }

    public GridCourseModel() {
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDesc() {
        return courseDesc;
    }

    public void setCourseDesc(String courseDesc) {
        this.courseDesc = courseDesc;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
