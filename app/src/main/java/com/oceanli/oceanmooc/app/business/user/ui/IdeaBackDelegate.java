package com.oceanli.oceanmooc.app.business.user.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.oceanli.ocean.core.delegates.OceanDelegate;
import com.oceanli.oceanmooc.app.R;

/**
 * Created by ocean on 2018/10/6
 * Author :  ocean
 * Email  :  348686686@qq.com
 */
public class IdeaBackDelegate extends OceanDelegate {


    public static IdeaBackDelegate newInstance(){
        IdeaBackDelegate ideaBackDelegate = new IdeaBackDelegate();
        return ideaBackDelegate;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_idea_back;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mImmersionBar.setStatusBarView(_mActivity, rootView.findViewById(R.id.view_my_idea_back_fill));
    }
}
