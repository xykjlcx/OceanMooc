package com.oceanli.oceanmooc.example;

import android.app.Application;

import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.oceanli.ocean.core.app.Ocean;

/**
 * Created by ocean on 2018/6/19
 * Author :  ocean
 * Email  :  348686686@qq.com
 */
public class OceanApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Ocean.init(this)
                .withApiHost("http://localhost:8080/")
                .withIcon(new FontAwesomeModule())
                .configure();
    }
}
