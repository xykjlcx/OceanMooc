package com.oceanli.oceanmooc.app.business;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.oceanli.ocean.core.delegates.OceanDelegate;
import com.oceanli.ocean.core.event.OceanMessageEvent;
import com.oceanli.ocean.core.util.timer.BaseTimerTask;
import com.oceanli.ocean.core.util.timer.ITimerListener;
import com.oceanli.oceanmooc.app.R;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ocean on 2018/10/8
 * Author :  ocean
 * Email  :  348686686@qq.com
 */
public class LaunchDelegate extends OceanDelegate implements ITimerListener {
    @BindView(R.id.iv_launch_img)
    ImageView launchImg;
    @BindView(R.id.tv_launch_skip)
    TextView skipLaunchTv;

    private String launch_img_url = "http://oceanbucket.oss-cn-beijing.aliyuncs.com/launch1.jpg";
    private MainDelegate mainDelegate = null;
    private Timer mTimer;
    private Integer count = 3;
    private long firstTime = System.currentTimeMillis();

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
        EventBus.getDefault().post(new OceanMessageEvent("inited"));
        final BaseTimerTask timerTask = new BaseTimerTask(this::onTimer);
        mTimer = new Timer();
        mTimer.schedule(timerTask,0,1000);
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        Glide.with(_mActivity)
                .load(R.mipmap.launch1)
                .centerCrop()
                .into(launchImg);

    }

    @Override
    public void onTimer() {
        getActivity().runOnUiThread(() -> {
            long nowTime = System.currentTimeMillis();
            skipLaunchTv.setText("跳过" + count--);
            if (nowTime - firstTime >= 3000){
                mTimer.cancel();
                startWithPop(mainDelegate);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mTimer != null){
            mTimer.cancel();
        }
    }
}
