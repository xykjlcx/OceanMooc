package com.oceanli.oceanmooc.app.ui.delegates;

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
public class CourseSectionDelegate extends OceanDelegate {

    public static CourseSectionDelegate newInstance(){
        CourseSectionDelegate courseSectionDelegate = new CourseSectionDelegate();
        return courseSectionDelegate;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_course_section;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
