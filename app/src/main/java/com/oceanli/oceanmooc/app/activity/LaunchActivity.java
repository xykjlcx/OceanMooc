package com.oceanli.oceanmooc.app.activity;

import android.util.Log;

import com.oceanli.ocean.core.activites.ProxyActivity;
import com.oceanli.ocean.core.delegates.OceanDelegate;
import com.oceanli.oceanmooc.app.delegates.ExampleDelegate;
import com.oceanli.oceanmooc.app.delegates.TestLaunchDelegate;

public class LaunchActivity extends ProxyActivity {
    @Override
    public OceanDelegate setRootDelegate() {
        return new TestLaunchDelegate();
    }


}
