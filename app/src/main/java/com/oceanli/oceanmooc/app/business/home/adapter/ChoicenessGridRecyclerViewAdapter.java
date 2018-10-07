package com.oceanli.oceanmooc.app.business.home.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.oceanli.oceanmooc.app.OmConstant;
import com.oceanli.oceanmooc.app.R;
import com.oceanli.oceanmooc.app.business.home.models.CourseVoModel;

import java.util.List;

/**
 * Created by ocean on 2018/9/26
 * Author :  ocean
 * Email  :  348686686@qq.com
 */
public class ChoicenessGridRecyclerViewAdapter extends BaseQuickAdapter<CourseVoModel.DataBean,BaseViewHolder>{

    public ChoicenessGridRecyclerViewAdapter(int layoutResId, @Nullable List<CourseVoModel.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CourseVoModel.DataBean item) {
        helper.setText(R.id.tv_choiceness_item_course_name,item.getCourseName())
                .setText(R.id.tv_choiceness_item_course_desc,item.getCourseDesc())
                .setText(R.id.tv_choiceness_item_course_price,"￥" + (Integer)item.getPrice());
        Glide.with(mContext)
                .load(item.getImgUrl() + OmConstant.IMG_COMPRESS_URL)
                .error(R.mipmap.b4)         // 加载出错时
                .fallback(R.mipmap.b1)      // 加载url为空时
                .placeholder(R.mipmap.b2)   // 加载图片前默认
                .centerCrop()
                .dontAnimate()
                .into((ImageView) helper.getView(R.id.iv_choiceness_img));
    }
}
