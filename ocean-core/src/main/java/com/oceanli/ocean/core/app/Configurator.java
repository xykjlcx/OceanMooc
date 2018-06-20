package com.oceanli.ocean.core.app;

import android.util.Log;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.WeakHashMap;

import okhttp3.Interceptor;

/**
 * Created by ocean on 2018/6/19
 * Author :  ocean
 * Email  :  348686686@qq.com
 */
public class Configurator {

    private static final HashMap<String,Object> OCEAN_CONFIGS = new HashMap<>();
    private static final ArrayList <IconFontDescriptor> ICONS = new ArrayList();
    private static final ArrayList<Interceptor> INTERCEPTORS = new ArrayList<>();

    private Configurator() {
        OCEAN_CONFIGS.put(ConfigType.CONFIG_READY.name(),false);
        OCEAN_CONFIGS.put(ConfigType.INTERCEPTOR.name(),INTERCEPTORS);
    }

    /**
     * 静态内部类单例
     */
    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }

    static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    final HashMap<String,Object> getOceanConfigs() {
        return OCEAN_CONFIGS;
    }

    public final void configure() {
        initIcons();
        OCEAN_CONFIGS.put(ConfigType.CONFIG_READY.name(),true);
    }

    public final Configurator withApiHost(String host) {
        OCEAN_CONFIGS.put(ConfigType.API_HOST.name(),host);
        return this;
    }

    public final Configurator withIntercepter(Interceptor intercepter) {
        INTERCEPTORS.add(intercepter);
        OCEAN_CONFIGS.put(ConfigType.INTERCEPTOR.name(),INTERCEPTORS);
        return this;
    }

    private  void initIcons() {
        if (ICONS.size() > 0){
            final Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for (int i = 1; i < ICONS.size(); i++) {
                initializer.with(ICONS.get(i));
            }
        }
    }

    public final Configurator withIcon(IconFontDescriptor descriptor) {
        ICONS.add(descriptor);
        return this;
    }

    public void checkConfiguration() {
        final boolean isReady = (boolean) OCEAN_CONFIGS.get(ConfigType.CONFIG_READY.name());
        if (!isReady){
            throw new RuntimeException("Configurator 配置未完成 ！");
        }
    }

    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Object key) {
        checkConfiguration();
        final Object value = OCEAN_CONFIGS.get(key);
        if (value == null) {
            throw new NullPointerException(key.toString() + " IS NULL");
        }
        return (T) OCEAN_CONFIGS.get(key);
    }

}
