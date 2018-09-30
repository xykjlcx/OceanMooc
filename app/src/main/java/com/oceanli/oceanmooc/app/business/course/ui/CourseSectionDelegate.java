package com.oceanli.oceanmooc.app.business.course.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.oceanli.ocean.core.delegates.OceanDelegate;
import com.oceanli.oceanmooc.app.R;
import com.oceanli.oceanmooc.app.business.course.adapter.CourseSectionExpandableAdapter;
import com.oceanli.oceanmooc.app.callback.IOnCourseSectionChildOnclickCallBack;
import com.oceanli.ocean.core.event.OceanMessageEvent;
import com.oceanli.oceanmooc.app.business.course.models.SectionChildModel;
import com.oceanli.oceanmooc.app.business.course.models.SectionGroupModel;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by ocean on 2018/9/28
 * Author :  ocean
 * Email  :  348686686@qq.com
 */
public class CourseSectionDelegate extends OceanDelegate {

    @BindView(R.id.recycler_course_section)
    RecyclerView mRecyclerView;
    private CourseSectionExpandableAdapter mExpandableAdapter;
    private List<MultiItemEntity> mData;


    public static CourseSectionDelegate newInstance(){
        CourseSectionDelegate courseSectionDelegate = new CourseSectionDelegate();
        return courseSectionDelegate;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_course_section;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initView(rootView);
        initData();
    }

    public void initView(View rootView){
        mData = generateData();
        mExpandableAdapter = new CourseSectionExpandableAdapter(mData);
        final LinearLayoutManager manager = new LinearLayoutManager(_mActivity);
        manager.setOrientation(LinearLayout.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mExpandableAdapter);
        mExpandableAdapter.expandAll();
        mExpandableAdapter.setIOnCourseSectionChildOnclickCallBack(new IOnCourseSectionChildOnclickCallBack() {
            @Override
            public void onSectionChildClick(int adapterPos, int displayPos, SectionChildModel childModel) {
//                Toast.makeText(_mActivity, "callback出来了：" + adapterPos, Toast.LENGTH_SHORT).show();
                EventBus.getDefault().post(new OceanMessageEvent("jass"));
            }
        });
    }

    public void initData(){

    }


    private ArrayList<MultiItemEntity> generateData() {
        int groupCount = 4;
        int childCount = 5;
        ArrayList<MultiItemEntity> res = new ArrayList<>();

        final String[] groupStrs = {
                "什么是Spring Boot?",
                "快速入门",
                "进阶",
                "实战"
        };

        final String[] childStrs = {
                "环境搭建IDEA",
                "测试样例编写",
                "Dao层搭建",
                "基于注解扫描的Bean加载",
                "约定大于配置"
        };

        for (int i = 0; i < groupCount; i++) {
            SectionGroupModel groupModel = new SectionGroupModel(i,groupStrs[i % groupCount]);
            for (int j = 0; j < childCount; j++) {
                groupModel.addSubItem(new SectionChildModel(j,childStrs[j % childCount]));
            }
            res.add(groupModel);
        }
        return res;
    }

}
