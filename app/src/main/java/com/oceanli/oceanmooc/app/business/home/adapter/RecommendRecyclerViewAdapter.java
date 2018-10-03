package com.oceanli.oceanmooc.app.business.home.adapter;

import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.oceanli.oceanmooc.app.OmConstant;
import com.oceanli.oceanmooc.app.R;
import com.oceanli.oceanmooc.app.business.home.models.CourseVoModel;

import java.util.List;
import java.util.Random;

/**
 * Created by ocean on 2018/9/26
 * Author :  ocean
 * Email  :  348686686@qq.com
 */
public class RecommendRecyclerViewAdapter extends BaseQuickAdapter<CourseVoModel.DataBean,BaseViewHolder> {

    public RecommendRecyclerViewAdapter(int layoutResId, @Nullable List<CourseVoModel.DataBean> data) {
        super(layoutResId, data);
    }

    private Random random = new Random();

    @Override
    protected void convert(BaseViewHolder helper, CourseVoModel.DataBean item) {
        helper.setText(R.id.tv_recommend_title,item.getCourseName())
                .setText(R.id.tv_recommend_content,item.getCourseDesc())
                .setText(R.id.tv_recommend_price,"￥" + item.getPrice())
                .setText(R.id.tv_recommend_study_count,random.nextInt(200) + "人学过");
        Glide.with(mContext)
                .load(item.getImgUrl() + OmConstant.IMG_COMPRESS_URL)
                .centerCrop()
                .dontAnimate()
                .into((ImageView) helper.getView(R.id.img_recommend));
    }
}
