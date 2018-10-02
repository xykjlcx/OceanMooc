package com.oceanli.oceanmooc.app.business.course.models;

import java.util.List;

/**
 * Created by ocean on 2018/10/2
 * Author :  ocean
 * Email  :  348686686@qq.com
 */
public class CourseClassifyModel {


    /**
     * code : 0
     * msg : 获取所有分类成功
     * data : {"oneLevel":[{"id":2,"classifyName":"后端","parentId":0,"sequence":"0",
     * "createClassifyTime":"2018-10-01T15:56:44.000+0000",
     * "updateClassifyTime":"2018-10-01T15:56:44.000+0000"},{"id":1,"classifyName":"前端",
     * "parentId":0,"sequence":"1","createClassifyTime":"2018-10-01T15:56:51.000+0000",
     * "updateClassifyTime":"2018-10-01T15:56:51.000+0000"},{"id":5,"classifyName":"游戏开发",
     * "parentId":0,"sequence":"2","createClassifyTime":"2018-10-01T15:56:54.000+0000",
     * "updateClassifyTime":"2018-10-01T15:56:54.000+0000"},{"id":4,"classifyName":"运维",
     * "parentId":0,"sequence":"3","createClassifyTime":"2018-09-22T11:37:39.000+0000",
     * "updateClassifyTime":"2018-09-22T11:37:37.000+0000"},{"id":3,"classifyName":"移动开发",
     * "parentId":0,"sequence":"4","createClassifyTime":"2018-10-01T15:56:52.000+0000",
     * "updateClassifyTime":"2018-10-01T15:56:52.000+0000"}],"twoLevel":[[{"id":9,
     * "classifyName":"Java","parentId":2,"sequence":"0",
     * "createClassifyTime":"2018-09-22T11:39:20.000+0000",
     * "updateClassifyTime":"2018-09-22T11:39:23.000+0000"},{"id":10,"classifyName":"SpringBoot",
     * "parentId":2,"sequence":"1","createClassifyTime":"2018-09-22T11:39:34.000+0000",
     * "updateClassifyTime":"2018-09-22T11:39:38.000+0000"},{"id":11,"classifyName":"PHP",
     * "parentId":2,"sequence":"2","createClassifyTime":"2018-09-22T11:39:59.000+0000",
     * "updateClassifyTime":"2018-09-22T11:40:04.000+0000"},{"id":12,"classifyName":"C#",
     * "parentId":2,"sequence":"3","createClassifyTime":"2018-09-22T11:40:20.000+0000",
     * "updateClassifyTime":"2018-09-22T11:40:23.000+0000"}],[{"id":6,"classifyName":"HTML/CSS",
     * "parentId":1,"sequence":"0","createClassifyTime":"2018-09-22T11:39:02.000+0000",
     * "updateClassifyTime":"2018-09-22T11:39:00.000+0000"},{"id":7,"classifyName":"JavaScript",
     * "parentId":1,"sequence":"1","createClassifyTime":"2018-09-22T11:39:00.000+0000",
     * "updateClassifyTime":"2018-09-22T11:38:58.000+0000"},{"id":8,"classifyName":"Vue.js",
     * "parentId":1,"sequence":"2","createClassifyTime":"2018-09-22T11:38:52.000+0000",
     * "updateClassifyTime":"2018-09-22T11:38:56.000+0000"}],[{"id":18,"classifyName":"Unity 3D",
     * "parentId":5,"sequence":"0","createClassifyTime":"2018-09-22T11:42:29.000+0000",
     * "updateClassifyTime":"2018-09-22T11:42:33.000+0000"},{"id":19,"classifyName":"Cocos2D-x",
     * "parentId":5,"sequence":"1","createClassifyTime":"2018-09-22T11:43:24.000+0000",
     * "updateClassifyTime":"2018-09-22T11:43:27.000+0000"}],[{"id":16,"classifyName":"Linux",
     * "parentId":4,"sequence":"0","createClassifyTime":"2018-09-22T11:41:46.000+0000",
     * "updateClassifyTime":"2018-09-22T11:41:49.000+0000"},{"id":17,"classifyName":"自动化运维",
     * "parentId":4,"sequence":"1","createClassifyTime":"2018-09-22T11:41:58.000+0000",
     * "updateClassifyTime":"2018-09-22T11:42:02.000+0000"}],[{"id":13,"classifyName":"Android",
     * "parentId":3,"sequence":"0","createClassifyTime":"2018-09-22T11:40:53.000+0000",
     * "updateClassifyTime":"2018-09-22T11:40:51.000+0000"},{"id":14,"classifyName":"IOS",
     * "parentId":3,"sequence":"1","createClassifyTime":"2018-09-22T11:41:00.000+0000",
     * "updateClassifyTime":"2018-09-22T11:41:03.000+0000"},{"id":15,"classifyName":"React
     * Native","parentId":3,"sequence":"2","createClassifyTime":"2018-09-22T11:41:24.000+0000",
     * "updateClassifyTime":"2018-09-22T11:41:27.000+0000"}]]}
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
        private List<OneLevelBean> oneLevel;
        private List<List<TwoLevelBean>> twoLevel;

        public List<OneLevelBean> getOneLevel() {
            return oneLevel;
        }

        public void setOneLevel(List<OneLevelBean> oneLevel) {
            this.oneLevel = oneLevel;
        }

        public List<List<TwoLevelBean>> getTwoLevel() {
            return twoLevel;
        }

        public void setTwoLevel(List<List<TwoLevelBean>> twoLevel) {
            this.twoLevel = twoLevel;
        }

        public static class OneLevelBean {
            /**
             * id : 2
             * classifyName : 后端
             * parentId : 0
             * sequence : 0
             * createClassifyTime : 2018-10-01T15:56:44.000+0000
             * updateClassifyTime : 2018-10-01T15:56:44.000+0000
             */

