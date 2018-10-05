package com.oceanli.oceanmooc.app.business.user.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.oceanli.ocean.core.delegates.OceanDelegate;
import com.oceanli.ocean.core.event.OceanMessageEvent;
import com.oceanli.oceanmooc.app.R;
import com.oceanli.oceanmooc.app.business.user.ui.setting.AboutDelegate;
import com.oceanli.oceanmooc.app.business.user.ui.setting.ModifyPassWordDelegate;
import com.oceanli.oceanmooc.app.business.user.ui.setting.UserInfoDelegate;
import com.oceanli.oceanmooc.app.other.utils.OmUtil;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ocean on 2018/10/5
 * Author :  ocean
 * Email  :  348686686@qq.com
 */
public class SettingDelegate extends OceanDelegate {
    @BindView(R.id.tv_setting_logout)
    TextView logOutTv;
    @BindView(R.id.layout_setting_one)
    RelativeLayout skipUserInfo;
    @BindView(R.id.layout_setting_two)
    RelativeLayout skipModifyPwd;
    @BindView(R.id.layout_setting_three)
    RelativeLayout skipAbout;
    @BindView(R.id.layout_setting_four)
    RelativeLayout openVersion;


    /**
     * 退出登录
     */
    @OnClick(R.id.tv_setting_logout)
    public void logOutOnClick(){
        OmUtil.clearUserInfoCache();
        EventBus.getDefault().post(new OceanMessageEvent("updateUserInfo"));
        pop();
    }

    @OnClick({R.id.layout_setting_one,R.id.layout_setting_two,R.id.layout_setting_three,R.id.layout_setting_four})
    public void skip(View view){
        switch (view.getId()){
            case R.id.layout_setting_one:
                start(UserInfoDelegate.newInstance());
                break;
            case R.id.layout_setting_two:
                start(ModifyPassWordDelegate.newInstance());
                break;
            case R.id.layout_setting_three:
                start(AboutDelegate.newInstance());
                break;
            case R.id.layout_setting_four:
                // todo 2.0版本完善
                OmUtil.toastSuccess(_mActivity,"已是最新版本");
                break;
            default:
                    break;
        }
    }

    @Override
    public void onMessageEvent(OceanMessageEvent event) {
        if (event.getMsg().equals("modifyPwd")){
            startWithPop(LoginDelegate.newInstance());
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_setting;
    }

    public static SettingDelegate newInstance(){
        SettingDelegate settingDelegate = new SettingDelegate();
        return settingDelegate;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mImmersionBar.setStatusBarView(_mActivity, rootView.findViewById(R.id.view_setting_fill));
    }
}
