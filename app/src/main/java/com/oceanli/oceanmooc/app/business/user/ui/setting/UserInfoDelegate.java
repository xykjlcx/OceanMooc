package com.oceanli.oceanmooc.app.business.user.ui.setting;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.oceanli.ocean.core.delegates.OceanDelegate;
import com.oceanli.oceanmooc.app.R;

/**
 * Created by ocean on 2018/10/5
 * Author :  ocean
 * Email  :  348686686@qq.com
 */
public class UserInfoDelegate extends OceanDelegate {

    public static UserInfoDelegate newInstance(){
        UserInfoDelegate userInfoDelegate = new UserInfoDelegate();
        return userInfoDelegate;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_user_info;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
