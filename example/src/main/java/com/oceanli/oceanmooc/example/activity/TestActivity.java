package com.oceanli.oceanmooc.example.activity;

import com.oceanli.ocean.core.activites.ProxyActivity;
import com.oceanli.ocean.core.delegates.OceanDelegate;
import com.oceanli.oceanmooc.example.delegates.ExampleDelegate;

/**
 * Created by ocean on 2018/7/4
 * Author :  ocean
 * Email  :  348686686@qq.com
 */
public class TestActivity extends ProxyActivity {
    @Override
    public OceanDelegate setRootDelegate() {
        return new ExampleDelegate();
    }
}
