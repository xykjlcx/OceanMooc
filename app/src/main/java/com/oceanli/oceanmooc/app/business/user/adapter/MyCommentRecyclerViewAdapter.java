package com.oceanli.oceanmooc.app.business.user.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.oceanli.oceanmooc.app.R;
import com.oceanli.oceanmooc.app.business.user.models.MyCommentModel;

import java.util.List;

/**
 * Created by ocean on 2018/10/6
 * Author :  ocean
 * Email  :  348686686@qq.com
 */
public class MyCommentRecyclerViewAdapter extends BaseQuickAdapter<MyCommentModel.DataBean,BaseViewHolder> {

    public MyCommentRecyclerViewAdapter(int layoutResId, @Nullable List<MyCommentModel.DataBean>
            data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyCommentModel.DataBean item) {
        helper.setText(R.id.tv_my_comment_time,item.getCommentTime())
                .setText(R.id.tv_my_comment_content,item.getCommentContent())
                .setText(R.id.tv_my_comment_course_name,item.getCourseVo().getCourseName());
        Glide.with(mContext)
                .load(item.getCourseVo().getImgUrl())
                .centerCrop()
                .dontAnimate()
                .into((ImageView) helper.getView(R.id.iv_my_comment_course_img));
    }
}
