package com.oceanli.oceanmooc.app.business.course.models;

import java.util.List;

/**
 * Created by ocean on 2018/10/3
 * Author :  ocean
 * Email  :  348686686@qq.com
 */
public class NetCommentModel {

    /**
     * code : 0
     * msg : 获取评论列表成功
     * data : [{"id":2,"userId":2,"userName":"ocean","userHeadImgUrl":"http://pevcw8o7e.bkt
     * .clouddn.com//img/default_head.png","courseId":1,"commentContent":"这是测试评论2",
     * "commentTime":"2018-10-03 10:37:34"},{"id":1,"userId":1,"userName":"xykjlcx",
     * "userHeadImgUrl":"http://pevcw8o7e.bkt.clouddn.com//img/default_head.png","courseId":1,
     * "commentContent":"这是测试评论1","commentTime":"2018-10-03 10:37:20"}]
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
         * id : 2
         * userId : 2
         * userName : ocean
         * userHeadImgUrl : http://pevcw8o7e.bkt.clouddn.com//img/default_head.png
         * courseId : 1
         * commentContent : 这是测试评论2
         * commentTime : 2018-10-03 10:37:34
         */

        private int id;
        private int userId;
        private String userName;
        private String userHeadImgUrl;
        private int courseId;
        private String commentContent;
        private String commentTime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserHeadImgUrl() {
            return userHeadImgUrl;
        }

        public void setUserHeadImgUrl(String userHeadImgUrl) {
            this.userHeadImgUrl = userHeadImgUrl;
        }

        public int getCourseId() {
            return courseId;
        }

        public void setCourseId(int courseId) {
            this.courseId = courseId;
        }

        public String getCommentContent() {
            return commentContent;
        }

        public void setCommentContent(String commentContent) {
            this.commentContent = commentContent;
        }

        public String getCommentTime() {
            return commentTime;
        }

        public void setCommentTime(String commentTime) {
            this.commentTime = commentTime;
        }
    }
}
