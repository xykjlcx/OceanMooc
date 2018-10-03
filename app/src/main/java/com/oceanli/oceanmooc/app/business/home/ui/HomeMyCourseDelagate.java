package com.oceanli.oceanmooc.app.business.home.ui;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.oceanli.ocean.core.delegates.OceanDelegate;
import com.oceanli.ocean.core.net.RestClient;
import com.oceanli.ocean.core.net.callback.ISuccess;
import com.oceanli.oceanmooc.app.OmConstant;
import com.oceanli.oceanmooc.app.R;
import com.oceanli.oceanmooc.app.business.home.adapter.MyCourseRecyclerViewAdapter;
import com.oceanli.oceanmooc.app.business.home.models.MyCourseModel;
import com.oceanli.oceanmooc.app.other.utils.OmUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by ocean on 2018/9/25
 * Author :  ocean
 * Email  :  348686686@qq.com
 */
public class HomeMyCourseDelagate extends OceanDelegate {

    @BindView(R.id.start_refresh_my_course)
    SmartRefreshLayout mSmartRefreshLayout;
    @BindView(R.id.recycler_my_course_list)
    RecyclerView mRecyclerView;
    @BindView(R.id.iv_my_course_lately)
    ImageView latelyImg;
    @BindView(R.id.tv_my_course_lately_coursename)
    TextView latelyCourseNameTv;
    @BindView(R.id.tv_my_course_lately_lasttime)
    TextView latelyLasteTimeTv;

    private MyCourseRecyclerViewAdapter mMyCourseRecyclerViewAdapter;
    private List<MyCourseModel.DataBean> mData;

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
        initRefresh();
        initRecycler(rootView);
    }


    public void initRecycler(View rootView){
        mData = new ArrayList<>();
        mMyCourseRecyclerViewAdapter = new MyCourseRecyclerViewAdapter(R.layout.item_recycler_my_course,mData);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(_mActivity);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mMyCourseRecyclerViewAdapter);
        mMyCourseRecyclerViewAdapter.bindToRecyclerView(mRecyclerView);
        mMyCourseRecyclerViewAdapter.setEmptyView(R.layout.layout_empty);
        mMyCourseRecyclerViewAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        setMyCourseRecyclerData(1);
    }

    public void initRefresh(){
        mSmartRefreshLayout.setRefreshHeader(new ClassicsHeader(_mActivity));
        // 禁用上拉加载
        mSmartRefreshLayout.setEnableLoadMore(false);
        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                setMyCourseRecyclerData(1);
                mSmartRefreshLayout.finishRefresh();
            }
        });
    }

    @SuppressLint("NewApi")
    public void setMyCourseRecyclerData(int userId){
        RestClient.builder()
                .url(OmConstant.BASE_URL + OmConstant.REQUEST_URL_POST_MY_COURSES)
                .params("userId",userId)
                .success(response -> {
                    mData.clear();
                    MyCourseModel myCourseModel = OmUtil.getGson().fromJson(response,MyCourseModel.class);
                    if (myCourseModel.getCode() == OmConstant.SUCCESS_CODE){
                        List<MyCourseModel.DataBean> dataBeanList = myCourseModel.getData();
                        latelyCourseNameTv.setText(dataBeanList.get(0).getCourseVo().getCourseName());
                        latelyLasteTimeTv.setText("最后学习时间： " + dataBeanList.get(0).getLastStudyTime());
                        Glide.with(_mActivity)
                                .load(dataBeanList.get(0).getCourseVo().getImgUrl())
                                .centerCrop()
                                .into(latelyImg);
                        dataBeanList.remove(0);
                        dataBeanList.forEach(bean -> mData.add(bean));
                        mMyCourseRecyclerViewAdapter.notifyDataSetChanged();
                    } else if (myCourseModel.getCode() == OmConstant.ERROR_CODE) {
                        OmUtil.toastError(_mActivity,"服务器正在开小差...");
                    }
                })
                .build()
                .post();
    }

}
