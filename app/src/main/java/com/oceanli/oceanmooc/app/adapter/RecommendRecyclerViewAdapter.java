package com.oceanli.oceanmooc.app.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.oceanli.oceanmooc.app.R;
import com.oceanli.oceanmooc.app.models.HomeCourseModel;

import java.util.List;

/**
 * Created by ocean on 2018/9/26
 * Author :  ocean
 * Email  :  348686686@qq.com
 */
public class RecommendRecyclerViewAdapter extends BaseQuickAdapter<HomeCourseModel,BaseViewHolder> {

    public RecommendRecyclerViewAdapter(int layoutResId, @Nullable List<HomeCourseModel> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeCourseModel item) {
        helper.setText(R.id.tv_recommend_title,item.getTitle())
                .setText(R.id.tv_recommend_content,item.getContent())
                .setImageResource(R.id.img_recommend,R.drawable.a2);
    }
}
