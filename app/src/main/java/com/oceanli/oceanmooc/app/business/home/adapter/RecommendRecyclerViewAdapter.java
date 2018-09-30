package com.oceanli.oceanmooc.app.business.home.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.oceanli.oceanmooc.app.R;
import com.oceanli.oceanmooc.app.business.home.models.RecommendCourseModel;

import java.util.List;
import java.util.Random;

/**
 * Created by ocean on 2018/9/26
 * Author :  ocean
 * Email  :  348686686@qq.com
 */
public class RecommendRecyclerViewAdapter extends BaseQuickAdapter<RecommendCourseModel,BaseViewHolder> {

    public RecommendRecyclerViewAdapter(int layoutResId, @Nullable List<RecommendCourseModel> data) {
        super(layoutResId, data);
    }

    private String[] imgUrls = {
            "http://pevcw8o7e.bkt.clouddn.com/om1fuben.jpg",
            "http://pevcw8o7e.bkt.clouddn.com/om2.jpg",
            "http://pevcw8o7e.bkt.clouddn.com/om3.jpg",
            "http://pevcw8o7e.bkt.clouddn.com/om4.jpg"
    };

    private Random random = new Random();

    @Override
    protected void convert(BaseViewHolder helper, RecommendCourseModel item) {
        helper.setText(R.id.tv_recommend_title,item.getTitle())
                .setText(R.id.tv_recommend_content,item.getContent());
        Glide.with(mContext)
                .load(imgUrls[random.nextInt(100) % 4])
                .centerCrop()
                .dontAnimate()
                .into((ImageView) helper.getView(R.id.img_recommend));
    }
}
