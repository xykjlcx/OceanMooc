package com.oceanli.oceanmooc.app.ui.delegates;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import com.oceanli.ocean.core.delegates.OceanDelegate;
import com.oceanli.oceanmooc.app.R;
import com.oceanli.oceanmooc.app.adapter.CourseParticularsDelegateViewPagerAdapter;
import com.oceanli.oceanmooc.app.ui.diy.ScaleTransitionPagerTitleView;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.BezierPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by ocean on 2018/9/28
 * Author :  ocean
 * Email  :  348686686@qq.com
 */
public class CourseParticularsDelegate extends OceanDelegate {

    private ImageView backImg;

    private ViewPager mViewPager;
    private CourseParticularsDelegateViewPagerAdapter mAdapter;

    private MagicIndicator magicIndicator;
    private String[] mDataList = {
            "简介",
            "章节",
            "评论"
    };

    private StandardGSYVideoPlayer standardGSYVideoPlayer;


    public static CourseParticularsDelegate newInstance(){
        CourseParticularsDelegate courseParticularsDelegate = new CourseParticularsDelegate();
        return courseParticularsDelegate;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_course_particulars;
    }

    @Override
    public boolean isSwip() {
        // 启用侧滑返回
        return true;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initView(rootView);
        backImg = rootView.findViewById(R.id.iv_course_particulars_back);
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop();
            }
        });
        standardGSYVideoPlayer = rootView.findViewById(R.id.gsy_course_particulars_video);
        standardGSYVideoPlayer.getBackButton().setVisibility(View.GONE);
    }



    public void initView(View rootView){
        mImmersionBar.setStatusBarView(_mActivity,rootView.findViewById(R.id.view_course_particulars_fill));
        magicIndicator = rootView.findViewById(R.id.magic_indicator_course_particulars);
        mViewPager = rootView.findViewById(R.id.vp_course_particulars);
        mAdapter = new CourseParticularsDelegateViewPagerAdapter(getChildFragmentManager(),mDataList);
        initMagicIndicator();
        mViewPager.setAdapter(mAdapter);
        ViewPagerHelper.bind(magicIndicator,mViewPager);
        mViewPager.setOffscreenPageLimit(2);
    }

    private void initMagicIndicator() {
        magicIndicator.setBackgroundColor(Color.WHITE);
        CommonNavigator commonNavigator = new CommonNavigator(_mActivity);
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mDataList == null ? 0 : mDataList.length;
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ScaleTransitionPagerTitleView(context);
                simplePagerTitleView.setText(mDataList[index]);
                simplePagerTitleView.setTextSize(18);
                simplePagerTitleView.setNormalColor(Color.parseColor("#616161"));
                simplePagerTitleView.setSelectedColor(Color.parseColor("#5C89CE"));
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

            @Override
            public float getTitleWeight(Context context, int index) {
                if (index == 0) {
                    return 1.0f;
                } else if (index == 1) {
                    return 1.0f;
                } else {
                    return 1.0f;
                }
            }
        });
        magicIndicator.setNavigator(commonNavigator);
    }

}
