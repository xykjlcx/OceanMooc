package com.oceanli.oceanmooc.app.other.utils;

import com.oceanli.oceanmooc.app.OmConfig;
import com.oceanli.oceanmooc.app.business.user.ui.LoginDelegate;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by ocean on 2018/9/29
 * Author :  ocean
 * Email  :  348686686@qq.com
 */
public class OmUtil {

    /**
     * 若已经登录则正常跳转
     * 否则跳转至登录页
     * @param targetFragment 目标跳转页面
     * @return
     */
    public static SupportFragment isLoginSkip(SupportFragment targetFragment){
        if (OmConfig.isLogin)
            return targetFragment;
        else
            return LoginDelegate.newInstance();
    }

}
