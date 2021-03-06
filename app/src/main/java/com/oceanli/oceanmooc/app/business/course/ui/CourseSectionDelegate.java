package com.oceanli.oceanmooc.app.business.course.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.oceanli.ocean.core.delegates.OceanDelegate;
import com.oceanli.ocean.core.net.RestClient;
import com.oceanli.oceanmooc.app.OmConstant;
import com.oceanli.oceanmooc.app.R;
import com.oceanli.oceanmooc.app.business.course.adapter.CourseSectionExpandableAdapter;
import com.oceanli.oceanmooc.app.business.course.models.ChapterSectionModel;
import com.oceanli.oceanmooc.app.business.home.models.CourseVoModel;
import com.oceanli.oceanmooc.app.callback.IOnCourseSectionChildOnclickCallBack;
import com.oceanli.ocean.core.event.OceanMessageEvent;
import com.oceanli.oceanmooc.app.business.course.models.SectionChildModel;
import com.oceanli.oceanmooc.app.business.course.models.SectionGroupModel;
import com.oceanli.oceanmooc.app.other.utils.OmUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by ocean on 2018/9/28 Author :  ocean Email  :  348686686@qq.com
 */
public class CourseSectionDelegate extends OceanDelegate {
    @BindView(R.id.recycler_course_section)
    RecyclerView mRecyclerView;
    @BindView(R.id.layout_course_section_empty)
    NestedScrollView emptyLayout;

    private CourseSectionExpandableAdapter mExpandableAdapter;
    private List<MultiItemEntity> mData;
    private CourseVoModel.DataBean receiveCourseData;

    public static CourseSectionDelegate newInstance() {
        CourseSectionDelegate courseSectionDelegate = new CourseSectionDelegate();
        return courseSectionDelegate;
    }

    public static CourseSectionDelegate newInstance(Bundle bundle) {
        CourseSectionDelegate courseSectionDelegate = new CourseSectionDelegate();
        courseSectionDelegate.setArguments(bundle);
        return courseSectionDelegate;
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        initData();
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_course_section;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        handleRevicerData();
        showSectionPage(false);
        initView(rootView);
    }

    public void handleRevicerData(){
        Bundle bundle = getArguments();
        receiveCourseData = (CourseVoModel.DataBean) bundle.getSerializable(OmConstant.BUNDLE_COURSE);
    }

    public void initView(View rootView) {
//        mData = generateData();
        mData = new ArrayList<>();
        mExpandableAdapter = new CourseSectionExpandableAdapter(mData);
        final LinearLayoutManager manager = new LinearLayoutManager(_mActivity);
        manager.setOrientation(LinearLayout.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mExpandableAdapter);
        mExpandableAdapter.expandAll();
        mExpandableAdapter.setIOnCourseSectionChildOnclickCallBack(new IOnCourseSectionChildOnclickCallBack() {
            @Override
            public void onSectionChildClick(int adapterPos, int displayPos, SectionChildModel
                    childModel) {/*                Toast.makeText (_mActivity, "callback出来了：" +
                    adapterPos, Toast.LENGTH_SHORT).show();*/
                OceanMessageEvent event = new OceanMessageEvent();
                // 发送章节被点击的事件
                // 在我的学习中插入新的学习记录(同一门课程，更新学习的章节信息，方便我的课程中的最后学习的章节统计)
                event.setMsg("skipSection");
                event.setData(childModel);
                EventBus.getDefault().post(event);
            }
        });
    }

    public void initData() {
        if (receiveCourseData != null){
            requestAllChapterAndSection(receiveCourseData.getId());
        }
    }

//    /**
//     * 模拟数据
//     * @return
//     */
//    private ArrayList<MultiItemEntity> generateData() {
//        int groupCount = 4;
//        int childCount = 5;
//        ArrayList<MultiItemEntity> res = new ArrayList<>();
//        final String[] groupStrs = {"什么是Spring Boot?", "快速入门", "进阶", "实战"};
//        final String[] childStrs = {"环境搭建IDEA", "测试样例编写", "Dao层搭建", "基于注解扫描的Bean加载", "约定大于配置"};
//        for (int i = 0; i < groupCount; i++) {
//            SectionGroupModel groupModel = new SectionGroupModel(i, groupStrs[i % groupCount]);
//            for (int j = 0; j < childCount; j++)
//                groupModel.addSubItem(new SectionChildModel(j, childStrs[j % childCount]));
//            res.add(groupModel);
//        }
//        return res;
//    }

    private List<ChapterSectionModel.DataBean.ChapterBean> chapterBeanList = null;
    private List<List<ChapterSectionModel.DataBean.SectionBean>> sectionBeanList = null;/*
    获取所有课程章节*/

    public void showSectionPage(boolean isShow){
        if (isShow){
            emptyLayout.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);
        }else {
            emptyLayout.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.GONE);
        }
    }

    public void requestAllChapterAndSection(int courseId) {
        RestClient.builder().url(OmConstant.BASE_URL + OmConstant
                .REQUEST_URL_POST_COURSE_SECTIONS).params("courseId", courseId).success(response
                -> {
            ChapterSectionModel chapterSectionModel = OmUtil.getGson().fromJson(response,
                    ChapterSectionModel.class);
            if (chapterSectionModel.getCode() == OmConstant.SUCCESS_CODE) {
                if (chapterSectionModel != null){
                    chapterBeanList = chapterSectionModel.getData().getChapter();
                    sectionBeanList = chapterSectionModel.getData().getSection();
                    mData.clear();
                    for (int i = 0; i < chapterBeanList.size(); i++) {
                        SectionGroupModel sectionGroupModel = new SectionGroupModel(chapterBeanList.get(i).getId(), chapterBeanList.get(i).getSectionName());
                        for (int i1 = 0; i1 < sectionBeanList.get(i).size(); i1++) {
                            ChapterSectionModel.DataBean.SectionBean netSectionData = sectionBeanList.get(i).get(i1);
                            SectionChildModel childModel = new SectionChildModel();
                            childModel.setId(netSectionData.getId());
                            childModel.setParentId(netSectionData.getParentId());
                            childModel.setCourseId(netSectionData.getCourseId());
                            childModel.setDuration(netSectionData.getDuration());
                            childModel.setSectionName(netSectionData.getSectionName());
                            childModel.setVideoUrl(netSectionData.getVideoUrl());
                            sectionGroupModel.addSubItem(childModel);
                        }
                        mData.add(sectionGroupModel);
                    }
                    mExpandableAdapter.notifyDataSetChanged();
                    mExpandableAdapter.expandAll();
                    // 成功获得章节数据
                    showSectionPage(true);
                }
            }
        }).build().post();
    }
}
