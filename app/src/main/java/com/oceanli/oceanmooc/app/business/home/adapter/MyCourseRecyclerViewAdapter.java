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
public class MyCourseRecyclerViewAdapter extends BaseQuickAdapter<MyCourseModel,BaseViewHolder> {

    public MyCourseRecyclerViewAdapter(int layoutResId, @Nullable List<MyCourseModel> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyCourseModel item) {
        helper.setText(R.id.tv_my_course_last_time,"最后时间")
                .setText(R.id.tv_my_course_name,item.getCourseName())
                .setText(R.id.tv_my_course_teacher,item.getTeacherName())
                .setText(R.id.tv_my_course_duration,item.getDuration());
        Glide.with(mContext)
                .load(R.drawable.a2)
                .centerCrop()
                .into((ImageView) helper.getView(R.id.iv_my_course_img));
    }
}
