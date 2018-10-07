package com.oceanli.oceanmooc.app.business.course.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.oceanli.ocean.core.delegates.OceanDelegate;
import com.oceanli.ocean.core.net.RestClient;
import com.oceanli.oceanmooc.app.OmConstant;
import com.oceanli.oceanmooc.app.R;
import com.oceanli.oceanmooc.app.business.MainDelegate;
import com.oceanli.oceanmooc.app.business.home.adapter.RecommendRecyclerViewAdapter;
import com.oceanli.oceanmooc.app.business.home.models.CourseVoModel;
import com.oceanli.oceanmooc.app.other.utils.OmUtil;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ocean on 2018/10/7
 * Author :  ocean
 * Email  :  348686686@qq.com
 */
public class CourseSearchDelegate extends OceanDelegate {
    @BindView(R.id.et_search_content)
    EditText searchContentEd;
    @BindView(R.id.iv_search_ok)
    ImageView okSearchIv;
    @BindView(R.id.refresh_search)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.recycler_search)
    RecyclerView mRecyclerView;

    @OnClick(R.id.iv_search_ok)
    public void searchOnClick(){
        // 调用搜索课程请求接口
        reSearch();
    }

    private RecommendRecyclerViewAdapter mAdapter;
    private List<CourseVoModel.DataBean> mData;
    private Integer PAGE_NUM = 0;
    private Integer SIZE = 4;
    private boolean IS_BOTTOM = false;

    @SuppressLint("NewApi")
    public void requestSearchCourses(String courseName, int page, int size,boolean isNew){
        RestClient.builder()
                .url(OmConstant.BASE_URL + OmConstant.REQUEST_URL_POST_SEARCH_COURSE)
                .params("courseName",courseName)
                .params("page",page)
                .params("size",size)
                .loader(_mActivity)
                .success(response -> {
                    CourseVoModel courseVoModel = OmUtil.getGson().fromJson(response,CourseVoModel.class);
                    if (courseVoModel.getCode() == OmConstant.SUCCESS_CODE){
                        if (isNew){
                            // 如果是新的搜索，则清空已有数据 否则直接add
                            mData.clear();
                        }
                        List<CourseVoModel.DataBean> dataBeanList = courseVoModel.getData();
                        // 本次请求得到的数据数量
                        int thisDataCount = dataBeanList.size();
                        if (thisDataCount < size){
                            IS_BOTTOM = true;
                        }
                        dataBeanList.forEach(dataBean -> {
                            mData.add(dataBean);
                        });
                    }else {
                        // 没有搜索到课程
                        OmUtil.toastWarning(_mActivity,"没有匹配课程");
                    }
                    mAdapter.notifyDataSetChanged();
                })
                .build()
                .post();
    }

    public static CourseSearchDelegate newInstance(){
        CourseSearchDelegate courseSearchDelegate = new CourseSearchDelegate();
        return courseSearchDelegate;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_course_search;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mImmersionBar.setStatusBarView(_mActivity, rootView.findViewById(R.id.view_course_search_fill));
        handleKeyEnter();
        initRecycler();
        initRefresh();
    }

    public void initRecycler(){
        mData = new ArrayList<>();
        mAdapter = new RecommendRecyclerViewAdapter(R.layout.item_recycler_recommend, mData);
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
                bundle.putSerializable(OmConstant.BUNDLE_COURSE,mData.get(position));
                start(OmUtil.isLoginSkip(OmConstant.SKIP_PARTICULARS,CourseParticularsDelegate
                        .newInstance(bundle)));
            }
        });
    }

    public void reSearch(){
        IS_BOTTOM = false;
        PAGE_NUM = 0;
        // 调用搜索课程请求接口
        String courseNameStr = searchContentEd.getText().toString();
        if (courseNameStr.equals("")){
            OmUtil.toastWarning(_mActivity,"请输入搜索的课程名称");
            return;
        }
        requestSearchCourses(courseNameStr,PAGE_NUM,SIZE,true);
    }

    public void handleKeyEnter(){
        searchContentEd.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())) {
                    // 处理事件
                    reSearch();
                }
                return false;
            }
        });
    }

    public void initRefresh(){
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                // todo 下拉
                reSearch();
                refreshLayout.finishRefresh();
            }
        });
        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                // todo 上拉
                if (!IS_BOTTOM){
                    // 没有到底
                    String courseNameStr = searchContentEd.getText().toString();
                    if (courseNameStr.equals("")){
                        OmUtil.toastWarning(_mActivity,"请输入搜索的课程名称");
                        return;
                    }
                    requestSearchCourses(courseNameStr,++PAGE_NUM,SIZE,false);
                }else {
                    OmUtil.toastWarning(_mActivity,"没有更多匹配");
                }
                refreshLayout.finishLoadMore();
            }
        });
    }

}
