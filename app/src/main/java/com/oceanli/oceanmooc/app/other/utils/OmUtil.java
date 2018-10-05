package com.oceanli.oceanmooc.app.other.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.google.gson.Gson;
import com.oceanli.ocean.core.util.storage.OceanPreferences;
import com.oceanli.oceanmooc.app.OmConstant;
import com.oceanli.oceanmooc.app.business.user.models.NetUserModel;
import com.oceanli.oceanmooc.app.business.user.ui.LoginDelegate;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public static void toastSuccess(Context context, String content,int duration) {
        Toasty.success(context, content, duration).show();
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

    /**
     * 缓存用户信息
     * @param dataBean
     * @param cacheLogin
     */
    public static void cacheUserData(NetUserModel.DataBean dataBean,boolean cacheLogin){
        SharedPreferences sharedPreferences = OceanPreferences.getSharedPreferences(OmConstant.SHARED_NAME_USER_INFO);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(OmConstant.UserInfoKey.ID,dataBean.getId());
        editor.putString(OmConstant.UserInfoKey.ACCOUNT,dataBean.getAccount());
        editor.putString(OmConstant.UserInfoKey.EMAIL,dataBean.getEmail());
        editor.putString(OmConstant.UserInfoKey.REAL_NAME,dataBean.getRealName());
        editor.putString(OmConstant.UserInfoKey.SIGNATURE,dataBean.getSignature());
        editor.putString(OmConstant.UserInfoKey.PHONE,dataBean.getPhone());
        editor.putString(OmConstant.UserInfoKey.HEAM_IMG_URL,dataBean.getHeadImg());
        editor.putInt(OmConstant.UserInfoKey.EDUCATION,dataBean.getEducation());
        editor.putInt(OmConstant.UserInfoKey.GENDER,dataBean.getGender());
        editor.putString(OmConstant.UserInfoKey.LAST_LOGIN_TIME,dataBean.getLastLoginTime());
        if (cacheLogin){
            editor.putBoolean(OmConstant.UserInfoKey.IS_LOGIN,true);
            OmConstant.isLogin = true;
        }
        editor.commit();
//        Logger.e("jass:" + sharedPreferences.getString(OmConstant.UserInfoKey.ACCOUNT,""));
    }

    public static NetUserModel.DataBean getCacheUserInfo(){
        SharedPreferences sharedPreferences = OceanPreferences.getSharedPreferences(OmConstant.SHARED_NAME_USER_INFO);
        NetUserModel.DataBean userDataBean = new NetUserModel.DataBean();
        boolean isLogin = sharedPreferences.getBoolean(OmConstant.UserInfoKey.IS_LOGIN,false);
        if (isLogin){
            userDataBean.setId(sharedPreferences.getInt(OmConstant.UserInfoKey.ID,-1));
            userDataBean.setAccount(sharedPreferences.getString(OmConstant.UserInfoKey.ACCOUNT,""));
            userDataBean.setEmail(sharedPreferences.getString(OmConstant.UserInfoKey.EMAIL,""));
            userDataBean.setRealName(sharedPreferences.getString(OmConstant.UserInfoKey.REAL_NAME,""));
            userDataBean.setSignature(sharedPreferences.getString(OmConstant.UserInfoKey.SIGNATURE,""));
            userDataBean.setPhone(sharedPreferences.getString(OmConstant.UserInfoKey.PHONE,""));
            userDataBean.setHeadImg(sharedPreferences.getString(OmConstant.UserInfoKey.HEAM_IMG_URL,""));
            userDataBean.setEducation(sharedPreferences.getInt(OmConstant.UserInfoKey.EDUCATION,-1));
            userDataBean.setGender(sharedPreferences.getInt(OmConstant.UserInfoKey.GENDER,-1));
            userDataBean.setLastLoginTime(sharedPreferences.getString(OmConstant.UserInfoKey.LAST_LOGIN_TIME,""));
            return userDataBean;
        }else {
            return null;
        }
    }

    public static void clearUserInfoCache(){
        clearCache(OmConstant.SHARED_NAME_USER_INFO);
        OmConstant.isLogin = false;
    }

    public static void clearCache(String sharedpreferencesName){
        SharedPreferences sharedPreferences = OceanPreferences.getSharedPreferences(sharedpreferencesName);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }

    //判断email格式是否正确
    public static boolean isEmail(String email) {
        final String str = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }

}
