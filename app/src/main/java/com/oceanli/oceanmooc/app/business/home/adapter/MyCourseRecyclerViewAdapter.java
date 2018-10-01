package com.oceanli.oceanmooc.app.business.home.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.oceanli.oceanmooc.app.R;
import com.oceanli.oceanmooc.app.business.home.models.MyCourseModel;

import java.util.List;

/**
 * Created by ocean on 2018/9/26
 * Author :  ocean
 * Email  :  348686686@qq.com
 */
public class MyCourseRecyclerViewAdapter extends BaseQuickAdapter<MyCourseModel.DataBean,BaseViewHolder> {

    public MyCourseRecyclerViewAdapter(int layoutResId, @Nullable List<MyCourseModel.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyCourseModel.DataBean item) {
        helper.setText(R.id.tv_my_course_last_time,item.getLastStudyTime())
                .setText(R.id.tv_my_course_name,"课程名称： " + item.getCourseVo().getCourseName())
                .setText(R.id.tv_my_course_study_section,"学习至： " + item.getSectionName())
                .setText(R.id.tv_my_course_duration,"时长： " + item.getCourseVo().getDuration());
        Glide.with(mContext)
                .load(item.getCourseVo().getImgUrl())
                .centerCrop()
                .into((ImageView) helper.getView(R.id.iv_my_course_img));
    }
}
