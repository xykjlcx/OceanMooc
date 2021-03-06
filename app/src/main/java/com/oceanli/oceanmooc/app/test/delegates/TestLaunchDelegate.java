package com.oceanli.oceanmooc.app.test.delegates;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.oceanli.ocean.core.delegates.OceanDelegate;
import com.oceanli.ocean.core.ui.launch.ISkipListener;
import com.oceanli.ocean.core.ui.launch.OceanLaunchView;
import com.oceanli.oceanmooc.app.R;
import com.oceanli.oceanmooc.app.business.MainActivity;

import butterknife.BindView;

/**
 * Created by ocean on 2018/7/4
 * Author :  ocean
 * Email  :  348686686@qq.com
 */
public class TestLaunchDelegate extends OceanDelegate {

    @BindView(R.id.launchView)
    OceanLaunchView launchView;

    @Override
    public Object setLayout() {
        return R.layout.delegate_launch_test;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        launchView.setImgResource(new int[]{
                R.drawable.a1,
                R.drawable.a2
        });
        launchView.setSkipListener(new ISkipListener() {
            @Override
            public void onClick() {
                getActivity().finish();
                startActivity(new Intent(getActivity(), MainActivity.class));
            }
        });
        launchView.launch();
    }
}
