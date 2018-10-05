package com.oceanli.oceanmooc.app.business.course.adapter;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.oceanli.oceanmooc.app.R;
import com.oceanli.oceanmooc.app.callback.IOnCourseSectionChildOnclickCallBack;
import com.oceanli.oceanmooc.app.business.course.models.SectionChildModel;
import com.oceanli.oceanmooc.app.business.course.models.SectionGroupModel;

import java.util.List;

/**
 * Created by ocean on 2018/9/29
 * Author :  ocean
 * Email  :  348686686@qq.com
 */
public class CourseSectionExpandableAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity,BaseViewHolder> {

    private static final String TAG = CourseSectionExpandableAdapter.class.getSimpleName();

    public static final int TYPE_GROUP = 0;
    public static final int TYPE_CHILD = 1;
    private IOnCourseSectionChildOnclickCallBack IOnCourseSectionChildOnclickCallBack;

    public void setIOnCourseSectionChildOnclickCallBack(IOnCourseSectionChildOnclickCallBack IOnCourseSectionChildOnclickCallBack) {
        this.IOnCourseSectionChildOnclickCallBack = IOnCourseSectionChildOnclickCallBack;
    }

    public CourseSectionExpandableAdapter(List<MultiItemEntity> data) {
        super(data);
        addItemType(TYPE_GROUP, R.layout.item_recycler_expandable_group);
        addItemType(TYPE_CHILD, R.layout.item_recycler_expandable_child);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final MultiItemEntity item) {
        switch (helper.getItemViewType()){
            case TYPE_GROUP:
                // 章
                final SectionGroupModel groupModel = (SectionGroupModel) item;
                helper.setText(R.id.tv_course_section_item_group,groupModel.getSectionName());
                helper.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = helper.getAdapterPosition();
                        if (groupModel.isExpanded()){
                            collapse(pos);
                        }else {
                            expand(pos);
                        }
                    }
                });
                break;
            case TYPE_CHILD:
                // 节
                final SectionChildModel childModel = (SectionChildModel) item;
                final int adapterPosition = helper.getAdapterPosition();
                final int displayPosition = helper.getLayoutPosition();
                helper.setText(R.id.tv_course_section_item_child,childModel.getSectionName());
                helper.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        Toast.makeText(mContext, "点击了子项:" + ((SectionChildModel) item).getSectionName() + "pos:" + helper.getAdapterPosition(), Toast.LENGTH_SHORT).show();
                        if (IOnCourseSectionChildOnclickCallBack != null){
                            if (beforeTv != null){
                                beforeTv.setTextColor(Color.parseColor("#8f8f8f"));
                            }
                            beforeTv = helper.getView(R.id.tv_course_section_item_child);
                            beforeTv.setTextColor(Color.parseColor("#35b0f2"));
                            IOnCourseSectionChildOnclickCallBack.onSectionChildClick(adapterPosition,displayPosition,childModel);
                        }
                    }
                });
                break;
        }
    }

    private TextView beforeTv = null;


}
