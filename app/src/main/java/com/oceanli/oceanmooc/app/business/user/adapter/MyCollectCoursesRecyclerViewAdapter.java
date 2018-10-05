package com.oceanli.oceanmooc.app.business.user.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.oceanli.oceanmooc.app.R;
import com.oceanli.oceanmooc.app.business.home.models.CourseVoModel;
import com.oceanli.oceanmooc.app.callback.IOnCancleCollectCourseOnClickCallBack;

import java.util.List;

/**
 * Created by ocean on 2018/10/5
 * Author :  ocean
 * Email  :  348686686@qq.com
 */
public class MyCollectCoursesRecyclerViewAdapter extends BaseQuickAdapter<CourseVoModel.DataBean,BaseViewHolder> {

    private IOnCancleCollectCourseOnClickCallBack onCancleCollectCourseOnClickCallBack;

    public void setOnCancleCollectCourseOnClickCallBack(IOnCancleCollectCourseOnClickCallBack
                                                                onCancleCollectCourseOnClickCallBack) {
        this.onCancleCollectCourseOnClickCallBack = onCancleCollectCourseOnClickCallBack;
    }

    public MyCollectCoursesRecyclerViewAdapter(int layoutResId, @Nullable List<CourseVoModel.DataBean>
            data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CourseVoModel.DataBean item) {
        helper.setText(R.id.tv_collect_level,item.getLevel())
                .setText(R.id.tv_collect_course_name,item.getCourseName())
                .setText(R.id.tv_collect_course_content,item.getCourseDesc())
                .setText(R.id.tv_collect_course_price,"$" + item.getPrice());
        helper.getView(R.id.tv_collect_cancle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 取消收藏回调
                if (onCancleCollectCourseOnClickCallBack != null){
                    onCancleCollectCourseOnClickCallBack.onclick(helper.getAdapterPosition(),item);
                }
            }
        });
        Glide.with(mContext)
                .load(item.getImgUrl())
                .centerCrop()
                .dontAnimate()
                .into((ImageView) helper.getView(R.id.iv_collect_item_course_img));
    }
}
