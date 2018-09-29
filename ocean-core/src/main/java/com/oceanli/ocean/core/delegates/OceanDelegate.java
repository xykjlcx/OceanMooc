package com.oceanli.ocean.core.delegates;

import android.widget.Toast;

import com.oceanli.ocean.core.R;
import com.oceanli.ocean.core.event.OceanMessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


/**
 * Created by ocean on 2018/6/20
 * Author :  ocean
 * Email  :  348686686@qq.com
 */
public abstract class OceanDelegate extends PermissionCheckerDelegate {

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(OceanMessageEvent event){

    }

}
