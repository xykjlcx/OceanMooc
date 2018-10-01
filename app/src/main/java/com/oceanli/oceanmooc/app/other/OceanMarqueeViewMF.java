package com.oceanli.oceanmooc.app.other;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gongwen.marqueen.MarqueeFactory;
import com.oceanli.oceanmooc.app.R;
import com.oceanli.oceanmooc.app.other.models.OceanMarqueeItemModel;

/**
 * Created by ocean on 2018/9/26 Author :  ocean Email  :  348686686@qq.com
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
        RelativeLayout mView = (RelativeLayout) inflater.inflate(R.layout.item_marquee, null);
        TextView titleOne = mView.findViewById(R.id.tv_marquee_title_one);
        TextView titleTwo = mView.findViewById(R.id.tv_marquee_title_two);
        titleOne.setText(data.getTitleOne());
        titleTwo.setText(data.getTitleTwo());
        return mView;
    }
}
