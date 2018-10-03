package com.oceanli.oceanmooc.app.other.utils;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;
import com.oceanli.oceanmooc.app.OmConstant;
import com.oceanli.oceanmooc.app.business.user.ui.LoginDelegate;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import es.dmoral.toasty.Toasty;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by ocean on 2018/9/29 Author :  ocean Email  :  348686686@qq.com
 */
public class OmUtil {

    private static final Map<String,SupportFragment> SUPPORT_FRAGMENT_MAP = new HashMap<>();

    public static SupportFragment getTargetFragment(String targetKey){
        return SUPPORT_FRAGMENT_MAP.get(targetKey);
    }

    /**
     * 若已经登录则正常跳转 否则跳转至登录页 @param targetFragment 目标跳转页面 @return
     */
    public static SupportFragment isLoginSkip(String targetName,SupportFragment targetFragment) {
        SUPPORT_FRAGMENT_MAP.put(targetName,targetFragment);
        if (OmConstant.isLogin)
            return targetFragment;
        else
            return LoginDelegate.newInstance(targetName);
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

    // md5加密
    public static String md5(String string) {
        if(string == null
                || string.equals("")){
            return "";
        }
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(string.getBytes());
            String result = "";
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result += temp;
            }
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

}
