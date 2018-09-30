package com.oceanli.oceanmooc.app.test.activity;

import com.oceanli.ocean.core.activites.ProxyActivity;
import com.oceanli.ocean.core.delegates.OceanDelegate;
import com.oceanli.oceanmooc.app.test.delegates.TestLaunchDelegate;

public class LaunchActivity extends ProxyActivity {
    @Override
    public OceanDelegate setRootDelegate() {
        return new TestLaunchDelegate();
    }


}
