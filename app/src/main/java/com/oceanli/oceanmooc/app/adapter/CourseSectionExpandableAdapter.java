package com.oceanli.oceanmooc.app.adapter;

import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.oceanli.oceanmooc.app.R;
import com.oceanli.oceanmooc.app.callback.OnCourseSectionChildOnclickCallBack;
import com.oceanli.oceanmooc.app.models.SectionChildModel;
import com.oceanli.oceanmooc.app.models.SectionGroupModel;

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
    private OnCourseSectionChildOnclickCallBack onCourseSectionChildOnclickCallBack;

    public void setOnCourseSectionChildOnclickCallBack(OnCourseSectionChildOnclickCallBack onCourseSectionChildOnclickCallBack) {
        this.onCourseSectionChildOnclickCallBack = onCourseSectionChildOnclickCallBack;
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
                        if (onCourseSectionChildOnclickCallBack != null)
                            onCourseSectionChildOnclickCallBack.onSectionChildClick(adapterPosition,displayPosition,childModel);
                    }
                });
                break;
        }
    }
}
