package com.oceanli.oceanmooc.example.delegates;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.oceanli.ocean.core.delegates.OceanDelegate;
import com.oceanli.oceanmooc.example.R;

/**
 * Created by ocean on 2018/6/20
 * Author :  ocean
 * Email  :  348686686@qq.com
 */
public class ExampleDelegate extends OceanDelegate {

    @Override
    public Object setLayout() {
        Log.e("ocean", "setLayout: " + "111111111111111111" );
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
