package com.oceanli.oceanmooc.app.business.home.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.oceanli.oceanmooc.app.OmConfig;
import com.oceanli.oceanmooc.app.R;
import com.oceanli.oceanmooc.app.business.home.models.ChoicenessCourseModel;

import java.util.List;
import java.util.Random;

/**
 * Created by ocean on 2018/9/26
 * Author :  ocean
 * Email  :  348686686@qq.com
 */
public class ChoicenessGridRecyclerViewAdapter extends BaseQuickAdapter<ChoicenessCourseModel,BaseViewHolder>{

    public ChoicenessGridRecyclerViewAdapter(int layoutResId, @Nullable List<ChoicenessCourseModel> data) {
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
    protected void convert(BaseViewHolder helper, ChoicenessCourseModel item) {
        helper.setText(R.id.tv_choiceness_item_course_name,item.getCourseName())
                .setText(R.id.tv_choiceness_item_course_desc,item.getCourseDesc())
                .setText(R.id.tv_choiceness_item_course_price,item.getPrice());
//        helper.setBackgroundColor(R.id.layout_choiceness_item, Color.parseColor(colors[item.getId()]));
//        helper.setBackgroundRes(R.id.layout_choiceness_item,imgs[item.getId()]);
        Glide.with(mContext)
                .load(imgUrls[random.nextInt(100) % 4] + OmConfig.IMG_COMPRESS_URL)
                .dontAnimate()
                .centerCrop()
                .into((ImageView) helper.getView(R.id.iv_choiceness_img));
    }
}
