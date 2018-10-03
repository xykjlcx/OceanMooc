package com.oceanli.oceanmooc.app.business.user.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.oceanli.ocean.core.delegates.OceanDelegate;
import com.oceanli.oceanmooc.app.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ocean on 2018/10/4
 * Author :  ocean
 * Email  :  348686686@qq.com
 */
public class ForgetDelegate extends OceanDelegate {

    @BindView(R.id.iv_forget_cancel)
    ImageView cancleImg;

    @OnClick(R.id.iv_forget_cancel)
    public void forgetCancleOnClick(){
        pop();
    }

    public static ForgetDelegate newInstance(){
        ForgetDelegate forgetDelegate = new ForgetDelegate();
        return forgetDelegate;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_forget;
    }

    @Override
    public boolean isSwip() {
        return true;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mImmersionBar.setStatusBarView(_mActivity, rootView.findViewById(R.id.view_forget_fill));
    }
}
