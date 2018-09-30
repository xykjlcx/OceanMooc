package com.oceanli.oceanmooc.app.ui.delegates;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.oceanli.ocean.core.delegates.OceanDelegate;
import com.oceanli.oceanmooc.app.R;
import com.oceanli.oceanmooc.app.adapter.MyCourseRecyclerViewAdapter;
import com.oceanli.oceanmooc.app.models.MyCourseModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by ocean on 2018/9/25
 * Author :  ocean
 * Email  :  348686686@qq.com
 */
public class HomeMyCourseDelagate extends OceanDelegate {

    @BindView(R.id.recycler_my_course_list)
    RecyclerView mRecyclerView;

    private MyCourseRecyclerViewAdapter myCourseRecyclerViewAdapter;
    private List<MyCourseModel> mData;

    public static HomeMyCourseDelagate newInstance(){
        Bundle bundle = new Bundle();
        HomeMyCourseDelagate homeMyCourseDelagate = new HomeMyCourseDelagate();
        homeMyCourseDelagate.setArguments(bundle);
        return homeMyCourseDelagate;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_home_my_course;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initView(rootView);
    }

    public void initView(View rootView){
        initRecycler(rootView);
    }


    public void initRecycler(View rootView){
        mData = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            MyCourseModel myCourseModel = new MyCourseModel();
            myCourseModel.setId(i);
            myCourseModel.setCourseName("课程" + i);
            myCourseModel.setTeacherName("老师" + i);
            myCourseModel.setDuration("时长" + i);
            mData.add(myCourseModel);
        }
        myCourseRecyclerViewAdapter = new MyCourseRecyclerViewAdapter(R.layout.item_recycler_my_course,mData);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(_mActivity);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(myCourseRecyclerViewAdapter);
        myCourseRecyclerViewAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
    }

}
