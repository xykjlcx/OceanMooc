package com.oceanli.oceanmooc.app.business.course.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.oceanli.ocean.core.delegates.OceanDelegate;
import com.oceanli.oceanmooc.app.OmConstant;
import com.oceanli.oceanmooc.app.R;
import com.oceanli.oceanmooc.app.business.home.models.CourseVoModel;

import butterknife.BindView;

/**
 * Created by ocean on 2018/9/28
 * Author :  ocean
 * Email  :  348686686@qq.com
 */
public class CourseIntroDelegate extends OceanDelegate {
    @BindView(R.id.tv_intro_course_desc)
    TextView courseDesc;

    private CourseVoModel.DataBean receiveCourse;

    public static CourseIntroDelegate newInstance(){
        CourseIntroDelegate courseIntroDelegate = new CourseIntroDelegate();
        return courseIntroDelegate;
    }

    public static CourseIntroDelegate newInstance(Bundle args){
        CourseIntroDelegate courseIntroDelegate = new CourseIntroDelegate();
        courseIntroDelegate.setArguments(args);
        return courseIntroDelegate;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_course_intro;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initData();
    }

    public void initData(){
        handleReceiveData();
    }

    public void handleReceiveData(){
        Bundle args = this.getArguments();
        if (args != null){
            receiveCourse = (CourseVoModel.DataBean) args.getSerializable(OmConstant.BUNDLE_COURSE);
            if (receiveCourse != null){
                courseDesc.setText(receiveCourse.getCourseDesc());
            }
        }
    }

}
