package com.oceanli.oceanmooc.app.business.course.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.oceanli.ocean.core.delegates.OceanDelegate;
import com.oceanli.oceanmooc.app.R;

/**
 * Created by ocean on 2018/9/28
 * Author :  ocean
 * Email  :  348686686@qq.com
 */
public class CourseIntroDelegate extends OceanDelegate {

    public static CourseIntroDelegate newInstance(){
        CourseIntroDelegate courseIntroDelegate = new CourseIntroDelegate();
        return courseIntroDelegate;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_course_intro;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
