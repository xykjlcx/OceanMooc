package com.oceanli.oceanmooc.app.business;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.oceanli.ocean.core.delegates.OceanDelegate;
import com.oceanli.oceanmooc.app.R;
import com.oceanli.oceanmooc.app.business.course.ui.CourseDelegate;
import com.oceanli.oceanmooc.app.business.home.ui.HomeDelagate;
import com.oceanli.oceanmooc.app.business.user.ui.UserDelegate;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by ocean on 2018/9/25 Author :  ocean Email  :  348686686@qq.com
 */
public class MainDelegate extends OceanDelegate {
    @BindView(R.id.bottombar_main)
    BottomBar bottomBar;
    /**
     * 首页
     */
    private static final Integer HOME = 0;
    /**
     * 课程
     */
    private static final Integer COURSE = 1;
    /**
     * 个人中心
     */
    private static final Integer USER = 2;
    private SupportFragment[] mFragments = new SupportFragment[3];

    public static MainDelegate newInstance() {
        Bundle args = new Bundle();
        MainDelegate mainDelegate = new MainDelegate();
        mainDelegate.setArguments(args);
        return mainDelegate;
    }

    @Nullable
    @Override
    public Object setLayout() {
        return R.layout.delegate_main;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initView(rootView);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        SupportFragment homeDelagate = findChildFragment(HomeDelagate.class);
        if (homeDelagate == null) {
            mFragments[HOME] = HomeDelagate.newInstance();
            mFragments[COURSE] = CourseDelegate.newInstance();
            mFragments[USER] = UserDelegate.newInstance();
            loadMultipleRootFragment(R.id.layout_main_container, HOME, mFragments[HOME], mFragments[COURSE], mFragments[USER]);
        } else {/** 如果存在，直接拿引用 */
            mFragments[HOME] = homeDelagate;
            mFragments[COURSE] = findChildFragment(CourseDelegate.class);
            mFragments[USER] = findChildFragment(UserDelegate.class);
        }
    }

    public void initView(View rootView) {
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch (tabId) {
                    case R.id.tab_home:
                        showHideFragment(mFragments[HOME]);
                        break;
                    case R.id.tab_course:
                        showHideFragment(mFragments[COURSE]);
                        break;
                    case R.id.tab_user:
                        showHideFragment(mFragments[USER]);
                        break;
                }
            }
        });
    }

    /**
     * start other BrotherFragment
     */
    public void startBrotherFragment(SupportFragment targetFragment) {
        start(targetFragment);/*        startActivity(new Intent(_mActivity,TestActivity.class));*/
    }/* 再点一次退出程序时间设置*/

    private static final long WAIT_TIME = 2000L;
    private long TOUCH_TIME = 0;

    /**
     * 处理回退事件 @return
     */
    @Override
    public boolean onBackPressedSupport() {
        if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) _mActivity.finish();
        else {
            TOUCH_TIME = System.currentTimeMillis();
            Toast.makeText(_mActivity, com.oceanli.ocean.core.R.string.press_again_exit, Toast.LENGTH_SHORT).show();
        }
        return true;
    }

}
