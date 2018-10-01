package com.oceanli.oceanmooc.app.business.course.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.oceanli.oceanmooc.app.business.course.ui.CourseCommentDelegate;
import com.oceanli.oceanmooc.app.business.course.ui.CourseIntroDelegate;
import com.oceanli.oceanmooc.app.business.course.ui.CourseSectionDelegate;

/**
 * Created by ocean on 2018/9/28
 * Author :  ocean
 * Email  :  348686686@qq.com
 */
public class CourseParticularsDelegateViewPagerAdapter extends FragmentPagerAdapter {
    private String[] mtitles;

    public CourseParticularsDelegateViewPagerAdapter(FragmentManager fm, String[] mtitles) {
        super(fm);
        this.mtitles = mtitles;
    }

    public CourseParticularsDelegateViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return CourseIntroDelegate.newInstance();
        } else if (position == 1) {
            return CourseSectionDelegate.newInstance();
        } else {
            return CourseCommentDelegate.newInstance();
        }
    }

    @Override
    public int getCount() {
        return mtitles.length;
    }
}
