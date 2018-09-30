package com.oceanli.ocean.core.delegates;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gyf.barlibrary.ImmersionBar;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;

/**
 * Created by ocean on 2018/6/20
 * Author :  ocean
 * Email  :  348686686@qq.com
 */
public abstract class BaseDelegate extends SwipeBackFragment {

    private Unbinder mUnbinder = null;

    protected ImmersionBar mImmersionBar;

    /**
     * 子类返回具体的布局
     * @return
     */
    public abstract Object setLayout();

    /**
     * 子类在这个方法里初始化布局
     * @param savedInstanceState
     * @param rootView
     */
    public abstract void onBindView(@Nullable Bundle savedInstanceState,View rootView);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = null;
        if (setLayout() instanceof Integer){
            rootView = inflater.inflate((Integer) setLayout(),container,false);
        }else if (setLayout() instanceof View){
            rootView = (View) setLayout();
        }
        if (rootView != null){
            mUnbinder = ButterKnife.bind(this,rootView);
            onBindView(savedInstanceState,rootView);
        }
        if (isSwip()){
            return attachToSwipeBack(rootView);
        }else {
            return rootView;
        }
    }

    /**
     * 是否支持侧滑
     * 默认 不支持
     * 子类重写该方法，返回true即可
     * @return
     */
    public boolean isSwip(){
        return false;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder != null)
            mUnbinder.unbind();
        if (mImmersionBar != null)
            mImmersionBar.destroy();
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        //如果要在Fragment单独使用沉浸式，请在onSupportVisible实现沉浸式
        if (isImmersionBarEnabled()) {
            View view = new View(_mActivity);
            mImmersionBar = ImmersionBar.with(this).statusBarDarkFont(true, 0.2f).statusBarView(view);
            mImmersionBar.navigationBarWithKitkatEnable(false).init();
        }
    }

    private boolean isImmersionBarEnabled() {
        return false;
    }


}
