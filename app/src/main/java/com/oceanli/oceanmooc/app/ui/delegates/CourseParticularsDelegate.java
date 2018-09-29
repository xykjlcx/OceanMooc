package com.oceanli.oceanmooc.app.ui.delegates;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

import com.oceanli.ocean.core.delegates.OceanDelegate;
import com.oceanli.ocean.core.net.RestClient;
import com.oceanli.ocean.core.net.callback.IFailure;
import com.oceanli.ocean.core.net.callback.ISuccess;
import com.oceanli.oceanmooc.app.R;
import com.oceanli.oceanmooc.app.adapter.CourseParticularsDelegateViewPagerAdapter;
import com.oceanli.oceanmooc.app.ui.diy.ScaleTransitionPagerTitleView;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
    private OrientationUtils orientationUtils;


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
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
//        initNetData();
        initVideo(
                "http://pevcw8o7e.bkt.clouddn.com/caifang.mp4",
                ""
        );
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
    }


    public void initNetData(){
        //
        RestClient.builder()
                .url("http://192.168.43.214:8088/home/getGuessLikeCourse")
                .params("page",0)
                .params("size",3)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        try {
                            Log.e("jass", "onSuccess: " + response);
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("data");
                            JSONObject realData = jsonArray.getJSONObject(0);
                            String videoUrl = realData.getString("videoUrl");
                            String imgUrl = realData.getString("imgUrl");
                            initVideo(videoUrl,imgUrl);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        Log.e("jass", "onFailure: " + "失败?");
                    }
                })
                .build()
                .post();
    }

    public void initView(final View rootView){
        standardGSYVideoPlayer = rootView.findViewById(R.id.gsy_course_particulars_video);
        standardGSYVideoPlayer.getBackButton().setVisibility(View.GONE);
        mImmersionBar.setStatusBarView(_mActivity,rootView.findViewById(R.id.view_course_particulars_fill));
        magicIndicator = rootView.findViewById(R.id.magic_indicator_course_particulars);
        mViewPager = rootView.findViewById(R.id.vp_course_particulars);
        mAdapter = new CourseParticularsDelegateViewPagerAdapter(getChildFragmentManager(),mDataList);
        initMagicIndicator();
        mViewPager.setAdapter(mAdapter);
        ViewPagerHelper.bind(magicIndicator,mViewPager);
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
                simplePagerTitleView.setTextSize(16);
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


    private void initVideo(String videoUrl,String imgUrl) {
        String source1 = videoUrl;
        standardGSYVideoPlayer.setUp(source1, true, "测试视频");

        //增加封面
        ImageView imageView = new ImageView(getActivity());
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(R.drawable.a1);
        standardGSYVideoPlayer.setThumbImageView(imageView);
        //增加title
        standardGSYVideoPlayer.getTitleTextView().setVisibility(View.VISIBLE);
        //设置返回键
        standardGSYVideoPlayer.getBackButton().setVisibility(View.GONE);
        //设置旋转
//        orientationUtils = new OrientationUtils(_mActivity,standardGSYVideoPlayer);
        //设置全屏按键功能,这是使用的是选择屏幕，而不是全屏
        standardGSYVideoPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                orientationUtils.resolveByClick();
                standardGSYVideoPlayer.startWindowFullscreen(_mActivity,false,true);
            }
        });
        //是否可以滑动调整
        standardGSYVideoPlayer.setIsTouchWiget(true);
//        standardGSYVideoPlayer.startPlayLogic();
        standardGSYVideoPlayer.setThumbPlay(true);
        standardGSYVideoPlayer.setShowFullAnimation(true);
    }


    @Override
    public void onPause() {
        super.onPause();
        standardGSYVideoPlayer.onVideoPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        standardGSYVideoPlayer.onVideoResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        GSYVideoManager.releaseAllVideos();
        if (orientationUtils != null)
            orientationUtils.releaseListener();
    }

    @Override
    public boolean onBackPressedSupport() {
        // 通过获取返回事件的发出场景，判断是否继续向上传递事件
        // backFromWindowFull()方法若当前处于全屏播放，推出全屏 并返回ture
        // 若不处于全屏，返回false
        // onBackPressedSupport()返回true则不会将back事件继续向上传递(同理，从全屏触发的back，并不会向上传递back事件)
        boolean isFull = GSYVideoManager.backFromWindowFull(_mActivity);
        return isFull;
    }
}
