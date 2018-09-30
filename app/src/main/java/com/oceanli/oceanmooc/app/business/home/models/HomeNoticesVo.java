package com.oceanli.oceanmooc.app.business.home.models;

import java.util.List;

/**
 * Created by ocean on 2018/9/30
 * Author :  ocean
 * Email  :  348686686@qq.com
 */
public class HomeNoticesVo {


    /**
     * code : 0
     * msg : 获取首页公告成功
     * data : [{"id":1,"contentOne":"常州信息职业技术学院2018迎新","contentTwo":"关于宿舍整改","createTime":"2018-09-30T11:08:46.000+0000","updateTime":"2018-09-30T11:08:46.000+0000"},{"id":2,"contentOne":"英语AB级开放报名","contentTwo":"大三实习准备","createTime":"2018-09-30T11:09:04.000+0000","updateTime":"2018-09-30T11:09:04.000+0000"}]
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
         * contentOne : 常州信息职业技术学院2018迎新
         * contentTwo : 关于宿舍整改
         * createTime : 2018-09-30T11:08:46.000+0000
         * updateTime : 2018-09-30T11:08:46.000+0000
         */

        private int id;
        private String contentOne;
        private String contentTwo;
        private String createTime;
        private String updateTime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getContentOne() {
            return contentOne;
        }

        public void setContentOne(String contentOne) {
            this.contentOne = contentOne;
        }

        public String getContentTwo() {
            return contentTwo;
        }

        public void setContentTwo(String contentTwo) {
            this.contentTwo = contentTwo;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }
    }
}
