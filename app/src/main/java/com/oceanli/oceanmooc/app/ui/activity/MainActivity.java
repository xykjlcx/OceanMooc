package com.oceanli.oceanmooc.app.ui.activity;

import com.oceanli.ocean.core.activites.ProxyActivity;
import com.oceanli.ocean.core.delegates.OceanDelegate;
import com.oceanli.oceanmooc.app.ui.delegates.MainDelegate;

import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * Created by ocean on 2018/9/25
 * Author :  ocean
 * Email  :  348686686@qq.com
 */
public class MainActivity extends ProxyActivity {

    @Override
    public OceanDelegate setRootDelegate() {
        return MainDelegate.newInstance();
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }
}
