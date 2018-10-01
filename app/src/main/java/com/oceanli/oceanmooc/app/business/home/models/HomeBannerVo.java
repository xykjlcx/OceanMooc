package com.oceanli.oceanmooc.app.business.home.models;

import java.util.List;

/**
 * Created by ocean on 2018/9/30 Author :  ocean Email  :  348686686@qq.com
 */
public class HomeBannerVo {
    /**
     * code : 0 msg : 获取课程轮播成功 data : [{"id":1,"bannerText":"前端高级课程vue.js源码分析","bannerPreviewImg":"https://img.mukewang
     * .com/5b6a947e00013edb09360316.jpg","bannerUrlAddress":"http://coding.imooc
     * .com/class/259.html?mc_marking=bf8153b00fbb5fc30db143d389ef462a&mc_channel=banner","weight":100,
     * "createBannerTime":"2018-09-22T12:04:23.000+0000","updateBannerTime":"2018-09-22T12:04:26.000+0000"},{"id":2,
     * "bannerText":"深度学习之神经网络（CNN/RNN/GAN）\n算法原理+实战","bannerPreviewImg":"https://img.mukewang.com/5b682a210001156e09360316.jpg",
     * "bannerUrlAddress":"https://img.mukewang.com/5b682a210001156e09360316.jpg","weight":80,"createBannerTime":"2018-09-22T12:04:57.000+0000",
     * "updateBannerTime":"2018-09-22T12:05:01.000+0000"},{"id":3,"bannerText":"Python 爬虫工程师必学\u2014\u2014App数据抓取实战\n掌握App数据抓取技能，工作面试更自信",
     * "bannerPreviewImg":"https://img.mukewang.com/5ba2fd2c0001445c09360316.jpg","bannerUrlAddress":"https://img.mukewang
     * .com/5ba2fd2c0001445c09360316.jpg","weight":88,"createBannerTime":"2018-09-22T12:05:35.000+0000",
     * "updateBannerTime":"2018-09-22T12:05:40.000+0000"},{"id":4,"bannerText":"React 服务器渲染原理解析与实践\n从零开始，带你搭建属于自己的React SSR框架",
     * "bannerPreviewImg":"https://img.mukewang.com/5ba1ae5d000191a109360316.jpg","bannerUrlAddress":"https://img.mukewang
     * .com/5ba1ae5d000191a109360316.jpg","weight":90,"createBannerTime":"2018-09-22T12:06:18.000+0000",
     * "updateBannerTime":"2018-09-22T12:06:22.000+0000"},{"id":5,"bannerText":"纯正商业级应用-微信小程序开发实战","bannerPreviewImg":"https://img.mukewang
     * .com/5b4c0686000136fc18720632.jpg","bannerUrlAddress":"https://img.mukewang.com/5b4c0686000136fc18720632.jpg","weight":89,
     * "createBannerTime":"2018-09-22T12:06:53.000+0000","updateBannerTime":"2018-09-22T12:06:56.000+0000"}]
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
         * id : 1 bannerText : 前端高级课程vue.js源码分析 bannerPreviewImg : https://img.mukewang.com/5b6a947e00013edb09360316.jpg bannerUrlAddress :
         * http://coding
         * .imooc.com/class/259.html?mc_marking=bf8153b00fbb5fc30db143d389ef462a&mc_channel=banner weight : 100 createBannerTime :
         * 2018-09-22T12:04:23.000+0000 updateBannerTime : 2018-09-22T12:04:26.000+0000
         */
        private int id;
        private String bannerText;
        private String bannerPreviewImg;
        private String bannerUrlAddress;
        private int weight;
        private String createBannerTime;
        private String updateBannerTime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getBannerText() {
            return bannerText;
        }

        public void setBannerText(String bannerText) {
            this.bannerText = bannerText;
        }

        public String getBannerPreviewImg() {
            return bannerPreviewImg;
        }

        public void setBannerPreviewImg(String bannerPreviewImg) {
            this.bannerPreviewImg = bannerPreviewImg;
        }

        public String getBannerUrlAddress() {
            return bannerUrlAddress;
        }

        public void setBannerUrlAddress(String bannerUrlAddress) {
            this.bannerUrlAddress = bannerUrlAddress;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public String getCreateBannerTime() {
            return createBannerTime;
        }

        public void setCreateBannerTime(String createBannerTime) {
            this.createBannerTime = createBannerTime;
        }

        public String getUpdateBannerTime() {
            return updateBannerTime;
        }

        public void setUpdateBannerTime(String updateBannerTime) {
            this.updateBannerTime = updateBannerTime;
        }
    }
}
