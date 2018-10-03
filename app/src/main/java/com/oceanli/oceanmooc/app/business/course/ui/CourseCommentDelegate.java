package com.oceanli.oceanmooc.app.business.course.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.oceanli.ocean.core.delegates.OceanDelegate;
import com.oceanli.ocean.core.net.RestClient;
import com.oceanli.oceanmooc.app.OmConstant;
import com.oceanli.oceanmooc.app.R;
import com.oceanli.oceanmooc.app.business.course.adapter.CourseSectionCommentRecyclerViewAdapter;
import com.oceanli.oceanmooc.app.business.course.models.NetCommentModel;
import com.oceanli.oceanmooc.app.business.course.models.SectionCommentModel;
import com.oceanli.oceanmooc.app.other.utils.OmUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by ocean on 2018/9/28 Author :  ocean Email  :  348686686@qq.com
 */
public class CourseCommentDelegate extends OceanDelegate {
    @BindView(R.id.recycler_course_comment)
    RecyclerView recyclerView;
    private CourseSectionCommentRecyclerViewAdapter mCommentRecyclerViewAdapter;
    private List<SectionCommentModel> mData;

    public static CourseCommentDelegate newInstance() {
        CourseCommentDelegate courseCommentDelegate = new CourseCommentDelegate();
        return courseCommentDelegate;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_course_comment;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initConmentRecycle();
    }

    /* 初始化评论列表*/
    public void initConmentRecycle() {
        mData = new ArrayList<>();
        mCommentRecyclerViewAdapter = new CourseSectionCommentRecyclerViewAdapter(R.layout.item_recycler_course_section_comment, mData);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(_mActivity);
        linearLayoutManager.setOrientation(LinearLayout.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(mCommentRecyclerViewAdapter);
        mCommentRecyclerViewAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        requestNetComments(1,0,3);
    }

    /* 获取评论数据*/
    public List<SectionCommentModel> getData() {
        ArrayList<SectionCommentModel> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            SectionCommentModel commentModel = new SectionCommentModel();
            list.add(commentModel);
        }
        return list;
    }

    @SuppressLint("NewApi")
    public void requestNetComments(int courseId, int page, int size){
        RestClient.builder()
                .url(OmConstant.BASE_URL + OmConstant.REQUEST_URL_POST_COMMENTS)
                .params("courseId",courseId)
                .params("page",page)
                .params("size",size)
                .loader(_mActivity)
                .success(response -> {
                    NetCommentModel commentModel = OmUtil.getGson().fromJson(response,NetCommentModel.class);
                    if (commentModel.getCode() == OmConstant.SUCCESS_CODE){
                        mData.clear();
                        List<NetCommentModel.DataBean> dataBeanList = commentModel.getData();
                        dataBeanList.forEach(dataBean -> {
                            SectionCommentModel sectionCommentModel = new SectionCommentModel();
                            sectionCommentModel.setId(dataBean.getId());
                            sectionCommentModel.setHeadImgUrl(dataBean.getUserHeadImgUrl());
                            sectionCommentModel.setUserName(dataBean.getUserName());
                            sectionCommentModel.setContent(dataBean.getCommentContent());
                            sectionCommentModel.setTime(dataBean.getCommentTime());
                            mData.add(sectionCommentModel);
                        });
                        mCommentRecyclerViewAdapter.notifyDataSetChanged();
                    }
                })
                .build()
                .post();
    }

}
