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
    @BindView(R.id.tv_user_name)
    TextView userNameTv;
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
    @BindView(R.id.layout_user_skip_collect)
    RelativeLayout skipCollect;
    @BindView(R.id.layout_user_skip_setting)
    RelativeLayout skipSetting;
    @BindView(R.id.tv_user_study_course_count)
    TextView userStudyCount;

    /**
     * 跳转我的收藏页
     */
    @OnClick({R.id.layout_user_skip_collect,R.id.layout_user_skip_comment,R.id.layout_user_skip_idea_back,R.id.layout_user_skip_setting})
    public void skipOnClick(View view){
        switch (view.getId()){
            case R.id.layout_user_skip_collect:
                ((MainDelegate)getParentFragment()).startBrotherFragment(OmUtil.isLoginSkip(OmConstant.SKIP_MY_COLLECT,MyCollectDelegate.newInstance()));
                break;
            case R.id.layout_user_skip_comment:
                ((MainDelegate)getParentFragment()).startBrotherFragment(OmUtil.isLoginSkip(OmConstant.SKIP_MY_COMMENT,MyCommentDelegate.newInstance()));
                break;
            case R.id.layout_user_skip_idea_back:
                ((MainDelegate)getParentFragment()).startBrotherFragment(OmUtil.isLoginSkip(OmConstant.SKIP_IDEA_BACK,IdeaBackDelegate.newInstance()));
                break;
            case R.id.layout_user_skip_setting:
                ((MainDelegate)getParentFragment()).startBrotherFragment(OmUtil.isLoginSkip(OmConstant.SKIP_SETTING,SettingDelegate.newInstance()));
                break;
            default:
                break;
        }
    }


    public static final Integer REQUEST_CODE = 3486;
    private NetUserModel.DataBean userDataBean;

    @OnClick(R.id.tv_user_login_and_register)
    public void skipLoginOnClick(){
        if (userDataBean == null){
            ((MainDelegate)getParentFragment()).startBrotherFragmentForResult(LoginDelegate.newInstance(),REQUEST_CODE);
        }
    }

    @Override
    public void onMessageEvent(OceanMessageEvent event) {
        if (event.getMsg().equals("updateUserInfo")){
            userDataBean = OmUtil.getCacheUserInfo();
            getCacheUserInfoUpdateUI();
        }else if (event.getMsg().equals("updateMyCourse")){
            // 我的课程数据更新
            int count = (int) event.getData();
            userStudyCount.setText("" + count);
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
        userDataBean = OmUtil.getCacheUserInfo();
        if (userDataBean != null){
            requestUserInfo(userDataBean.getId());
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
            if (userDataBean != null){
                requestUserInfo(userDataBean.getId());
            }
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
                        // 应用内更新
                        userDataBean = dataBean;
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
        if (userDataBean != null){
            // 已经登录
            // 隐藏未登录布局、显示头像、签名...
            noLoginLayout.setVisibility(View.GONE);
            loginedLayout.setVisibility(View.VISIBLE);
            Glide.with(_mActivity)
                    .load(userDataBean.getHeadImg())
                    .centerCrop()
                    .dontAnimate()
                    .into(headIv);
            signatureTv.setText(userDataBean.getSignature());
            userNameTv.setText(userDataBean.getAccount());
        }else {
            // 显示登录布局
            noLoginLayout.setVisibility(View.VISIBLE);
            loginedLayout.setVisibility(View.GONE);
        }

    }

}
