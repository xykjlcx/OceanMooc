package com.oceanli.oceanmooc.app.business;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.oceanli.ocean.core.delegates.OceanDelegate;
import com.oceanli.oceanmooc.app.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ocean on 2018/10/8
 * Author :  ocean
 * Email  :  348686686@qq.com
 */
public class LaunchDelegate extends OceanDelegate {
    @BindView(R.id.iv_launch_img)
    ImageView launchImg;
    @BindView(R.id.tv_launch_skip)
    TextView skipLaunchTv;

    private String launch_img_url = "http://oceanbucket.oss-cn-beijing.aliyuncs.com/launch1.jpg";
    private MainDelegate mainDelegate = null;

    @OnClick(R.id.tv_launch_skip)
    public void skipOnClick(){
        startWithPop(mainDelegate);
    }

    public static LaunchDelegate newInstance(){
        LaunchDelegate launchDelegate = new LaunchDelegate();
        return launchDelegate;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_launch;
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        mainDelegate = MainDelegate.newInstance();
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        Glide.with(_mActivity)
                .load(launch_img_url)
                .centerCrop()
                .into(launchImg);
    }
}
