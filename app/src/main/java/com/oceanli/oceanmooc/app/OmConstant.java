package com.oceanli.oceanmooc.app;

/**
 * Created by ocean on 2018/9/26 Author :  ocean Email  :  348686686@qq.com
 */ /* 配置相关参数、常量...*/ public class OmConstant {
    public static final Integer SUCCESS_CODE = 0;
    public static final Integer ERROR_CODE = -1;
    public static final String BASE_URL = "http://10.16.216.47:8088";
    public static final String REQUEST_URL_GET_BANNER = "/home/getBannerData";
    public static final String REQUEST_URL_GET_NOTICES = "/home/getNotice";
    public static final String REQUEST_URL_GET_CHOICENESS = "/home/getRecommendCourse";
    public static final String REQUEST_URL_POST_RECOMMEND = "/home/getGuessLikeCourse";
    public static final String REQUEST_URL_POST_MY_COURSES = "/users/getStudyCourses";
    public static final String REQUEST_URL_POST_ALL_COURSES = "/courses/getAllCourseForPage";
    public static final String REQUEST_URL_GET_CLASSIFY = "/courses/getAllClassify";
    public static final String REQUEST_URL_POST_STUDY_COURSE = "/users/addStudyCourse";
    public static final String REQUEST_URL_POST_IS_STUDIED = "/users/isStudyCourse";
    public static final String REQUEST_URL_POST_COURSE_SECTIONS = "/courses/getCourseChapterAndSection";
    public static final String REQUEST_URL_POST_COMMENTS = "/courses/getCourseComments";
    public static final String REQUEST_URL_POST_ADD_COMMENTS = "/courses/addComment";
    public static final String REQUEST_URL_POST_LOGIN = "/auth/login";
    public static final String REQUEST_URL_POST_REGISTER = "/auth/register";
    public static final String REQUEST_URL_POST_FORGET = "/auth/forget";
    public static final String REQUEST_URL_POST_GET_USER_INFO = "/auth/getUserInfo";
    public static final String REQUEST_URL_POST_ADD_COLLECT_COURSE = "/users/addCollectCourse";
    public static final String REQUEST_URL_POST_IS_COLLECT = "/users/isCollect";
    public static final String REQUEST_URL_POST_ALL_COLLECT_COURSE = "/users/getCollectCourses";
    public static final String REQUEST_URL_POST_DELETE_COLLECT = "/users/deleteCollectCourse";

    /**
     * 七牛云图片处理拼接url
     */
    public static final String IMG_COMPRESS_URL = "?imageView2/1/w/900/h/450/q/75|watermark/2/text/T2NlYW5Nb29j/font/5b6u6L2v6ZuF6buR/fontsize/200" +
            "/fill/I0ZGRkZGRg==/dissolve/70/gravity/SouthEast/dx/10/dy/10|imageslim";
    /* 是否登录*/
    public static boolean isLogin = false;

    /* bundle跳转key */
    public static final String BUNDLE_COURSE = "courseModel";
    public static final String BUNDLE_TARGET_NAME = "targetName";

    public static final String SHARED_NAME_USER_INFO = "userInfo";

    /**
     * 用户数据缓存key
     */
    public static class UserInfoKey{
        public static final String ID = "userId";
        public static final String ACCOUNT = "account";
        public static final String EMAIL = "userEmail";
        public static final String REAL_NAME = "realName";
        public static final String SIGNATURE = "signature";
        public static final String PHONE = "phone";
        public static final String HEAM_IMG_URL = "headImgUrl";
        public static final String IS_LOGIN = "isLogin";
        public static final String EDUCATION = "education";
        public static final String GENDER = "gender";
        public static final String LAST_LOGIN_TIME = "lastLoginTime";
    }

    // 登录页转跳页面key
    public static final String SKIP_PARTICULARS = "course_particulars";
    public static final String SKIP_MY_COLLECT = "course_collect";

}
