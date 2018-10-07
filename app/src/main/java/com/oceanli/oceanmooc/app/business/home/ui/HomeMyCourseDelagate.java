package com.oceanli.oceanmooc.app.business.home.ui;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.oceanli.ocean.core.delegates.OceanDelegate;
import com.oceanli.ocean.core.event.OceanMessageEvent;
import com.oceanli.ocean.core.net.RestClient;
import com.oceanli.ocean.core.net.callback.ISuccess;
import com.oceanli.oceanmooc.app.OmConstant;
import com.oceanli.oceanmooc.app.R;
import com.oceanli.oceanmooc.app.business.MainDelegate;
import com.oceanli.oceanmooc.app.business.course.ui.CourseParticularsDelegate;
import com.oceanli.oceanmooc.app.business.home.adapter.MyCourseRecyclerViewAdapter;
import com.oceanli.oceanmooc.app.business.home.models.CourseVoModel;
import com.oceanli.oceanmooc.app.business.home.models.MyCourseModel;
import com.oceanli.oceanmooc.app.business.user.models.NetUserModel;
import com.oceanli.oceanmooc.app.other.utils.OmUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

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
    @BindView(R.id.layout_my_course)
    RelativeLayout firstCourseLayout;
    @BindView(R.id.include_empty_my_study)
    RelativeLayout emptyLayout;
    @BindView(R.id.appbar_my_studyed)
    AppBarLayout appBarLayout;

    // 设置空布局
    public void isShow(boolean isHaveData){
        if (isHaveData){
            emptyLayout.setVisibility(View.GONE);
            appBarLayout.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.VISIBLE);
        }else {
            emptyLayout.setVisibility(View.VISIBLE);
            appBarLayout.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.GONE);
        }
    }

    private CourseVoModel.DataBean mFirstCourse = null;
    private NetUserModel.DataBean userDataBean = null;

    @OnClick(R.id.layout_my_course)
    public void firstCourseOnClick(){
        if (mFirstCourse != null){
            Bundle bundle = new Bundle();
            bundle.putSerializable(OmConstant.BUNDLE_COURSE,mFirstCourse);
            ((MainDelegate) getParentFragment().getParentFragment()).startBrotherFragment(OmUtil.isLoginSkip(OmConstant.SKIP_PARTICULARS, CourseParticularsDelegate
                    .newInstance(bundle)));
        }
    }

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
        userDataBean = OmUtil.getCacheUserInfo();
        initView(rootView);
    }

    public void initView(View rootView){
        initRefresh();
        initRecycler(rootView);
        isShow(false);
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
        mMyCourseRecyclerViewAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                CourseVoModel.DataBean dataBean = mData.get(position).getCourseVo();
                bundle.putSerializable(OmConstant.BUNDLE_COURSE,dataBean);
                ((MainDelegate) getParentFragment().getParentFragment()).startBrotherFragment(OmUtil.isLoginSkip(OmConstant.SKIP_PARTICULARS, CourseParticularsDelegate
                        .newInstance(bundle)));
            }
        });
        if (userDataBean != null){
            setMyCourseRecyclerData(userDataBean.getId());
        }
    }

    public void initRefresh(){
        mSmartRefreshLayout.setRefreshHeader(new ClassicsHeader(_mActivity));
        // 禁用上拉加载
        mSmartRefreshLayout.setEnableLoadMore(false);
        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                if (userDataBean != null ){
                    setMyCourseRecyclerData(userDataBean.getId());
                }
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
                        isShow(true);
                        List<MyCourseModel.DataBean> dataBeanList = myCourseModel.getData();
                        latelyCourseNameTv.setText(dataBeanList.get(0).getCourseVo().getCourseName());
                        latelyLasteTimeTv.setText("最后学习时间： " + dataBeanList.get(0).getLastStudyTime());
                        // 获取第一个课程(最近一次学习)
                        mFirstCourse = dataBeanList.get(0).getCourseVo();
                        Glide.with(_mActivity)
                                .load(mFirstCourse.getImgUrl())
                                .centerCrop()
                                .into(latelyImg);
                        dataBeanList.remove(0);
                        dataBeanList.forEach(bean -> mData.add(bean));
                        mMyCourseRecyclerViewAdapter.notifyDataSetChanged();
                    } else if (myCourseModel.getCode() == OmConstant.ERROR_CODE) {
                        // todo 未学习任何课程设置空布局
                        isShow(false);
//                        OmUtil.toastWarning(_mActivity,myCourseModel.getMsg());
                    }
                })
                .build()
                .post();
    }

    @Override
    public void onMessageEvent(OceanMessageEvent event) {
        if (event.getMsg().equals("studyNewCourse")){
            if (userDataBean != null){
                setMyCourseRecyclerData(userDataBean.getId());
            }
        }
    }
}
