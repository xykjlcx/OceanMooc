package com.oceanli.ocean.core.util;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.oceanli.ocean.core.app.Ocean;

/**
 * Created by ocean on 2018/7/1
 * Author :  ocean
 * Email  :  348686686@qq.com
 */
public class DimenUtil {

    public static int getScreenWidth() {
        final Resources resources = Ocean.getApplicationContext().getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        return metrics.widthPixels;
    }

    public static int getScreenHeight() {
        final Resources resources = Ocean.getApplicationContext().getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        return metrics.heightPixels;
    }

}
