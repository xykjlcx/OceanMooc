package com.oceanli.oceanmooc.app.business.home.ui;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.oceanli.ocean.core.delegates.OceanDelegate;
import com.oceanli.oceanmooc.app.R;
import com.oceanli.oceanmooc.app.business.home.adapter.HomeDelegateViewPageAdapter;
import com.oceanli.oceanmooc.app.other.diy.ScaleTransitionPagerTitleView;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.BezierPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by ocean on 2018/9/25
 * Author :  ocean
 * Email  :  348686686@qq.com
 */
public class HomeDelagate extends OceanDelegate {

    @BindView(R.id.vp_home_recommend_and_my)
    ViewPager mViewPager;
    @BindView(R.id.magicindicator_home)
    MagicIndicator magicIndicator;

    private HomeDelegateViewPageAdapter mAdapter;
    private String[] titles = {
            "推荐",
            "我的"
    };
    private List<String> mTitleDataList;


    public static HomeDelagate newInstance(){
        Bundle args = new Bundle();
        HomeDelagate homeDelagate = new HomeDelagate();
        homeDelagate.setArguments(args);
        return homeDelagate;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_home;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initView(rootView);
    }

    public void initView(View rootView){
        mImmersionBar.setStatusBarView(_mActivity,rootView.findViewById(R.id.view_home_fill));
        mAdapter = new HomeDelegateViewPageAdapter(getChildFragmentManager(),titles);
        mTitleDataList = new ArrayList<>();
        mTitleDataList.add("推荐");
        mTitleDataList.add("我的");
        initTabLayout(rootView);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        mViewPager.setAdapter(mAdapter);
        ViewPagerHelper.bind(magicIndicator,mViewPager);
    }

    public void initTabLayout(View rootView){
        CommonNavigator commonNavigator = new CommonNavigator(_mActivity);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {

            @Override
            public int getCount() {
                return mTitleDataList == null ? 0 : mTitleDataList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ScaleTransitionPagerTitleView(context);
                simplePagerTitleView.setText(mTitleDataList.get(index));
                simplePagerTitleView.setTextSize(25);
                simplePagerTitleView.setNormalColor(Color.GRAY);
                simplePagerTitleView.setSelectedColor(Color.BLACK);
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mViewPager.setCurrentItem(index);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                BezierPagerIndicator indicator = new BezierPagerIndicator(context);
//                indicator.setColors(Color.parseColor("#ff4a42"), Color.parseColor("#fcde64"), Color.parseColor("#73e8f4"), Color.parseColor("#76b0ff"), Color.parseColor("#c683fe"));
                indicator.setColors(
                        Color.parseColor("#5EFCE8"),
                        Color.parseColor("#736EFE")
                );
                return indicator;
            }
        });
        magicIndicator.setNavigator(commonNavigator);
    }

}
