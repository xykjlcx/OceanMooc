package com.oceanli.ocean.core.ui.launch;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.oceanli.ocean.core.R;
import com.oceanli.ocean.core.app.Ocean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ocean on 2018/7/3
 * Author :  ocean
 * Email  :  348686686@qq.com
 */
public class OceanLaunchView extends RelativeLayout{
    private AppCompatButton mSkipBtn;
    private ViewPager mLaunchVpg;
    private OceanLaunchPageAdapter mAdapter;
    private LinearLayoutCompat mLayoutCompat;
    private List<AppCompatTextView> mTextViews = new ArrayList<>();
    private Context mContext;

    /**
     * 跳过按钮被单击 - 监听器
     */
    private ISkipListener mISkipListener;
    /**
     * 图片资源集合
     */
    private List<Integer> mImgResource = new ArrayList<>();
    /**
     * 图片集合容器
     */
    private List<AppCompatImageView> mImageViews = new ArrayList<>();
    /**
     * 是否准备完成
     */
    private boolean isReady = false;


    public OceanLaunchView(Context context) {
        super(context);
        initView(context);
    }

    public OceanLaunchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public OceanLaunchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public OceanLaunchView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }

    private void initView(Context context){
        mContext = context;
        LayoutInflater.from(context).inflate(R.layout.view_launch,this,true);
        mSkipBtn = findViewById(R.id.btn_launch);
        mLayoutCompat = findViewById(R.id.layout_launch);
        mLaunchVpg = findViewById(R.id.vpg_launch);
        //TODO 7.5日参考https://www.jianshu.com/p/adb21180862a完善引导页封装，并参考视频封装SharedPreferences,收尾网络模块
        mAdapter = new OceanLaunchPageAdapter(mImageViews);
        mSkipBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mISkipListener != null){
                    mISkipListener.onClick();
                }
            }
        });
    }

    public void setSkipListener(ISkipListener mISkipListener) {
        this.mISkipListener = mISkipListener;
    }

    public void setImgResource(List<Integer> imgs) {
        isReady = true;
        mImgResource.clear();
        mImgResource = imgs;
        matchImg();
        addIndicator();
    }

    public void setImgResource(int[] imgs) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < imgs.length; i++) {
            list.add(imgs[i]);
        }
        setImgResource(list);
    }

    /**
     * 匹配图片资源
     */
    private void matchImg() {
        mImageViews.clear();
        for (int i = 0; i < mImgResource.size(); i++) {
            AppCompatImageView imageView = new AppCompatImageView(mContext);
            imageView.setImageResource(mImgResource.get(i));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            mImageViews.add(imageView);
        }
    }

    /**
     * 动态添加指示器
     */
    private void addIndicator() {
        if (mImgResource != null
                && !mImgResource.isEmpty()){
            for (int i = 0; i < mImgResource.size(); i++) {
                AppCompatTextView textView = new AppCompatTextView(mContext);
                textView.setBackgroundResource(i==0 ? R.drawable.shaper_indicator_selected : R.drawable.shaper_indicator_normal);
                mTextViews.add(textView);
                mLayoutCompat.addView(textView,30,30);
                setMargin(textView,10,0,10,0);
            }
        }
    }

    private void setMargin(View view,int left,int top,int right,int bottom) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        ViewGroup.MarginLayoutParams marginParams = null;
        //获取view的margin设置参数
        if (params instanceof ViewGroup.MarginLayoutParams) {
            marginParams = (ViewGroup.MarginLayoutParams) params;
        } else {
            //不存在时创建一个新的参数
            //基于View本身原有的布局参数对象
            marginParams = new ViewGroup.MarginLayoutParams(params);
        }
        marginParams.setMargins(left,top,right,bottom);
        view.setLayoutParams(marginParams);
    }

    private void setTab(int position) {
        for (int i = 0; i < mTextViews.size(); i++) {
            mTextViews.get(i).setBackgroundResource(i == position ? R.drawable.shaper_indicator_selected
                : R.drawable.shaper_indicator_normal);
        }
    }

    public void launch() {
        if (isReady == false){
            throw new RuntimeException("OceanLaunchView Configuration Not Complete !!! ");
        }
        mLaunchVpg.setAdapter(mAdapter);
        mLaunchVpg.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.e("ocean", "onPageScrolled: "  + "A:" + position + "B:" + positionOffset + "C:" + positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                setTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.e("ocean", "onPageScrollStateChanged: " + state);
            }
        });
    }

}
