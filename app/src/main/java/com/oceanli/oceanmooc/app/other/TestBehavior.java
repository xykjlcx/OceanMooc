package com.oceanli.oceanmooc.app.other;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by ocean on 2018/9/27
 * Author :  ocean
 * Email  :  348686686@qq.com
 */
public class TestBehavior extends AppBarLayout.Behavior {

    private static final int TYPE_FLING = 1;
    private boolean isFlinging;
    private boolean shouldBlockNestedScroll;


    public TestBehavior() {
    }

    public TestBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(CoordinatorLayout parent, AppBarLayout child, MotionEvent ev) {
        Log.i("TestBehavior", "onInterceptTouchEvent:" + child.getTotalScrollRange());
        shouldBlockNestedScroll = false;
        if (isFlinging) {
            shouldBlockNestedScroll = true;
        }
        return super.onInterceptTouchEvent(parent, child, ev);
    }


    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, AppBarLayout child, View target, int dx, int dy, int[] consumed, int type) {
        Log.i("TestBehavior", "onNestedPreScroll:" + child.getTotalScrollRange() + " ,dx:" + dx + " ,dy:" + dy + " ,type:" + type);
        if (type == TYPE_FLING) {
            isFlinging = true;
        }
        if (!shouldBlockNestedScroll) {
            super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type);
        }
    }


    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, AppBarLayout child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
        Log.i("TestBehavior", "onNestedScroll: target:" + target.getClass() + " ," + child.getTotalScrollRange() + " ,dxConsumed:"                + dxConsumed + " ,dyConsumed:" + dyConsumed + " " + ",type:" + type);
        if (!shouldBlockNestedScroll) {
            super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type);
        }
    }

    @Override
    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, AppBarLayout abl, View target, int type) {
        super.onStopNestedScroll(coordinatorLayout, abl, target, type);
        isFlinging = false;
        shouldBlockNestedScroll = false;
    }
}
