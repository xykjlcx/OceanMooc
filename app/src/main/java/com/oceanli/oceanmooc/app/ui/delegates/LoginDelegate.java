package com.oceanli.oceanmooc.app.ui.delegates;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.Animation;

import com.oceanli.ocean.core.delegates.OceanDelegate;
import com.oceanli.oceanmooc.app.OmConfig;
import com.oceanli.oceanmooc.app.R;

import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.DefaultVerticalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

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
