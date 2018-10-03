package com.oceanli.oceanmooc.app.business.course.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.oceanli.oceanmooc.app.R;
import com.oceanli.oceanmooc.app.business.course.models.SectionCommentModel;

import java.util.List;

/**
 * Created by ocean on 2018/9/29
 * Author :  ocean
 * Email  :  348686686@qq.com
 */
public class CourseSectionCommentRecyclerViewAdapter extends BaseQuickAdapter<SectionCommentModel,BaseViewHolder> {

    public CourseSectionCommentRecyclerViewAdapter(int layoutResId, @Nullable List<SectionCommentModel> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SectionCommentModel item) {
        helper.setText(R.id.tv_comment_user_name,item.getUserName())
                .setText(R.id.tv_comment_time,item.getTime())
                .setText(R.id.tv_comment_content,item.getContent());
        Glide.with(mContext)
                .load(item.getHeadImgUrl())
                .centerCrop()
                .into((ImageView) helper.getView(R.id.iv_comment_head));
    }
}
