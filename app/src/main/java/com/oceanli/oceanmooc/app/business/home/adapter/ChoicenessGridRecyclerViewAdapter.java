package com.oceanli.oceanmooc.app.business.home.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.oceanli.oceanmooc.app.OmConstant;
import com.oceanli.oceanmooc.app.R;
import com.oceanli.oceanmooc.app.business.home.models.ChoicenessCourseModel;

import java.util.List;
import java.util.Random;

/**
 * Created by ocean on 2018/9/26
 * Author :  ocean
 * Email  :  348686686@qq.com
 */
public class ChoicenessGridRecyclerViewAdapter extends BaseQuickAdapter<ChoicenessCourseModel.DataBean,BaseViewHolder>{

    public ChoicenessGridRecyclerViewAdapter(int layoutResId, @Nullable List<ChoicenessCourseModel.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ChoicenessCourseModel.DataBean item) {
        helper.setText(R.id.tv_choiceness_item_course_name,item.getCourseName())
                .setText(R.id.tv_choiceness_item_course_desc,item.getCourseDesc())
                .setText(R.id.tv_choiceness_item_course_price,"￥" + (Integer)item.getPrice());
        Glide.with(mContext)
                .load(item.getImgUrl() + OmConstant.IMG_COMPRESS_URL)
                .centerCrop()
                .dontAnimate()
                .into((ImageView) helper.getView(R.id.iv_choiceness_img));
    }
}
