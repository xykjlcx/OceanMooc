package com.oceanli.oceanmooc.app.ui.delegates;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.view.View;

import com.oceanli.ocean.core.delegates.OceanDelegate;
import com.oceanli.oceanmooc.app.R;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by ocean on 2018/9/25
 * Author :  ocean
 * Email  :  348686686@qq.com
 */
public class MainDelegate extends OceanDelegate {

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

    private BottomBar bottomBar;



    public static MainDelegate newInstance(){
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
        if (homeDelagate == null){
            mFragments[HOME] = HomeDelagate.newInstance();
            mFragments[COURSE] = CourseDelegate.newInstance();
            mFragments[USER] = UserDelegate.newInstance();

            loadMultipleRootFragment(
                    R.id.layout_main_container,
                    HOME,
                    mFragments[HOME],
                    mFragments[COURSE],
                    mFragments[USER]
            );
        } else {
            /**
             * 如果存在，直接拿引用
             */
            mFragments[HOME] = homeDelagate;
            mFragments[COURSE] = findChildFragment(CourseDelegate.class);
            mFragments[HOME] = findChildFragment(UserDelegate.class);
        }

    }

    public void initView(View rootView){
        bottomBar = rootView.findViewById(R.id.bottombar_main);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch (tabId){
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

}
