package com.oceanli.oceanmooc.app.business.user.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.oceanli.ocean.core.delegates.OceanDelegate;
import com.oceanli.ocean.core.event.OceanMessageEvent;
import com.oceanli.ocean.core.net.RestClient;
import com.oceanli.ocean.core.util.storage.OceanPreferences;
import com.oceanli.oceanmooc.app.OmConstant;
import com.oceanli.oceanmooc.app.R;
import com.oceanli.oceanmooc.app.business.MainDelegate;
import com.oceanli.oceanmooc.app.business.user.models.NetUserModel;
import com.oceanli.oceanmooc.app.other.utils.OmUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ocean on 2018/9/25 Author :  ocean Email  :  348686686@qq.com
 */
public class UserDelegate extends OceanDelegate {
    @BindView(R.id.view_user_fill)
    View view;
    @BindView(R.id.iv_user_img)
    ImageView headIv;
    @BindView(R.id.tv_user_signature)
    TextView signatureTv;
    @BindView(R.id.layout_user_nologin)
    RelativeLayout noLoginLayout;
    @BindView(R.id.layout_user_login)
    RelativeLayout loginedLayout;
    @BindView(R.id.tv_user_login_and_register)
    TextView skipLoginTv;
    @BindView(R.id.refresh_user)
    SmartRefreshLayout mRefreshLayout;

    private SharedPreferences sharedPreferences;
    private boolean isLogin = false;
    private static Integer userId = -1;
    public static final Integer REQUEST_CODE = 3486;

    @OnClick(R.id.tv_user_login_and_register)
    public void skipLoginOnClick(){
        if (!isLogin){
            ((MainDelegate)getParentFragment()).startBrotherFragmentForResult(LoginDelegate.newInstance(),REQUEST_CODE);
        }
    }

    @Override
    public void onMessageEvent(OceanMessageEvent event) {
        if (event.getMsg().equals("updateUserInfo")){
            getCacheUserInfoUpdateUI();
        }
    }

    public static UserDelegate newInstance() {
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
        sharedPreferences = OceanPreferences.getSharedPreferences(OmConstant.SHARED_NAME_USER_INFO);
        userId = sharedPreferences.getInt(OmConstant.UserInfoKey.ID,-1);
        if (userId != -1){
            requestUserInfo(userId);
        }else {
            getCacheUserInfoUpdateUI();
        }
    }

    public void initView(View rootView) {
        mImmersionBar.setStatusBarView(_mActivity, view);
        initRefresh();
    }

    public void initRefresh(){
        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            requestUserInfo(userId);
           refreshLayout.finishRefresh();
        });
    }

    public void requestUserInfo(int userId){
        RestClient.builder()
                .url(OmConstant.BASE_URL + OmConstant.REQUEST_URL_POST_GET_USER_INFO)
                .params("userId",userId)
                .success(response -> {
                    NetUserModel userModel = OmUtil.getGson().fromJson(response,NetUserModel.class);
                    if (userModel.getCode() == OmConstant.SUCCESS_CODE){
                        // 更新userinfo缓存
                        NetUserModel.DataBean dataBean = userModel.getData();
                        OmUtil.cacheUserData(dataBean,false);
                    }else {
                        // 获取用户信息失败
                    }
                    // 读取缓存，更新ui
                    getCacheUserInfoUpdateUI();
                })
                .build()
                .post();
    }

    public void getCacheUserInfoUpdateUI(){
        SharedPreferences sharedPreferences = OceanPreferences.getSharedPreferences(OmConstant.SHARED_NAME_USER_INFO);
        if (sharedPreferences != null){
            String headImgUrl = sharedPreferences.getString(OmConstant.UserInfoKey.HEAM_IMG_URL,"");
            String signature = sharedPreferences.getString(OmConstant.UserInfoKey.SIGNATURE,"");
            isLogin = sharedPreferences.getBoolean(OmConstant.UserInfoKey.IS_LOGIN,false);
            if (isLogin){
                // 已经登录
                // 隐藏未登录布局、显示头像、签名...
                noLoginLayout.setVisibility(View.GONE);
                loginedLayout.setVisibility(View.VISIBLE);
                Glide.with(_mActivity)
                        .load(headImgUrl)
                        .centerCrop()
                        .dontAnimate()
                        .into(headIv);
                signatureTv.setText(signature);
            }else {
                // 显示登录布局
                noLoginLayout.setVisibility(View.VISIBLE);
                loginedLayout.setVisibility(View.GONE);
            }
        }
    }

}
