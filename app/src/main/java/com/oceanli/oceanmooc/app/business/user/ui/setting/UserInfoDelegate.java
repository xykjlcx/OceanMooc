package com.oceanli.oceanmooc.app.business.user.ui.setting;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.oceanli.ocean.core.delegates.OceanDelegate;
import com.oceanli.ocean.core.event.OceanMessageEvent;
import com.oceanli.ocean.core.net.RestClient;
import com.oceanli.oceanmooc.app.OmConstant;
import com.oceanli.oceanmooc.app.R;
import com.oceanli.oceanmooc.app.business.user.models.NetUserModel;
import com.oceanli.oceanmooc.app.other.utils.OmUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ocean on 2018/10/5
 * Author :  ocean
 * Email  :  348686686@qq.com
 */
public class UserInfoDelegate extends OceanDelegate {
    @BindView(R.id.tv_userinfo_account)
    TextView accountTv;
    @BindView(R.id.et_userinfo_realname)
    EditText realnameEd;
    @BindView(R.id.et_userinfo_signature)
    EditText signatureEd;
    @BindView(R.id.spn_userinfo_education)
    Spinner educationSpinner;
    @BindView(R.id.spn_userinfo_gender)
    Spinner genderSpinner;
    @BindView(R.id.et_userinfo_email)
    EditText emailEd;
    @BindView(R.id.tv_userinfo_update)
    TextView updateUserInfoTv;

    private ArrayAdapter<String> educationAdapter;
    private String[] ecucationlist = {
            "专科",
            "本科",
            "硕士",
            "博士"
    };
    private ArrayAdapter<String> genderAdapter;
    private String[] genderlist = {
            "男",
            "女"
    };
    private Integer def_education = 0;
    private Integer def_gender = 0;
    private NetUserModel.DataBean userDataBean = null;


    @OnClick(R.id.tv_userinfo_update)
    public void updateUserInfoOnClick(){
        String releNameStr = realnameEd.getText().toString();
        String signatureStr = signatureEd.getText().toString();
        String emailStr = emailEd.getText().toString();
        if (releNameStr.equals("")
                || signatureStr.equals("")
                || emailStr.equals("")){
            OmUtil.toastWarning(_mActivity,"请完整填写资料");
            return;
        }
        if (!OmUtil.isEmail(emailStr)){
            OmUtil.toastWarning(_mActivity,"邮箱格式不正确");
            return;
        }
        // todo 请求更新
        HashMap<String,Object> data = new HashMap<>();
        data.put("id",userDataBean.getId());
        data.put("account",userDataBean.getAccount());
        data.put("realName",releNameStr);
        data.put("signature",signatureStr);
        data.put("education",def_education);
        data.put("gender",def_gender);
        data.put("email",emailStr);
        requestUpdateUserInfo(data);
    }

    public void requestUpdateUserInfo(HashMap<String,Object> params){
        RestClient.builder()
                .url(OmConstant.BASE_URL + OmConstant.REQUEST_URL_POST_UPDATE_USER_INFO)
                .params(params)
                .loader(_mActivity)
                .success(response -> {
                    NetUserModel userModel = OmUtil.getGson().fromJson(response,NetUserModel.class);
                    if (userModel.getCode() == OmConstant.SUCCESS_CODE){
                        NetUserModel.DataBean dataBean = userModel.getData();
                        userDataBean = dataBean;
                        fillUI();
                        OmUtil.cacheUserData(dataBean,false);
                        OmUtil.toastSuccess(_mActivity,userModel.getMsg());
                        pop();
                    }else {
                        // 失败
                        OmUtil.toastError(_mActivity,userModel.getMsg());
                    }
                    EventBus.getDefault().post(new OceanMessageEvent("updateUserInfo"));
                })
                .build()
                .post();
    }

    public static UserInfoDelegate newInstance(){
        UserInfoDelegate userInfoDelegate = new UserInfoDelegate();
        return userInfoDelegate;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_user_info;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mImmersionBar.setStatusBarView(_mActivity, rootView.findViewById(R.id.view_userinfo_fill));
        userDataBean = OmUtil.getCacheUserInfo();
        initSpinner();
        fillUI();
    }

    public void fillUI(){
        if (userDataBean != null){
            accountTv.setText(userDataBean.getAccount());
            signatureEd.setText(userDataBean.getSignature());
            realnameEd.setText(userDataBean.getRealName());
            educationSpinner.setSelection(userDataBean.getEducation(),true);
            genderSpinner.setSelection(userDataBean.getGender(),true);
            emailEd.setText(userDataBean.getEmail());
        }
    }

    // 初始化学历、性别选择器
    public void initSpinner(){
        educationAdapter = new ArrayAdapter<>(_mActivity,android.R.layout.simple_list_item_1,ecucationlist);
        genderAdapter = new ArrayAdapter<>(_mActivity,android.R.layout.simple_list_item_1,genderlist);
        educationSpinner.setAdapter(educationAdapter);
        genderSpinner.setAdapter(genderAdapter);
        educationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // 选中学历
                def_education = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        genderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // 选中性别
                def_gender = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

}
