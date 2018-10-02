package com.oceanli.oceanmooc.app.business.course.models;

import java.util.List;

/**
 * Created by ocean on 2018/10/3
 * Author :  ocean
 * Email  :  348686686@qq.com
 */
public class ChapterSectionModel {


    /**
     * code : 0
     * msg : 获取课程章节列表成功
     * data : {"chapter":[{"id":1,"courseId":1,"parentId":0,"sectionName":"什么是CSS?",
     * "duration":"4","videoUrl":"http://pevcw8o7e.bkt.clouddn.com//video/Jass/testV.mp4"},
     * {"id":2,"courseId":1,"parentId":0,"sectionName":"如何使用?","duration":"23",
     * "videoUrl":"http://pevcw8o7e.bkt.clouddn.com//video/Jass/testV.mp4"},{"id":3,"courseId":1,
     * "parentId":0,"sectionName":"进阶","duration":"23","videoUrl":"http://pevcw8o7e.bkt.clouddn
     * .com//video/Jass/testV.mp4"},{"id":4,"courseId":1,"parentId":0,"sectionName":"CSS框架",
     * "duration":"23","videoUrl":"http://pevcw8o7e.bkt.clouddn.com//video/Jass/testV.mp4"},
     * {"id":5,"courseId":1,"parentId":0,"sectionName":"实战","duration":"2",
     * "videoUrl":"http://pevcw8o7e.bkt.clouddn.com//video/Jass/testV.mp4"},{"id":6,"courseId":1,
     * "parentId":0,"sectionName":"总结","duration":"14","videoUrl":"http://pevcw8o7e.bkt.clouddn
     * .com//video/Jass/testV.mp4"}],"section":[[{"id":7,"courseId":1,"parentId":1,
     * "sectionName":"背景","duration":"44","videoUrl":"http://pevcw8o7e.bkt.clouddn
     * .com//video/Jass/testV.mp4"},{"id":8,"courseId":1,"parentId":1,"sectionName":"CSS介绍",
     * "duration":"42","videoUrl":"http://pevcw8o7e.bkt.clouddn.com//video/Jass/testV.mp4"},
     * {"id":9,"courseId":1,"parentId":1,"sectionName":"与HTML的关系","duration":"11",
     * "videoUrl":"http://pevcw8o7e.bkt.clouddn.com//video/Jass/testV.mp4"},{"id":10,
     * "courseId":1,"parentId":1,"sectionName":"与JS的关系","duration":"45",
     * "videoUrl":"http://pevcw8o7e.bkt.clouddn.com//video/Jass/testV.mp4"}],[{"id":11,
     * "courseId":1,"parentId":2,"sectionName":"引入CSS","duration":"6",
     * "videoUrl":"http://pevcw8o7e.bkt.clouddn.com//video/Jass/testV.mp4"},{"id":12,
     * "courseId":1,"parentId":2,"sectionName":"重点：选择器","duration":"7",
     * "videoUrl":"http://pevcw8o7e.bkt.clouddn.com//video/Jass/testV.mp4"},{"id":13,
     * "courseId":1,"parentId":2,"sectionName":"编写外部CSS文件","duration":"1",
     * "videoUrl":"http://pevcw8o7e.bkt.clouddn.com//video/Jass/testV.mp4"}],[{"id":14,
     * "courseId":1,"parentId":3,"sectionName":"CSS的继承","duration":"5",
     * "videoUrl":"http://pevcw8o7e.bkt.clouddn.com//video/Jass/testV.mp4"},{"id":15,
     * "courseId":1,"parentId":3,"sectionName":"编码规范","duration":"2","videoUrl":"http://pevcw8o7e
     * .bkt.clouddn.com//video/Jass/testV.mp4"}],[{"id":16,"courseId":1,"parentId":4,
     * "sectionName":"LESS介绍","duration":"2","videoUrl":"http://pevcw8o7e.bkt.clouddn
     * .com//video/Jass/testV.mp4"},{"id":17,"courseId":1,"parentId":4,"sectionName":"LESS使用",
     * "duration":"5","videoUrl":"http://pevcw8o7e.bkt.clouddn.com//video/Jass/testV.mp4"},
     * {"id":18,"courseId":1,"parentId":4,"sectionName":"SASS介绍","duration":"2",
     * "videoUrl":"http://pevcw8o7e.bkt.clouddn.com//video/Jass/testV.mp4"},{"id":19,
     * "courseId":1,"parentId":4,"sectionName":"SASS使用","duration":"8",
     * "videoUrl":"http://pevcw8o7e.bkt.clouddn.com//video/Jass/testV.mp4"}],[{"id":20,
     * "courseId":1,"parentId":5,"sectionName":"仿京东样式","duration":"2",
     * "videoUrl":"http://pevcw8o7e.bkt.clouddn.com//video/Jass/testV.mp4"},{"id":21,
     * "courseId":1,"parentId":5,"sectionName":"仿QQ首页","duration":"5",
     * "videoUrl":"http://pevcw8o7e.bkt.clouddn.com//video/Jass/testV.mp4"}],[{"id":22,
     * "courseId":1,"parentId":6,"sectionName":"未来前景","duration":"14",
     * "videoUrl":"http://pevcw8o7e.bkt.clouddn.com//video/Jass/testV.mp4"},{"id":23,
     * "courseId":1,"parentId":6,"sectionName":"学习路径","duration":"67",
     * "videoUrl":"http://pevcw8o7e.bkt.clouddn.com//video/Jass/testV.mp4"}]]}
     */

    private int code;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<ChapterBean> chapter;
        private List<List<SectionBean>> section;

        public List<ChapterBean> getChapter() {
            return chapter;
        }

        public void setChapter(List<ChapterBean> chapter) {
            this.chapter = chapter;
        }

        public List<List<SectionBean>> getSection() {
            return section;
        }

        public void setSection(List<List<SectionBean>> section) {
            this.section = section;
        }

        public static class ChapterBean {
            /**
             * id : 1
             * courseId : 1
             * parentId : 0
             * sectionName : 什么是CSS?
             * duration : 4
             * videoUrl : http://pevcw8o7e.bkt.clouddn.com//video/Jass/testV.mp4
             */

            private int id;
            private int courseId;
            private int parentId;
            private String sectionName;
            private String duration;
            private String videoUrl;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

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

            public String getSectionName() {
                return sectionName;
            }

            public void setSectionName(String sectionName) {
                this.sectionName = sectionName;
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
        }

        public static class SectionBean {
            /**
             * id : 7
             * courseId : 1
             * parentId : 1
             * sectionName : 背景
             * duration : 44
             * videoUrl : http://pevcw8o7e.bkt.clouddn.com//video/Jass/testV.mp4
             */

            private int id;
            private int courseId;
            private int parentId;
            private String sectionName;
            private String duration;
            private String videoUrl;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

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

            public String getSectionName() {
                return sectionName;
            }

            public void setSectionName(String sectionName) {
                this.sectionName = sectionName;
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
        }
    }
}
