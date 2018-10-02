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

    /**
     * 七牛云图片处理拼接url
     */
    public static final String IMG_COMPRESS_URL = "?imageView2/1/w/900/h/450/q/75|watermark/2/text/T2NlYW5Nb29j/font/5b6u6L2v6ZuF6buR/fontsize/200" +
            "/fill/I0ZGRkZGRg==/dissolve/70/gravity/SouthEast/dx/10/dy/10|imageslim";
    /* 是否登录*/
    public static boolean isLogin = false;

    /* bundle跳转key */
    public static final String BUNDLE_COURSE = "courseModel";

}
