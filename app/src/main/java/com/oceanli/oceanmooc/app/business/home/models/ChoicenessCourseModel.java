package com.oceanli.oceanmooc.app.business.home.models;

import java.util.List;

/**
 * Created by ocean on 2018/9/26
 * Author :  ocean
 * Email  :  348686686@qq.com
 */
public class ChoicenessCourseModel {
    /**
     * code : 0
     * msg : 获取推荐好课成功
     * data : [{"id":1,"courseName":"css定位入门","courseDesc":"本课程，将带领大家了解一下定位的知识，教大家如何通过定位来进行布局。","classifyId":1,"duration":"30:29","level":"初级","imgUrl":"https://www.skuimg.com/u/20180924/11215159.png","videoUrl":"http://pevcw8o7e.bkt.clouddn.com/ec8e5f1a7b8b63aec9b7a01552fade5b.mp4","isFree":0,"price":0,"count":12},{"id":17,"courseName":"Cocos2d-x游戏开发之贪吃蛇(上)","courseDesc":"还记得早期Nokia手机上的贪吃蛇么？玩起来是不是很过瘾。今天我们有请徐波老师为大家带来用Cocos引起制作的贪吃蛇游戏，并且一定比原版的更好玩哦！本课程为游戏开发的入门课程，将为大家介绍如何在cocos环境中使用lua语言制作贪吃蛇的游戏","classifyId":19,"duration":"15：33","level":"初级","imgUrl":"图","videoUrl":"http://pevcw8o7e.bkt.clouddn.com//video/Jass/testV.mp4","isFree":0,"price":0,"count":0},{"id":8,"courseName":"Android性能优化之布局优化","courseDesc":"从Android系统屏幕UI刷新机制,布局的选择，优化标签的使用，原生View控件的优化等布局角度去讲解如何提高Android性能","classifyId":13,"duration":"34:12","level":"中级","imgUrl":"图","videoUrl":"http://pevcw8o7e.bkt.clouddn.com//video/Jass/testV.mp4","isFree":0,"price":0,"count":0},{"id":9,"courseName":"Android流行下拉菜单UI效果","courseDesc":"Android仿美团流行下拉菜单实现","classifyId":13,"duration":"27：19","level":"初级","imgUrl":"图","videoUrl":"http://pevcw8o7e.bkt.clouddn.com//video/Jass/testV.mp4","isFree":0,"price":0,"count":0}]
     */

    private int code;
    private String msg;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * courseName : css定位入门
         * courseDesc : 本课程，将带领大家了解一下定位的知识，教大家如何通过定位来进行布局。
         * classifyId : 1
         * duration : 30:29
         * level : 初级
         * imgUrl : https://www.skuimg.com/u/20180924/11215159.png
         * videoUrl : http://pevcw8o7e.bkt.clouddn.com/ec8e5f1a7b8b63aec9b7a01552fade5b.mp4
         * isFree : 0
         * price : 0
         * count : 12
         */

        private int id;
        private String courseName;
        private String courseDesc;
        private int classifyId;
        private String duration;
        private String level;
        private String imgUrl;
        private String videoUrl;
        private int isFree;
        private int price;
        private int count;

        public int getId() {
            return id;
        }

        public void setId(int id) {
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

        public int getClassifyId() {
            return classifyId;
        }

        public void setClassifyId(int classifyId) {
            this.classifyId = classifyId;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getVideoUrl() {
            return videoUrl;
        }

        public void setVideoUrl(String videoUrl) {
            this.videoUrl = videoUrl;
        }

        public int getIsFree() {
            return isFree;
        }

        public void setIsFree(int isFree) {
            this.isFree = isFree;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "id=" + id +
                    ", courseName='" + courseName + '\'' +
                    ", courseDesc='" + courseDesc + '\'' +
                    ", classifyId=" + classifyId +
                    ", duration='" + duration + '\'' +
                    ", level='" + level + '\'' +
                    ", imgUrl='" + imgUrl + '\'' +
                    ", videoUrl='" + videoUrl + '\'' +
                    ", isFree=" + isFree +
                    ", price=" + price +
                    ", count=" + count +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ChoicenessCourseModel{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
