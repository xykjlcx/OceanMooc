package com.oceanli.oceanmooc.app.business.user.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.oceanli.ocean.core.delegates.OceanDelegate;
import com.oceanli.oceanmooc.app.R;

import butterknife.BindView;

/**
 * Created by ocean on 2018/9/25
 * Author :  ocean
 * Email  :  348686686@qq.com
 */
public class UserDelegate extends OceanDelegate {

    @BindView(R.id.view_user_fill)
    View view;

    @BindView(R.id.iv_user_img)
    ImageView headIv;

    @BindView(R.id.tv_user_signature)
    TextView signatureTv;

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
        mImmersionBar.setStatusBarView(_mActivity,view);
        signatureTv.setText("Jass");
        Glide.with(_mActivity)
                .load(R.drawable.head)
                .centerCrop()
                .into(headIv);

    }

}
