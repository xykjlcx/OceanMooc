package com.oceanli.oceanmooc.example.activity;

import android.util.Log;

import com.oceanli.ocean.core.activites.ProxyActivity;
import com.oceanli.ocean.core.delegates.OceanDelegate;
import com.oceanli.oceanmooc.example.delegates.ExampleDelegate;

public class ExampleActivity extends ProxyActivity {
    @Override
    public OceanDelegate setRootDelegate() {
        return new ExampleDelegate();
    }
}
