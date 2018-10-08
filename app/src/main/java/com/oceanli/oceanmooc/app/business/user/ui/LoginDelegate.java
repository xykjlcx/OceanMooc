package com.oceanli.oceanmooc.app.business.user.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gyf.barlibrary.ImmersionBar;
import com.oceanli.ocean.core.delegates.OceanDelegate;
import com.oceanli.ocean.core.event.OceanMessageEvent;
import com.oceanli.ocean.core.net.RestClient;
import com.oceanli.ocean.core.util.storage.OceanPreferences;
import com.oceanli.oceanmooc.app.OmConstant;
import com.oceanli.oceanmooc.app.R;
import com.oceanli.oceanmooc.app.business.user.models.NetUserModel;
import com.oceanli.oceanmooc.app.other.GlideCircleTransform;
import com.oceanli.oceanmooc.app.other.utils.OmUtil;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by ocean on 2018/9/29 Author :  ocean Email  :  348686686@qq.com
 */
public class LoginDelegate extends OceanDelegate {
    @BindView(R.id.et_login_account)
    EditText accountEt;
    @BindView(R.id.et_login_pwd)
    EditText pwdEt;
    @BindView(R.id.tv_login_login)
    TextView loginTv;
    @BindView(R.id.tv_login_register)
    TextView registerTv;
    @BindView(R.id.tv_login_forget)
    TextView forgetTv;
    @BindView(R.id.iv_login_cancel)
    ImageView cancleImg;
    @BindView(R.id.iv_login_head_img)
    ImageView headImg;

    private static final Integer DEF_HEAD_IMG_URL = R.mipmap.ic_launcher;

    @OnClick(R.id.iv_login_cancel)
    public void loginCancleOnClick(){
        pop();
    }

    /**
     * 初始化完成后获取目标页面实例
     * 登录成功后跳转
     */
    private SupportFragment targetSupportFragment = null;

    @OnClick(R.id.tv_login_login)
    public void loginOnClick(View view){
        // todo login
        String accountStr = accountEt.getText().toString();
        String pwdStr = pwdEt.getText().toString();
        if (accountStr.equals("")
                || pwdStr.equals("")){
            OmUtil.toastWarning(_mActivity,"请完整输入！");
            return;
        }
        pwdStr = OmUtil.md5(pwdStr);
        requestLogin(accountStr,pwdStr);
    }

    @OnClick(R.id.tv_login_register)
    public void registerOnClick(View view){
        // todo register
        startWithPop(RegisterDelegate.newInstance());
    }

    @OnClick(R.id.tv_login_forget)
    public void forgetOnClick(View view){
        // todo forget
        start(ForgetDelegate.newInstance());
    }

    public static LoginDelegate newInstance(String targetName) {
        Bundle args = new Bundle();
        args.putString(OmConstant.BUNDLE_TARGET_NAME,targetName);
        LoginDelegate loginDelegate = new LoginDelegate();
        loginDelegate.setArguments(args);
        return loginDelegate;
    }

    public static LoginDelegate newInstance() {
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
        ImmersionBar.setStatusBarView(_mActivity, rootView.findViewById(R.id.view_login_fill));
        handleReceiverData();
        // 加载默认图标
        Glide.with(_mActivity)
                .load(DEF_HEAD_IMG_URL)
                .centerCrop()
                .dontAnimate()
                .transform(new GlideCircleTransform(_mActivity))
                .into(headImg);
    }

    public void handleReceiverData(){
        Bundle arguments = getArguments();
        if (arguments != null){
            targetSupportFragment = OmUtil.getTargetFragment((String) arguments.get(OmConstant.BUNDLE_TARGET_NAME));
        }
    }

    public void requestLogin(String account,String pwd){
        RestClient.builder()
                .url(OmConstant.BASE_URL + OmConstant.REQUEST_URL_POST_LOGIN)
                .params("account",account)
                .params("password",pwd)
                .loader(_mActivity)
                .success(response -> {
                    NetUserModel userModel = OmUtil.getGson().fromJson(response,NetUserModel.class);
                    if (userModel.getCode() == OmConstant.SUCCESS_CODE){
                        NetUserModel.DataBean dataBean = userModel.getData();
                        // todo 缓存用户信息
                        OmUtil.cacheUserData(dataBean,true);
                        OmUtil.toastSuccess(_mActivity,userModel.getMsg());
                        if (targetSupportFragment != null){
                            startWithPop(targetSupportFragment);
                        }else {
                            // 非拦截页面导致的登录，成功登录后pop this
                            pop();
                        }
                        EventBus.getDefault().post(new OceanMessageEvent("updateUserInfo"));

                    }else {
                        OmUtil.toastError(_mActivity,userModel.getMsg());
                    }
                })
                .build()
                .post();
    }


}
