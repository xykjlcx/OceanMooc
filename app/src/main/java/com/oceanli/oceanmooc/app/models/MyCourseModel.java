package com.oceanli.oceanmooc.app.models;

import java.sql.Timestamp;

/**
 * Created by ocean on 2018/9/26
 * Author :  ocean
 * Email  :  348686686@qq.com
 */
public class MyCourseModel {

    private Integer id;
    private String courseName;
    private String courseDesc;
    private String duration;
    private Timestamp startStudyTime;
    private Timestamp endStudyTime;
    private String teacherName;

    public MyCourseModel() {
    }

    public MyCourseModel(String courseName, String courseDesc, String duration, Timestamp startStudyTime, Timestamp endStudyTime, String teacherName) {
        this.courseName = courseName;
        this.courseDesc = courseDesc;
        this.duration = duration;
        this.startStudyTime = startStudyTime;
        this.endStudyTime = endStudyTime;
        this.teacherName = teacherName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Timestamp getStartStudyTime() {
        return startStudyTime;
    }

    public void setStartStudyTime(Timestamp startStudyTime) {
        this.startStudyTime = startStudyTime;
    }

    public Timestamp getEndStudyTime() {
        return endStudyTime;
    }

    public void setEndStudyTime(Timestamp endStudyTime) {
        this.endStudyTime = endStudyTime;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
}
