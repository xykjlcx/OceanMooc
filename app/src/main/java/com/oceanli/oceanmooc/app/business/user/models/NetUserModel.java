package com.oceanli.oceanmooc.app.business.user.models;

/**
 * Created by ocean on 2018/10/3
 * Author :  ocean
 * Email  :  348686686@qq.com
 */
public class NetUserModel {

    /**
     * code : 0
     * msg : 登录成功
     * data : {"id":1,"account":"xykjlcx","email":"348686686@qq.com","realName":"李长昕",
     * "signature":"这家伙有点懒，什么都没有留下！","phone":"15806119676","headImg":"http://pevcw8o7e.bkt
     * .clouddn.com//img/default_head.png","education":1,"gender":0,"lastLoginTime":"2018-10-03
     * 18:24:16"}
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
        /**
         * id : 1
         * account : xykjlcx
         * email : 348686686@qq.com
         * realName : 李长昕
         * signature : 这家伙有点懒，什么都没有留下！
         * phone : 15806119676
         * headImg : http://pevcw8o7e.bkt.clouddn.com//img/default_head.png
         * education : 1
         * gender : 0
         * lastLoginTime : 2018-10-03 18:24:16
         */

        private int id;
        private String account;
        private String email;
        private String realName;
        private String signature;
        private String phone;
        private String headImg;
        private int education;
        private int gender;
        private String lastLoginTime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getHeadImg() {
            return headImg;
        }

        public void setHeadImg(String headImg) {
            this.headImg = headImg;
        }

        public int getEducation() {
            return education;
        }

        public void setEducation(int education) {
            this.education = education;
        }

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public String getLastLoginTime() {
            return lastLoginTime;
        }

        public void setLastLoginTime(String lastLoginTime) {
            this.lastLoginTime = lastLoginTime;
        }
    }
}
