package com.oceanli.oceanmooc.app.business.home.models;

import java.util.List;

/**
 * Created by ocean on 2018/9/26
 * Author :  ocean
 * Email  :  348686686@qq.com
 */
public class RecommendCourseModel {


    /**
     * code : 0
     * msg : 获取课程数据成功,当前页:0,每页显示：8
     * data : [{"id":1,"courseName":"css定位入门","courseDesc":"本课程，将带领大家了解一下定位的知识，教大家如何通过定位来进行布局。","classifyId":1,"duration":"30:29","level":"初级","imgUrl":"http://pevcw8o7e.bkt.clouddn.com/om1fuben.jpg","videoUrl":"http://pevcw8o7e.bkt.clouddn.com/ec8e5f1a7b8b63aec9b7a01552fade5b.mp4","isFree":0,"price":0,"count":12},{"id":2,"courseName":"Java9模块化","courseDesc":"该课程以一个具体实例为主线，贯穿Java 9平台模块系统实战开发的全过程。所涵盖的内容包括运行和开发环境的搭建和准备，模块系统概述，模块声明中包含的模块导出和依赖和服务，Maven项目构建，以及使用jlink创建自定义运行时镜像。","classifyId":1,"duration":"20:18","level":"初级","imgUrl":"https://www.skuimg.com/u/20180924/11215159.png","videoUrl":"http://pevcw8o7e.bkt.clouddn.com//video/Jass/testV.mp4","isFree":0,"price":0,"count":0},{"id":3,"courseName":"vue2.5入门","courseDesc":"对于很多刚接触Vue的同学，最难做到的就是编程思路的切换，这门课程，我们将通过形象的例子给大家讲解Vue的基础语法及编程思路，带大家快速的上手Vue的基础开发，这门课也包含了关于组件话和vue-cli等内容的基础讲解。","classifyId":8,"duration":"19:36","level":"中级","imgUrl":"https://www.skuimg.com/u/20180924/11215159.png","videoUrl":"http://pevcw8o7e.bkt.clouddn.com//video/Jass/testV.mp4","isFree":0,"price":0,"count":0},{"id":4,"courseName":"从JS到TS开发数独游戏（TS版）","courseDesc":"通过前端技术实现一个简单的数独游戏，介绍相对简单的数独生成算法(递归)和检查算法。主要使用的前端技术包括 JavaScript、TypeScript 和 LESS 等。同时会讲到前端构建（打包）技术，使用的工具有 Gulp、Webpack、 Babel 和 TSC(TypeScript Compiler) 等。课程的重点在于关键算法和从 JavaScript 技术实现向 TypeScript 技术实现的转换。","classifyId":7,"duration":"116：43","level":"初级","imgUrl":"https://www.skuimg.com/u/20180924/11215159.png","videoUrl":"http://pevcw8o7e.bkt.clouddn.com//video/Jass/testV.mp4","isFree":0,"price":0,"count":0},{"id":5,"courseName":"Spring Boot 发送邮件","courseDesc":"本课程为大家详细介绍了发送邮件的相关知识和原理，介绍了 Spring Boot 的基础使用，最后为大家演示了如何通过 Spring Boot 发送各种类型的邮件，掌握如何去设计一个邮件系统。","classifyId":10,"duration":"87：00","level":"中级","imgUrl":"图地址","videoUrl":"http://pevcw8o7e.bkt.clouddn.com//video/Jass/testV.mp4","isFree":0,"price":0,"count":0},{"id":6,"courseName":"PhpStorm的基本应用","courseDesc":"PhpStorm是PHP集成开发工具，能提高我们的开发效率，提供智能代码补全，快速导航以及即时错误检查。","classifyId":11,"duration":"60：00","level":"初级","imgUrl":"图地址","videoUrl":"http://pevcw8o7e.bkt.clouddn.com//video/Jass/testV.mp4","isFree":0,"price":0,"count":0},{"id":7,"courseName":"PHP消息队列实现及应用","courseDesc":"消息队列是个高大上的名词儿，本质上就是如何业务进行排队处理，最常的使用场景就是发送短信的时候使用短信队列。但消息队列可以做的不止是这一类场景，它在解耦、消峰、异步、一致性等方面都有很大的用武之地。因此如何合理使用消息队列来处理一些特殊的业务需求，这就是我们这节课要解决的内容。 课程所需库文件：http://pan.baidu.com/s/1bQg038","classifyId":11,"duration":"74：14","level":"中级","imgUrl":"图地址","videoUrl":"http://pevcw8o7e.bkt.clouddn.com//video/Jass/testV.mp4","isFree":0,"price":0,"count":0},{"id":8,"courseName":"Android性能优化之布局优化","courseDesc":"从Android系统屏幕UI刷新机制,布局的选择，优化标签的使用，原生View控件的优化等布局角度去讲解如何提高Android性能","classifyId":13,"duration":"34:12","level":"中级","imgUrl":"http://pevcw8o7e.bkt.clouddn.com/om3.jpg","videoUrl":"http://pevcw8o7e.bkt.clouddn.com//video/Jass/testV.mp4","isFree":0,"price":0,"count":0}]
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
         * imgUrl : http://pevcw8o7e.bkt.clouddn.com/om1fuben.jpg
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
    }
}
