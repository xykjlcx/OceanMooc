package com.oceanli.ocean.core.app;

import android.content.Context;

/**
 * Created by ocean on 2018/6/19 Author :  ocean Email  :  348686686@qq.com
 */
public final class Ocean {
    public static Configurator init(Context context) {
        getConfigurator().getOceanConfigs().put(ConfigType.APPLICATION_CONTEXT.name(), context);
        return Configurator.getInstance();
    }

    public static Configurator getConfigurator() {
        return Configurator.getInstance();
    }

    public static <T> T getConfiguration(Object key) {
        return getConfigurator().getConfiguration(key);
    }

    public static Context getApplicationContext() {
        return (Context) getConfigurator().getOceanConfigs().get(ConfigType.APPLICATION_CONTEXT.name());
    }
}
