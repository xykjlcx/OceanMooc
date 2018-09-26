package com.oceanli.oceanmooc.app.delegates;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.oceanli.ocean.core.delegates.OceanDelegate;
import com.oceanli.oceanmooc.app.R;

/**
 * Created by ocean on 2018/9/25
 * Author :  ocean
 * Email  :  348686686@qq.com
 */
public class HomeMyCourseDelagate extends OceanDelegate {

    public static HomeMyCourseDelagate newInstance(){
        Bundle bundle = new Bundle();
        HomeMyCourseDelagate homeMyCourseDelagate = new HomeMyCourseDelagate();
        homeMyCourseDelagate.setArguments(bundle);
        return homeMyCourseDelagate;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_home_my_course;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initView(rootView);
    }

    public void initView(View rootView){

    }

}
