package com.oceanli.oceanmooc.app.other.utils;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;
import com.oceanli.oceanmooc.app.OmConstant;
import com.oceanli.oceanmooc.app.business.user.ui.LoginDelegate;

import es.dmoral.toasty.Toasty;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by ocean on 2018/9/29 Author :  ocean Email  :  348686686@qq.com
 */
public class OmUtil {
    /**
     * 若已经登录则正常跳转 否则跳转至登录页 @param targetFragment 目标跳转页面 @return
     */
    public static SupportFragment isLoginSkip(SupportFragment targetFragment) {
        if (OmConstant.isLogin) return targetFragment;
        else return LoginDelegate.newInstance();
    }

    public static class Holder {
        public static final Gson GSON = new Gson();
    }

    public static Gson getGson() {
        return Holder.GSON;
    }

    public static void toastSuccess(Context context, String content) {
        Toasty.success(context, content, Toast.LENGTH_SHORT).show();
    }

    public static void toastError(Context context, String content) {
        Toasty.error(context, content, Toast.LENGTH_SHORT).show();
    }

    public static void toastInfo(Context context, String content) {
        Toasty.info(context, content, Toast.LENGTH_SHORT).show();
    }

    public static void toastWarning(Context context, String content) {
        Toasty.warning(context, content, Toast.LENGTH_SHORT).show();
    }
}
