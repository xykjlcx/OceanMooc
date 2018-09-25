package com.oceanli.oceanmooc.example.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.oceanli.oceanmooc.example.delegates.HomeMyCourseDelagate;
import com.oceanli.oceanmooc.example.delegates.HomeRecommendDelegate;

/**
 * Created by ocean on 2018/9/25
 * Author :  ocean
 * Email  :  348686686@qq.com
 */
public class HomeDelegateViewPageAdapter extends FragmentPagerAdapter {
    private String[] mtitles;

    public HomeDelegateViewPageAdapter(FragmentManager fm, String[] mtitles) {
        super(fm);
        this.mtitles = mtitles;
    }

    public HomeDelegateViewPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return HomeRecommendDelegate.newInstance();
        } else {
            return HomeMyCourseDelagate.newInstance();
        }
    }

    @Override
    public int getCount() {
        return mtitles.length;
    }
}
