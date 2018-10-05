package com.oceanli.oceanmooc.app.business.user.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.oceanli.oceanmooc.app.business.home.models.CourseVoModel;

import java.util.List;

/**
 * Created by ocean on 2018/10/5
 * Author :  ocean
 * Email  :  348686686@qq.com
 */
public class MyCollectCoursesRecyclerViewAdapter extends BaseQuickAdapter<CourseVoModel,BaseViewHolder> {

    public MyCollectCoursesRecyclerViewAdapter(int layoutResId, @Nullable List<CourseVoModel>
            data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CourseVoModel item) {

    }
}
