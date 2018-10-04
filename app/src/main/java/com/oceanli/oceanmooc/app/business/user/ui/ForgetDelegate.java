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
 * Created by ocean on 2018/10/4
 * Author :  ocean
 * Email  :  348686686@qq.com
 */
public class ForgetDelegate extends OceanDelegate {

    @BindView(R.id.iv_forget_cancel)
    ImageView cancleImg;
    @BindView(R.id.et_forget_email)
    EditText emailEt;
    @BindView(R.id.tv_forget_ok)
    TextView senForgetTv;

    @OnClick(R.id.tv_forget_ok)
    public void forgetOkOnClick(){
        String emailStr = emailEt.getText().toString();
        if (emailStr.equals("")){
            OmUtil.toastWarning(_mActivity,"请谨慎填写接收新密码的邮箱");
            return;
        }
        if (!OmUtil.isEmail(emailStr)){
            OmUtil.toastWarning(_mActivity,"邮箱格式不正确...");
            return;
        }
        requestForget(emailStr);
    }

    public void requestForget(String email){
        RestClient.builder()
                .url(OmConstant.BASE_URL + OmConstant.REQUEST_URL_POST_FORGET)
                .loader(_mActivity)
                .params("email",email)
                .success(response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        if (jsonObject.getInt("code") == OmConstant.SUCCESS_CODE){
                            OmUtil.toastSuccess(_mActivity,jsonObject.getString("msg"));
                            pop();
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
