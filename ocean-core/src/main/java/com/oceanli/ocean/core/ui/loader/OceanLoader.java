package com.oceanli.ocean.core.ui.loader;

import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.oceanli.ocean.core.R;
import com.oceanli.ocean.core.util.dimen.DimenUtil;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

/**
 * Created by ocean on 2018/7/1
 * Author :  ocean
 * Email  :  348686686@qq.com
 */
public class OceanLoader {

    private static final int LOADING_SIZE_SCALE = 8;
    private static final ArrayList<AppCompatDialog> LOADINGS = new ArrayList<>();
    // 默认loading样式
    public static LoaderStyle DEFAULT_LOADING_STYLE = LoaderStyle.BallPulseIndicator;

    public static void showLoading(Context context,String type) {
        final AppCompatDialog dialog = new AppCompatDialog(context, R.style.loadingDialog);
        dialog.setCanceledOnTouchOutside(false);
        AVLoadingIndicatorView avLoadingIndicatorView = LoaderCreator.create(context,type);
        dialog.setContentView(avLoadingIndicatorView);
        int devicesWidth = DimenUtil.getScreenWidth();
        int devicesHeight = DimenUtil.getScreenHeight();
        final Window dialogWindow = dialog.getWindow();
        if (dialogWindow != null){
            WindowManager.LayoutParams layoutParams = dialogWindow.getAttributes();
            layoutParams.width = devicesWidth / LOADING_SIZE_SCALE;
            layoutParams.height = devicesHeight / LOADING_SIZE_SCALE;
            layoutParams.gravity = Gravity.CENTER;
        }
        LOADINGS.add(dialog);
        dialog.show();
    }

    public static void showLoading(Context context) {
        showLoading(context,DEFAULT_LOADING_STYLE.name());
    }

    public static void showLoading(Context context,Enum<LoaderStyle> style) {
        showLoading(context,style.name());
    }

    public static void stopLoading() {
        for(AppCompatDialog dialog:LOADINGS) {
            if (dialog != null && dialog.isShowing()){
                dialog.cancel();
            }
        }
    }

}
