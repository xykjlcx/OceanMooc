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
import com.oceanli.oceanmooc.app.business.course.ui.CourseParticularsDelegate;
import com.oceanli.oceanmooc.app.business.home.models.CourseVoModel;
import com.oceanli.oceanmooc.app.business.user.adapter.MyCollectCoursesRecyclerViewAdapter;
import com.oceanli.oceanmooc.app.business.user.adapter.MyCommentRecyclerViewAdapter;
import com.oceanli.oceanmooc.app.business.user.models.MyCommentModel;
import com.oceanli.oceanmooc.app.business.user.models.NetUserModel;
import com.oceanli.oceanmooc.app.other.utils.OmUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by ocean on 2018/10/6
 * Author :  ocean
 * Email  :  348686686@qq.com
 */
public class MyCommentDelegate extends OceanDelegate {
    @BindView(R.id.refresh_my_comment)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.recycler_my_comment)
    RecyclerView mRecyclerView;

    private MyCommentRecyclerViewAdapter mAdapter;
    private List<MyCommentModel.DataBean> mData;
    private NetUserModel.DataBean userDataBean;

    public static MyCommentDelegate newInstance(){
        MyCommentDelegate myCommentDelegate = new MyCommentDelegate();
        return myCommentDelegate;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_my_comment;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mImmersionBar.setStatusBarView(_mActivity, rootView.findViewById(R.id.view_my_comment_fill));
        userDataBean = OmUtil.getCacheUserInfo();
        initRecycler();
        initRefresh();
    }

    public void initRefresh(){
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                if (userDataBean != null){
                    requestMyComments(userDataBean.getId());
                }
                refreshLayout.finishRefresh();
            }
        });
    }

    public void initRecycler(){
        mData = new ArrayList<>();
        mAdapter = new MyCommentRecyclerViewAdapter(R.layout.item_recycler_my_comment, mData);
        LinearLayoutManager layoutManager = new LinearLayoutManager(_mActivity);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.bindToRecyclerView(mRecyclerView);
        mAdapter.setEmptyView(R.layout.layout_empty);
        mAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        mAdapter.isFirstOnly(false);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putSerializable(OmConstant.BUNDLE_COURSE,mData.get(position).getCourseVo());
                start(CourseParticularsDelegate.newInstance(bundle));
            }
        });
        if (userDataBean != null){
            requestMyComments(userDataBean.getId());
        }
    }

    @SuppressLint("NewApi")
    public void requestMyComments(int userId){
        RestClient.builder()
                .url(OmConstant.BASE_URL + OmConstant.REQUEST_URL_POST_MY_COMMENTS)
                .params("userId",userId)
                .loader(_mActivity)
                .success(response -> {
                    MyCommentModel commentModel = OmUtil.getGson().fromJson(response,MyCommentModel.class);
                    if (commentModel.getCode() == OmConstant.SUCCESS_CODE){
                        mData.clear();
                        List<MyCommentModel.DataBean> dataBeanList = commentModel.getData();
                        dataBeanList.forEach(dataBean -> {
                            mData.add(dataBean);
                        });
                    }else {
                        OmUtil.toastError(_mActivity,commentModel.getMsg());
                    }
                    mAdapter.notifyDataSetChanged();
                })
                .build()
                .post();
    }

}
