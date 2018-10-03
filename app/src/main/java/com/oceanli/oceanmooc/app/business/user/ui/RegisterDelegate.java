package com.oceanli.oceanmooc.app.business.user.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.oceanli.ocean.core.delegates.OceanDelegate;
import com.oceanli.oceanmooc.app.R;
import com.oceanli.oceanmooc.app.other.utils.OmUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ocean on 2018/10/3
 * Author :  ocean
 * Email  :  348686686@qq.com
 */
public class RegisterDelegate extends OceanDelegate {
    @BindView(R.id.et_register_account)
    EditText accountEt;
    @BindView(R.id.et_register_pwd)
    EditText pwdEt;
    @BindView(R.id.et_register_email)
    EditText emailEt;
    @BindView(R.id.tv_register_register)
    TextView registerTv;
    @BindView(R.id.tv_register_login)
    TextView skipLoginTv;

    @OnClick(R.id.tv_register_register)
    public void registerTvOnClick(View view){
        // 确定注册
        String accountStr = accountEt.getText().toString();
        String pwdStr = pwdEt.getText().toString();
        String emailStr = emailEt.getText().toString();
        if (accountStr.equals("")
                || pwdStr.equals("")
                || emailStr.equals("")){
            OmUtil.toastWarning(_mActivity,"请完整填写注册信息!");
            return;
        }
        // 调用注册接口
    }

    @OnClick(R.id.tv_register_login)
    public void skipLoginTvOnClick(View view){
        // 跳转登录
        startWithPop(LoginDelegate.newInstance());
    }

    public static RegisterDelegate newInstance(){
        RegisterDelegate registerDelegate = new RegisterDelegate();
        return registerDelegate;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_register;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mImmersionBar.setStatusBarView(_mActivity, rootView.findViewById(R.id.view_register_fill));
    }
}
