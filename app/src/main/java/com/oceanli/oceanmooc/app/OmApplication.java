package com.oceanli.oceanmooc.app;

import android.app.Application;

import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.oceanli.ocean.core.app.Ocean;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

/**
 * Created by ocean on 2018/6/19
 * Author :  ocean
 * Email  :  348686686@qq.com
 */
public class OmApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Ocean.init(this)
                .withApiHost(OmConstant.BASE_URL)
                .withIcon(new FontAwesomeModule())
                .configure();
        Logger.addLogAdapter(new AndroidLogAdapter());
    }

}

