package com.oceanli.oceanmooc.app.ui.activity;

import com.oceanli.ocean.core.activites.ProxyActivity;
import com.oceanli.ocean.core.delegates.OceanDelegate;
import com.oceanli.oceanmooc.app.ui.delegates.TestLaunchDelegate;

public class LaunchActivity extends ProxyActivity {
    @Override
    public OceanDelegate setRootDelegate() {
        return new TestLaunchDelegate();
    }


}
