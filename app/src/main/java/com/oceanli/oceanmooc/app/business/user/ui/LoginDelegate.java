package com.oceanli.oceanmooc.app.business.user.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.oceanli.ocean.core.delegates.OceanDelegate;
import com.oceanli.oceanmooc.app.R;

/**
 * Created by ocean on 2018/9/29
 * Author :  ocean
 * Email  :  348686686@qq.com
 */
public class LoginDelegate extends OceanDelegate {


    public static LoginDelegate newInstance(){
        LoginDelegate loginDelegate = new LoginDelegate();
        return loginDelegate;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_login;
    }

    @Override
    public boolean isSwip() {
        return true;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mImmersionBar.setStatusBarView(_mActivity,rootView.findViewById(R.id.view_login_fill));

    }
}
