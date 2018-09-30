package com.oceanli.oceanmooc.app.business.course.ui;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.oceanli.ocean.core.delegates.OceanDelegate;
import com.oceanli.ocean.core.net.RestClient;
import com.oceanli.ocean.core.net.callback.IFailure;
import com.oceanli.ocean.core.net.callback.ISuccess;
import com.oceanli.oceanmooc.app.R;
import com.oceanli.oceanmooc.app.business.course.adapter.CourseParticularsDelegateViewPagerAdapter;
import com.oceanli.ocean.core.event.OceanMessageEvent;
import com.oceanli.oceanmooc.app.other.diy.ScaleTransitionPagerTitleView;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.wx.goodview.GoodView;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.BezierPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ocean on 2018/9/28
 * Author :  ocean
 * Email  :  348686686@qq.com
 */
public class CourseParticularsDelegate extends OceanDelegate {

    @BindView(R.id.iv_course_particulars_back)
    ImageView backImg;
    @BindView(R.id.vp_course_particulars)
    ViewPager mViewPager;
    private CourseParticularsDelegateViewPagerAdapter mAdapter;

    @BindView(R.id.magic_indicator_course_particulars)
    MagicIndicator magicIndicator;
    private String[] mDataList = {
            "简介",
            "章节",
            "评论"
    };

    @BindView(R.id.gsy_course_particulars_video)
    StandardGSYVideoPlayer standardGSYVideoPlayer;
    private OrientationUtils orientationUtils;

    @BindView(R.id.iv_course_particulars_collect)
    ImageView collectImg;

    @OnClick(R.id.iv_course_particulars_back)
    public void backOnClick(){
        pop();
    }

    GoodView goodView = null;

    @OnClick(R.id.iv_course_particulars_collect)
    public void collectonClick(View view){
        goodView.setImage(R.mipmap.shoucanged);
        goodView.show(view);
        collectImg.setImageResource(R.mipmap.shoucanged);
    }


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
        setVideoSource(
                "http://pevcw8o7e.bkt.clouddn.com/caifang.mp4",
                "初始化加载标题",
                "http://pevcw8o7e.bkt.clouddn.com/om2.jpg"
        );
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initView(rootView);
        initData();
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
                            setVideoSource(videoUrl,"网络标题",imgUrl);
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
        mImmersionBar.setStatusBarView(_mActivity,rootView.findViewById(R.id.view_course_particulars_fill));
        goodView = new GoodView(_mActivity);
        initGSYVideoView();
        initMagicIndicator();
    }

    public void initData(){
        mAdapter = new CourseParticularsDelegateViewPagerAdapter(getChildFragmentManager(),mDataList);
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
                indicator.setColors(
                        Color.parseColor("#0396FF"),
                        Color.parseColor("#28C76F"),
                        Color.parseColor("#F8D800")
                );
                return indicator;
            }

            @Override
            public float getTitleWeight(Context context, int index) {
                // 指示器横向评价分配宽度
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

    private void initGSYVideoView(){
        //增加title
        standardGSYVideoPlayer.getTitleTextView().setVisibility(View.VISIBLE);
        //设置旋转
//        orientationUtils = new OrientationUtils(_mActivity,standardGSYVideoPlayer);
        //设置全屏按键功能
        standardGSYVideoPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                standardGSYVideoPlayer.startWindowFullscreen(_mActivity,false,true);
            }
        });
        // 设置视频空间返回按钮不显示
        standardGSYVideoPlayer.getBackButton().setVisibility(View.GONE);
        //是否可以滑动调整
        standardGSYVideoPlayer.setIsTouchWiget(true);
        standardGSYVideoPlayer.setThumbPlay(true);
        standardGSYVideoPlayer.setShowFullAnimation(true);
    }

    public void setVideoSource(String videoUrl,String videoTitle,String imgUrl){
        //增加封面
        ImageView imageView = new ImageView(getActivity());
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Glide.with(_mActivity)
                .load(imgUrl)
                .centerCrop()
                .into(imageView);
        standardGSYVideoPlayer.setThumbImageView(imageView);
        standardGSYVideoPlayer.setUp(videoUrl, true, videoTitle);
    }


    // 接收点击章节后视频的更新
    @Override
    public void onMessageEvent(OceanMessageEvent event) {
        super.onMessageEvent(event);
        Toast.makeText(_mActivity, "接收到消息了" + event.getMsg(), Toast.LENGTH_SHORT).show();
        setVideoSource(
                "http://pevcw8o7e.bkt.clouddn.com/caipai.mp4",
                "接收的新视频",
                ""
        );
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