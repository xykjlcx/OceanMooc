package com.oceanli.oceanmooc.example.delegates;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.oceanli.ocean.core.delegates.OceanDelegate;
import com.oceanli.oceanmooc.example.R;
import com.oceanli.oceanmooc.example.adapter.HomeDelegateViewPageAdapter;
import com.oceanli.oceanmooc.example.other.ScaleTransitionPagerTitleView;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.BezierPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ocean on 2018/9/25
 * Author :  ocean
 * Email  :  348686686@qq.com
 */
public class HomeDelagate extends OceanDelegate {

    private ViewPager mViewPager;
    private HomeDelegateViewPageAdapter mAdapter;
    private String[] titles = {
            "推荐",
            "我的"
    };
    private List<String> mTitleDataList;
    private MagicIndicator magicIndicator;

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
        mImmersionBar.setStatusBarView(_mActivity,rootView.findViewById(R.id.cheng));
        mViewPager = rootView.findViewById(R.id.dlgt_home_viewpager);
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
        magicIndicator = rootView.findViewById(R.id.magic_indicator);
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
                        Color.parseColor("#0396FF"),
                        Color.parseColor("#28C76F"),
                        Color.parseColor("#F8D800")
                );
                return indicator;
            }
        });
        magicIndicator.setNavigator(commonNavigator);
    }

}
