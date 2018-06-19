package com.oceanli.ocean.core.app;

import android.content.Context;

import java.util.HashMap;
import java.util.WeakHashMap;

/**
 * Created by ocean on 2018/6/19
 * Author :  ocean
 * Email  :  348686686@qq.com
 */
public final class Ocean {

    public static Configurator init(Context context) {
        getConfiguratorsMap().put(ConfigType.APPLICATION_CONTEXT.name(),context);
        return Configurator.getInstance();
    }

    private static Configurator getConfigurator() {
        return Configurator.getInstance();
    }

    private static HashMap<String,Object> getConfiguratorsMap() {
        return getConfigurator().getOceanConfigs();
    }

    public static Context getApplicationContext() {
        return (Context) getConfiguratorsMap().get(ConfigType.APPLICATION_CONTEXT.name());
    }

}
