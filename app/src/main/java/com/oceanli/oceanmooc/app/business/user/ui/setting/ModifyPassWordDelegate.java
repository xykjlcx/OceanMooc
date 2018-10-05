package com.oceanli.oceanmooc.app.business.user.ui.setting;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.oceanli.ocean.core.delegates.OceanDelegate;
import com.oceanli.ocean.core.event.OceanMessageEvent;
import com.oceanli.ocean.core.net.RestClient;
import com.oceanli.oceanmooc.app.OmConstant;
import com.oceanli.oceanmooc.app.R;
import com.oceanli.oceanmooc.app.business.user.models.NetUserModel;
import com.oceanli.oceanmooc.app.other.utils.OmUtil;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ocean on 2018/10/5
 * Author :  ocean
 * Email  :  348686686@qq.com
 */
public class ModifyPassWordDelegate extends OceanDelegate {
    @BindView(R.id.et_modify_pwd_oldpwd)
    EditText oldPwdEd;
    @BindView(R.id.et_modify_pwd_newpwd)
    EditText newPwdEd;
    @BindView(R.id.et_modify_pwd_affirm)
    EditText affimPwdEd;
    @BindView(R.id.tv_modify_pwd_ok)
    TextView updatePwdOkTv;

    private NetUserModel.DataBean userDataBean = null;

    @OnClick(R.id.tv_modify_pwd_ok)
    public void updatePwdOnClick(){
        String oldStr = oldPwdEd.getText().toString();
        String newStr = newPwdEd.getText().toString();
        String affimStr = affimPwdEd.getText().toString();
        if (oldStr.equals("")
                || newStr.equals("")
                || affimStr.equals("")){
            OmUtil.toastWarning(_mActivity,"请完整输入");
            return;
        }
        if (!newStr.equals(affimStr)){
            OmUtil.toastWarning(_mActivity,"两次输入的密码不一致");
            return;
        }
        // todo 网络请求修改密码
        // 成功后
        if (userDataBean != null){
            requestModifyPwd(userDataBean.getId(),OmUtil.md5(oldStr),OmUtil.md5(newStr));
        }
    }

    public static ModifyPassWordDelegate newInstance(){
        ModifyPassWordDelegate modifyPassWordDelegate = new ModifyPassWordDelegate();
        return modifyPassWordDelegate;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_modify_pwd;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mImmersionBar.setStatusBarView(_mActivity, rootView.findViewById(R.id.view_modify_pwd_fill));
        userDataBean = OmUtil.getCacheUserInfo();
    }

    public void requestModifyPwd(int userId,String oldPwd,String newPwd){
        RestClient.builder()
                .url(OmConstant.BASE_URL + OmConstant.REQUEST_URL_POST_MODIFY_PASSWORD)
                .params("userId",userId)
                .params("oldPwd",oldPwd)
                .params("newPwd",newPwd)
                .loader(_mActivity)
                .success(response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        if (jsonObject.getInt("code") == OmConstant.SUCCESS_CODE){
                            OmUtil.toastSuccess(_mActivity,jsonObject.getString("msg"), Toast.LENGTH_LONG);
                            OmUtil.clearUserInfoCache();
                            pop();
                            EventBus.getDefault().post(new OceanMessageEvent("updateUserInfo"));
                            EventBus.getDefault().post(new OceanMessageEvent("modifyPwd"));
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

}
