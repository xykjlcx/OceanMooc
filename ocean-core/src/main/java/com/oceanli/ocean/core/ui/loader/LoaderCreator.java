package com.oceanli.ocean.core.ui.loader;

import android.content.Context;

import com.wang.avi.AVLoadingIndicatorView;
import com.wang.avi.Indicator;

import java.util.WeakHashMap;

/**
 * Created by ocean on 2018/7/1
 * Author :  ocean
 * Email  :  348686686@qq.com
 */
public final class LoaderCreator {

    private static final WeakHashMap<String,Object> LOADING_TYPE = new WeakHashMap<>();

    static AVLoadingIndicatorView create(Context context,String type) {
        AVLoadingIndicatorView avLoadingIndicatorView = new AVLoadingIndicatorView(context);
        if (LOADING_TYPE.get(type) == null){
            final Indicator indicator = getIndicator(type);
            LOADING_TYPE.put(type,indicator);
        }
        avLoadingIndicatorView.setIndicator((Indicator) LOADING_TYPE.get(type));
        return avLoadingIndicatorView;
    }

    private static Indicator getIndicator(String name) {
        if (name == null
                || name.isEmpty()){
            return null;
        }
        final StringBuilder drawableClassName = new StringBuilder();
        if (!name.contains(".")) {
            final String defaultPackageName = AVLoadingIndicatorView.class.getPackage().getName();
            drawableClassName.append(defaultPackageName)
                    .append(".indicators")
                    .append(".");
        }
        drawableClassName.append(name);
        try {
            final Class<?> drawableClass = Class.forName(drawableClassName.toString());
            return (Indicator) drawableClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
