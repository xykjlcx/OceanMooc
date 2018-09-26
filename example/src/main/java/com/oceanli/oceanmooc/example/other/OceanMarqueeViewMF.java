package com.oceanli.oceanmooc.example.other;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gongwen.marqueen.MarqueeFactory;
import com.oceanli.oceanmooc.example.R;

/**
 * Created by ocean on 2018/9/26
 * Author :  ocean
 * Email  :  348686686@qq.com
 */
public class OceanMarqueeViewMF extends MarqueeFactory<RelativeLayout, OceanMarqueeItemModel> {
    private LayoutInflater inflater;
    private Context context;

    public OceanMarqueeViewMF(Context mContext, LayoutInflater inflater) {
        super(mContext);
        context = mContext;
        this.inflater = inflater;
    }

    @Override
    protected RelativeLayout generateMarqueeItemView(OceanMarqueeItemModel data) {
        RelativeLayout mView = (RelativeLayout) inflater.inflate(R.layout.marquee_item,null);
        TextView titleOne = mView.findViewById(R.id.marquee_item_title_one);
        TextView titleTwo = mView.findViewById(R.id.marquee_item_title_two);
        ImageView imageView = mView.findViewById(R.id.marquee_item_img);
        titleOne.setText(data.getTitleOne());
        titleTwo.setText(data.getTitleTwo());
        Glide.with(context)
                .load("http://pevcw8o7e.bkt.clouddn.com//img/default_head.png")
                .centerCrop()
                .into(imageView);
        return mView;
    }
}
