package com.oceanli.ocean.core.util.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.oceanli.ocean.core.app.Ocean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ocean on 2018/7/5
 * Author :  ocean
 * Email  :  348686686@qq.com
 */
public class OceanPreferences {

    private static final Map<String,SharedPreferences> SP_MAP = new HashMap<>();

    public static SharedPreferences getSharedPreferences(String name,Context context) {
        SharedPreferences sharedPreferences = null;
        if (name == null
                || name.isEmpty()) {
            throw new IllegalArgumentException("SharedPreferences Name is Null!");
        }
        if (!SP_MAP.containsKey(name)){
            sharedPreferences = context.getSharedPreferences(name,0);
            SP_MAP.put(name,sharedPreferences);
        }
        sharedPreferences = SP_MAP.get(name);
        return sharedPreferences;
    }

    public static SharedPreferences getSharedPreferences(String name) {
        Context context = Ocean.getApplicationContext();
        if (context != null)
            return OceanPreferences.getSharedPreferences(name,context);
        else
            throw new NullPointerException("获取SharedPreferences失败，ApplicationContext为空！");
    }


}
