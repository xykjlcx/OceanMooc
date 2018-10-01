package com.oceanli.ocean.core.delegates;

import android.widget.Toast;

import com.oceanli.ocean.core.R;
import com.oceanli.ocean.core.event.OceanMessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by ocean on 2018/6/20 Author :  ocean Email  :  348686686@qq.com
 */
public abstract class OceanDelegate extends PermissionCheckerDelegate {
    /**
     * 初始化eventbus
     */
    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    /**
     * 解绑eventbus
     */
    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    /**
     * 必须所有支持eventbus的类 都要至少拥有一个@Subscribe注解的方法 这里提供一个空实现，子类有需要时直接重写即可 @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(OceanMessageEvent event) {
    }
}
