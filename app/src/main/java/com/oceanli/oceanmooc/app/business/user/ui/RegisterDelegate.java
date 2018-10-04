package com.oceanli.oceanmooc.app.business.user.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.oceanli.ocean.core.delegates.OceanDelegate;
import com.oceanli.ocean.core.net.RestClient;
import com.oceanli.oceanmooc.app.OmConstant;
import com.oceanli.oceanmooc.app.R;
import com.oceanli.oceanmooc.app.other.utils.OmUtil;

import org.json.JSONException;
import org.json.JSONObject;

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
    @BindView(R.id.iv_register_cancel)
    ImageView cancleImg;

    @OnClick(R.id.iv_register_cancel)
    public void registerCancleOnClick(){
        pop();
    }

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
        if (!OmUtil.isEmail(emailStr)){
            OmUtil.toastWarning(_mActivity,"邮箱格式不正确");
            return;
        }
        // 调用注册接口
        requestRegister(accountStr,pwdStr,emailStr);
    }

    public void requestRegister(String account,String password,String email){
        password = OmUtil.md5(password);
        RestClient.builder()
                .url(OmConstant.BASE_URL + OmConstant.REQUEST_URL_POST_REGISTER)
                .loader(_mActivity)
                .params("account",account)
                .params("password",password)
                .params("email",email)
                .success(response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        if (jsonObject.getInt("code") == OmConstant.SUCCESS_CODE){
                            OmUtil.toastSuccess(_mActivity,"注册成功，稍后请接收邮件激活账户");
                            startWithPop(LoginDelegate.newInstance());
                        }else {
                            OmUtil.toastError(_mActivity,jsonObject.getString("msg"));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                })
                .build()
                .post();
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
    public boolean isSwip() {
        return true;
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
