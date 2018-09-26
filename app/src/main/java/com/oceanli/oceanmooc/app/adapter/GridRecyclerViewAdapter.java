package com.oceanli.oceanmooc.app.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
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

    private String[] colors = {
            "#FFF886",
            "#EE9AE5",
            "#FFD3A5",
            "#69FF97"
    };

    @Override
    protected void convert(BaseViewHolder helper, GridCourseModel item) {
        helper.setText(R.id.tv_choiceness_item_course_name,item.getCourseName())
                .setText(R.id.tv_choiceness_item_course_desc,item.getCourseDesc())
                .setText(R.id.tv_choiceness_item_course_price,item.getPrice());
        helper.setBackgroundColor(R.id.layout_choiceness_item, Color.parseColor(colors[item.getId()]));
    }
}
