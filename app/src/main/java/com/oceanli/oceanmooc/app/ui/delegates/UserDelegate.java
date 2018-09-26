package com.oceanli.oceanmooc.app.ui.delegates;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.oceanli.ocean.core.delegates.OceanDelegate;
import com.oceanli.oceanmooc.app.R;

/**
 * Created by ocean on 2018/9/25
 * Author :  ocean
 * Email  :  348686686@qq.com
 */
public class UserDelegate extends OceanDelegate {


    public static UserDelegate newInstance(){
        Bundle bundle = new Bundle();
        UserDelegate userDelegate = new UserDelegate();
        userDelegate.setArguments(bundle);
        return userDelegate;
    }


    @Override
    public Object setLayout() {
        return R.layout.delegate_user;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initView(rootView);
    }

    public void initView(View rootView){

    }

}
