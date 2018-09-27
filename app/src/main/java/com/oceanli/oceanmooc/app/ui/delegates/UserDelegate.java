package com.oceanli.oceanmooc.app.ui.delegates;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.oceanli.ocean.core.delegates.OceanDelegate;
import com.oceanli.oceanmooc.app.R;

/**
 * Created by ocean on 2018/9/25
 * Author :  ocean
 * Email  :  348686686@qq.com
 */
public class UserDelegate extends OceanDelegate {

    private ImageView headIv;
    private TextView signatureTv;

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
        mImmersionBar.setStatusBarView(_mActivity,rootView.findViewById(R.id.view_user_fill));
        headIv = rootView.findViewById(R.id.iv_user_img);
        signatureTv = rootView.findViewById(R.id.tv_user_signature);
        Glide.with(_mActivity)
                .load(R.drawable.head)
                .centerCrop()
                .into(headIv);

    }

}
