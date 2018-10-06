package com.oceanli.oceanmooc.app.business.user.models;

import com.oceanli.oceanmooc.app.business.home.models.CourseVoModel;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ocean on 2018/10/6
 * Author :  ocean
 * Email  :  348686686@qq.com
 */
public class MyCommentModel {


    /**
     * code : 0
     * msg : 获取我的评论列表成功
     * data : [{"id":41,"userId":1,"commentContent":"？？？？？？\nJiong jaja",
     * "commentTime":"2018-10-06 10:53:10","courseVo":{"id":1,"courseName":"css定位入门",
     * "courseDesc":"本课程，将带领大家了解一下定位的知识，教大家如何通过定位来进行布局。","classifyId":1,"duration":"30:29",
     * "level":"初级","imgUrl":"https://img2.mukewang.com/5ac3418d0001a9b806000338-240-135.jpg",
     * "videoUrl":"http://pevcw8o7e.bkt.clouddn.com/ec8e5f1a7b8b63aec9b7a01552fade5b.mp4",
     * "isFree":0,"price":20,"count":12}},{"id":40,"userId":1,"commentContent":"这个总可以评论了吧",
     * "commentTime":"2018-10-06 02:16:52","courseVo":{"id":1,"courseName":"css定位入门",
     * "courseDesc":"本课程，将带领大家了解一下定位的知识，教大家如何通过定位来进行布局。","classifyId":1,"duration":"30:29",
     * "level":"初级","imgUrl":"https://img2.mukewang.com/5ac3418d0001a9b806000338-240-135.jpg",
     * "videoUrl":"http://pevcw8o7e.bkt.clouddn.com/ec8e5f1a7b8b63aec9b7a01552fade5b.mp4",
     * "isFree":0,"price":20,"count":12}},{"id":39,"userId":1,"commentContent":"怎么说",
     * "commentTime":"2018-10-05 22:10:53","courseVo":{"id":1,"courseName":"css定位入门",
     * "courseDesc":"本课程，将带领大家了解一下定位的知识，教大家如何通过定位来进行布局。","classifyId":1,"duration":"30:29",
     * "level":"初级","imgUrl":"https://img2.mukewang.com/5ac3418d0001a9b806000338-240-135.jpg",
     * "videoUrl":"http://pevcw8o7e.bkt.clouddn.com/ec8e5f1a7b8b63aec9b7a01552fade5b.mp4",
     * "isFree":0,"price":20,"count":12}}]
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
         * id : 41
         * userId : 1
         * commentContent : ？？？？？？
         Jiong jaja
         * commentTime : 2018-10-06 10:53:10
         * courseVo : {"id":1,"courseName":"css定位入门",
         * "courseDesc":"本课程，将带领大家了解一下定位的知识，教大家如何通过定位来进行布局。","classifyId":1,"duration":"30:29",
         * "level":"初级","imgUrl":"https://img2.mukewang
         * .com/5ac3418d0001a9b806000338-240-135.jpg","videoUrl":"http://pevcw8o7e.bkt.clouddn
         * .com/ec8e5f1a7b8b63aec9b7a01552fade5b.mp4","isFree":0,"price":20,"count":12}
         */

        private int id;
        private int userId;
        private String commentContent;
        private String commentTime;
        private CourseVoModel.DataBean courseVo;

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

        public CourseVoModel.DataBean getCourseVo() {
            return courseVo;
        }

        public void setCourseVo(CourseVoModel.DataBean courseVo) {
            this.courseVo = courseVo;
        }

    }
}
