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
public class AboutDelegate extends OceanDelegate {

    public static AboutDelegate newInstance(){
        AboutDelegate aboutDelegate = new AboutDelegate();
        return aboutDelegate;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_about;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mImmersionBar.setStatusBarView(_mActivity, rootView.findViewById(R.id.view_about_fill));
    }
}
