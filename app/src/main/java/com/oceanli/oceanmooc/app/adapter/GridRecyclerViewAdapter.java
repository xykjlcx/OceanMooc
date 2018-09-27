package com.oceanli.oceanmooc.app.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.oceanli.oceanmooc.app.OmConfig;
import com.oceanli.oceanmooc.app.R;
import com.oceanli.oceanmooc.app.models.GridCourseModel;

import java.util.List;

/**
 * Created by ocean on 2018/9/26
 * Author :  ocean
 * Email  :  348686686@qq.com
 */
public class GridRecyclerViewAdapter extends BaseQuickAdapter<GridCourseModel,BaseViewHolder>{

    public GridRecyclerViewAdapter(int layoutResId, @Nullable List<GridCourseModel> data) {
        super(layoutResId, data);
    }

    private String[] imgUrls = {
            "http://pevcw8o7e.bkt.clouddn.com/om1fuben.jpg",
            "http://pevcw8o7e.bkt.clouddn.com/om2.jpg",
            "http://pevcw8o7e.bkt.clouddn.com/om3.jpg",
            "http://pevcw8o7e.bkt.clouddn.com/om4.jpg"
    };

    @Override
    protected void convert(BaseViewHolder helper, GridCourseModel item) {
        helper.setText(R.id.tv_choiceness_item_course_name,item.getCourseName())
                .setText(R.id.tv_choiceness_item_course_desc,item.getCourseDesc())
                .setText(R.id.tv_choiceness_item_course_price,item.getPrice());
//        helper.setBackgroundColor(R.id.layout_choiceness_item, Color.parseColor(colors[item.getId()]));
//        helper.setBackgroundRes(R.id.layout_choiceness_item,imgs[item.getId()]);
        Glide.with(mContext)
                .load(imgUrls[item.getId()] + OmConfig.IMG_COMPRESS_URL)
                .dontAnimate()
                .centerCrop()
                .into((ImageView) helper.getView(R.id.iv_choiceness_img));
    }
}
