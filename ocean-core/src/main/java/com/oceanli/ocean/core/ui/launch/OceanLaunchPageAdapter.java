package com.oceanli.ocean.core.ui.launch;

/**
 * Created by ocean on 2018/7/4
 * Author :  ocean
 * Email  :  348686686@qq.com
 */

import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * 静态内部类 —— 适配器
 */
public class OceanLaunchPageAdapter extends PagerAdapter {
    private List<AppCompatImageView> mImageViews;

    public OceanLaunchPageAdapter(List<AppCompatImageView> mImageViews) {
        this.mImageViews = mImageViews;
    }

    @Override
    public int getCount() {
        return mImageViews.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        AppCompatImageView imageView = mImageViews.get(position);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}