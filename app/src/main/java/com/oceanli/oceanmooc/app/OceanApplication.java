package com.oceanli.oceanmooc.app;

import android.app.Application;

import com.gyf.barlibrary.ImmersionBar;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.oceanli.ocean.core.app.Ocean;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

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
                .withApiHost("http://www.baidu.com/")
                .withIcon(new FontAwesomeModule())
                .configure();
    }

}

