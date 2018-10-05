package com.oceanli.oceanmooc.app.business.user.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.oceanli.ocean.core.delegates.OceanDelegate;
import com.oceanli.ocean.core.net.RestClient;
import com.oceanli.oceanmooc.app.OmConstant;
import com.oceanli.oceanmooc.app.R;
import com.oceanli.oceanmooc.app.business.MainDelegate;
import com.oceanli.oceanmooc.app.business.course.ui.CourseParticularsDelegate;
import com.oceanli.oceanmooc.app.business.home.adapter.RecommendRecyclerViewAdapter;
import com.oceanli.oceanmooc.app.business.home.models.CourseVoModel;
import com.oceanli.oceanmooc.app.business.user.adapter.MyCollectCoursesRecyclerViewAdapter;
import com.oceanli.oceanmooc.app.business.user.models.NetUserModel;
import com.oceanli.oceanmooc.app.other.utils.OmUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by ocean on 2018/10/5
 * Author :  ocean
 * Email  :  348686686@qq.com
 */
public class MyCollectDelegate extends OceanDelegate {
    @BindView(R.id.refresh_my_collect)
    SmartRefreshLayout mSmartRefreshLayout;
    @BindView(R.id.recycler_my_collect)
    RecyclerView mRecyclerView;
    private MyCollectCoursesRecyclerViewAdapter mAdapter;
    private List<CourseVoModel.DataBean> mData;
    private NetUserModel.DataBean userDataBean;
    private int cancle_pos = -1;

    public static MyCollectDelegate newInstance(){
        MyCollectDelegate myCollectDelegate = new MyCollectDelegate();
        return myCollectDelegate;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_my_collect;
    }

    @Override
    public boolean isSwip() {
        return true;
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        if (userDataBean != null){
            requestAllCollectCourses(userDataBean.getId());
        }
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mImmersionBar.setStatusBarView(_mActivity, rootView.findViewById(R.id.view_collect_fill));
        userDataBean = OmUtil.getCacheUserInfo();
        initRecycler();
        initRefresh();
    }

    public void initRefresh(){
        mSmartRefreshLayout.setOnRefreshListener(refreshLayout -> {
            if (userDataBean != null){
                requestAllCollectCourses(userDataBean.getId());
            }
            refreshLayout.finishRefresh();
        });
    }

    public void initRecycler(){
        mData = new ArrayList<>();
        mAdapter = new MyCollectCoursesRecyclerViewAdapter(R.layout.item_recycler_my_collect, mData);
        LinearLayoutManager layoutManager = new LinearLayoutManager(_mActivity);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.bindToRecyclerView(mRecyclerView);
        mAdapter.setEmptyView(R.layout.layout_empty);
        mAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        mAdapter.isFirstOnly(false);
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            // todo 跳转课程详情页
            Bundle bundle = new Bundle();
            bundle.putSerializable(OmConstant.BUNDLE_COURSE,mData.get(position));
            start(CourseParticularsDelegate.newInstance(bundle));
        });
        mAdapter.setOnCancleCollectCourseOnClickCallBack((position,item) -> {
            // 取消收藏点击
            cancle_pos = position;
            if (userDataBean != null){
                requestCancleCollect(userDataBean.getId(),item.getId());
            }
        });
    }

    // 获取所有收藏课程
    @SuppressLint("NewApi")
    public void requestAllCollectCourses(int userId){
        RestClient.builder()
                .url(OmConstant.BASE_URL + OmConstant.REQUEST_URL_POST_ALL_COLLECT_COURSE)
                .params("userId",userId)
                .loader(_mActivity)
                .success(response -> {
                    CourseVoModel courseVoModel = OmUtil.getGson().fromJson(response,CourseVoModel.class);
                    if (courseVoModel.getCode() == OmConstant.SUCCESS_CODE){
                        List<CourseVoModel.DataBean> dataBeanList = courseVoModel.getData();
                        mData.clear();
                        if (dataBeanList != null
                                && !dataBeanList.isEmpty()){
                            dataBeanList.forEach(dataBean -> {
                                mData.add(dataBean);
                            });
                        }else {
                            OmUtil.toastWarning(_mActivity,courseVoModel.getMsg());
                        }
                    }else {
                        OmUtil.toastWarning(_mActivity,courseVoModel.getMsg());
                    }
                    mAdapter.notifyDataSetChanged();
                })
                .build()
                .post();
    }

    // 取消收藏
    public void requestCancleCollect(int userId,int courseId){
        RestClient.builder()
                .url(OmConstant.BASE_URL + OmConstant.REQUEST_URL_POST_DELETE_COLLECT)
                .params("userId",userId)
                .params("courseId",courseId)
                .loader(_mActivity)
                .success(response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        if (jsonObject.getInt("code") == OmConstant.SUCCESS_CODE){
                            OmUtil.toastSuccess(_mActivity,jsonObject.getString("msg"));
                            mData.remove(cancle_pos);
                            requestAllCollectCourses(userId);
                        }else {
                            OmUtil.toastError(_mActivity,jsonObject.getString("msg"));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                })
                .build()
                .post();
    }

}
