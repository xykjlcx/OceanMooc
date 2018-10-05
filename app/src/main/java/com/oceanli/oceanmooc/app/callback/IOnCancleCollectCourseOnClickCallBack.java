package com.oceanli.oceanmooc.app.callback;

import com.oceanli.oceanmooc.app.business.home.models.CourseVoModel;

/**
 * Created by ocean on 2018/10/5
 * Author :  ocean
 * Email  :  348686686@qq.com
 */
public interface IOnCancleCollectCourseOnClickCallBack {
    void onclick(int position, CourseVoModel.DataBean itemData);
}
