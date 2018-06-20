package com.oceanli.ocean.core.activites;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;

import com.oceanli.ocean.core.R;
import com.oceanli.ocean.core.delegates.OceanDelegate;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by ocean on 2018/6/20
 * Author :  ocean
 * Email  :  348686686@qq.com
 */
public abstract class ProxyActivity extends SupportActivity {

    public abstract OceanDelegate setRootDelegate();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContainer(savedInstanceState);
    }

    private void initContainer(@Nullable Bundle savedInstanceState) {
        final ContentFrameLayout contentFrameLayout = new ContentFrameLayout(this);
        contentFrameLayout.setId(R.id.delegate_container);
        setContentView(contentFrameLayout);
        if (savedInstanceState == null){
            loadRootFragment(R.id.delegate_container,setRootDelegate());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
        System.runFinalization();
    }
}
