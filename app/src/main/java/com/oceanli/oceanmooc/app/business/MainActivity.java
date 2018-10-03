package com.oceanli.oceanmooc.app.business;

import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.oceanli.ocean.core.activites.ProxyActivity;
import com.oceanli.ocean.core.delegates.OceanDelegate;
import com.oceanli.ocean.core.util.storage.OceanPreferences;
import com.oceanli.oceanmooc.app.OmConstant;
import com.oceanli.oceanmooc.app.business.MainDelegate;
import com.oceanli.oceanmooc.app.other.utils.OmUtil;

import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * Created by ocean on 2018/9/25 Author :  ocean Email  :  348686686@qq.com
 */
public class MainActivity extends ProxyActivity {
    @Override
    public OceanDelegate setRootDelegate() {
        return MainDelegate.newInstance();
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        OmConstant.isLogin = isLogin();
    }

    public boolean isLogin(){
        SharedPreferences sharedPreferences = OceanPreferences.getSharedPreferences(OmConstant.SHARED_NAME_USER_INFO);
        return sharedPreferences.getBoolean(OmConstant.UserInfoKey.IS_LOGIN,false);
    }

}
