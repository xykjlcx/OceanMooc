package com.oceanli.oceanmooc.app.business.home.models;

import java.util.List;

/**
 * Created by ocean on 2018/9/26
 * Author :  ocean
 * Email  :  348686686@qq.com
 */
public class MyCourseModel {

    /**
     * code : 0
     * msg : 获取我的课程成功
     * data : [{"id":1,"courseVo":{"id":3,"courseName":"vue2.5入门","courseDesc":"对于很多刚接触Vue的同学，最难做到的就是编程思路的切换，这门课程，我们将通过形象的例子给大家讲解Vue的基础语法及编程思路，带大家快速的上手Vue的基础开发，这门课也包含了关于组件话和vue-cli等内容的基础讲解。","classifyId":8,"duration":"19:36","level":"中级","imgUrl":"https://img2.sycdn.imooc.com/szimg/5b6015ac00011ca105400300.jpg","videoUrl":"http://pevcw8o7e.bkt.clouddn.com//video/Jass/testV.mp4","isFree":0,"price":43,"count":0},"sectionId":3,"sectionName":"章3","firstStudyTime":"2018-10-01T06:23:41.000+0000","lastStudyTime":"2018-10-01T06:23:45.000+0000"},{"id":2,"courseVo":{"id":2,"courseName":"Java9模块化","courseDesc":"该课程以一个具体实例为主线，贯穿Java 9平台模块系统实战开发的全过程。所涵盖的内容包括运行和开发环境的搭建和准备，模块系统概述，模块声明中包含的模块导出和依赖和服务，Maven项目构建，以及使用jlink创建自定义运行时镜像。","classifyId":1,"duration":"20:18","level":"初级","imgUrl":"https://img1.sycdn.imooc.com/szimg/5bab70af00014fe105400300.jpg","videoUrl":"http://pevcw8o7e.bkt.clouddn.com//video/Jass/testV.mp4","isFree":0,"price":23,"count":0},"sectionId":2,"sectionName":"章2","firstStudyTime":"2018-10-01T06:22:13.000+0000","lastStudyTime":"2018-10-01T06:22:18.000+0000"},{"id":3,"courseVo":{"id":1,"courseName":"css定位入门","courseDesc":"本课程，将带领大家了解一下定位的知识，教大家如何通过定位来进行布局。","classifyId":1,"duration":"30:29","level":"初级","imgUrl":"https://img2.mukewang.com/5ac3418d0001a9b806000338-240-135.jpg","videoUrl":"http://pevcw8o7e.bkt.clouddn.com/ec8e5f1a7b8b63aec9b7a01552fade5b.mp4","isFree":0,"price":20,"count":12},"sectionId":4,"sectionName":"章4","firstStudyTime":"2018-10-01T03:14:28.000+0000","lastStudyTime":"2018-10-01T03:14:32.000+0000"}]
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
         * courseVo : {"id":3,"courseName":"vue2.5入门","courseDesc":"对于很多刚接触Vue的同学，最难做到的就是编程思路的切换，这门课程，我们将通过形象的例子给大家讲解Vue的基础语法及编程思路，带大家快速的上手Vue的基础开发，这门课也包含了关于组件话和vue-cli等内容的基础讲解。","classifyId":8,"duration":"19:36","level":"中级","imgUrl":"https://img2.sycdn.imooc.com/szimg/5b6015ac00011ca105400300.jpg","videoUrl":"http://pevcw8o7e.bkt.clouddn.com//video/Jass/testV.mp4","isFree":0,"price":43,"count":0}
         * sectionId : 3
         * sectionName : 章3
         * firstStudyTime : 2018-10-01T06:23:41.000+0000
         * lastStudyTime : 2018-10-01T06:23:45.000+0000
         */

        private int id;
        private CourseVoBean courseVo;
        private int sectionId;
        private String sectionName;
        private String firstStudyTime;
        private String lastStudyTime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public CourseVoBean getCourseVo() {
            return courseVo;
        }

        public void setCourseVo(CourseVoBean courseVo) {
            this.courseVo = courseVo;
        }

        public int getSectionId() {
            return sectionId;
        }

        public void setSectionId(int sectionId) {
            this.sectionId = sectionId;
        }

        public String getSectionName() {
            return sectionName;
        }

        public void setSectionName(String sectionName) {
            this.sectionName = sectionName;
        }

        public String getFirstStudyTime() {
            return firstStudyTime;
        }

        public void setFirstStudyTime(String firstStudyTime) {
            this.firstStudyTime = firstStudyTime;
        }

        public String getLastStudyTime() {
            return lastStudyTime;
        }

        public void setLastStudyTime(String lastStudyTime) {
            this.lastStudyTime = lastStudyTime;
        }

        public static class CourseVoBean {
            /**
             * id : 3
             * courseName : vue2.5入门
             * courseDesc : 对于很多刚接触Vue的同学，最难做到的就是编程思路的切换，这门课程，我们将通过形象的例子给大家讲解Vue的基础语法及编程思路，带大家快速的上手Vue的基础开发，这门课也包含了关于组件话和vue-cli等内容的基础讲解。
             * classifyId : 8
             * duration : 19:36
             * level : 中级
             * imgUrl : https://img2.sycdn.imooc.com/szimg/5b6015ac00011ca105400300.jpg
             * videoUrl : http://pevcw8o7e.bkt.clouddn.com//video/Jass/testV.mp4
             * isFree : 0
             * price : 43
             * count : 0
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
        }
    }
}