            private int id;
            private String classifyName;
            private int parentId;
            private String sequence;
            private String createClassifyTime;
            private String updateClassifyTime;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getClassifyName() {
                return classifyName;
            }

            public void setClassifyName(String classifyName) {
                this.classifyName = classifyName;
            }

            public int getParentId() {
                return parentId;
            }

            public void setParentId(int parentId) {
                this.parentId = parentId;
            }

            public String getSequence() {
                return sequence;
            }

            public void setSequence(String sequence) {
                this.sequence = sequence;
            }

            public String getCreateClassifyTime() {
                return createClassifyTime;
            }

            public void setCreateClassifyTime(String createClassifyTime) {
                this.createClassifyTime = createClassifyTime;
            }

            public String getUpdateClassifyTime() {
                return updateClassifyTime;
            }

            public void setUpdateClassifyTime(String updateClassifyTime) {
                this.updateClassifyTime = updateClassifyTime;
            }
        }

        public static class TwoLevelBean {
            /**
             * id : 9
             * classifyName : Java
             * parentId : 2
             * sequence : 0
             * createClassifyTime : 2018-09-22T11:39:20.000+0000
             * updateClassifyTime : 2018-09-22T11:39:23.000+0000
             */

            private int id;
            private String classifyName;
            private int parentId;
            private String sequence;
            private String createClassifyTime;
            private String updateClassifyTime;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getClassifyName() {
                return classifyName;
            }

            public void setClassifyName(String classifyName) {
                this.classifyName = classifyName;
            }

            public int getParentId() {
                return parentId;
            }

            public void setParentId(int parentId) {
                this.parentId = parentId;
            }

            public String getSequence() {
                return sequence;
            }

            public void setSequence(String sequence) {
                this.sequence = sequence;
            }

            public String getCreateClassifyTime() {
                return createClassifyTime;
            }

            public void setCreateClassifyTime(String createClassifyTime) {
                this.createClassifyTime = createClassifyTime;
            }

            public String getUpdateClassifyTime() {
                return updateClassifyTime;
            }

            public void setUpdateClassifyTime(String updateClassifyTime) {
                this.updateClassifyTime = updateClassifyTime;
            }
        }
    }
}
