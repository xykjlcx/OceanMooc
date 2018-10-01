package com.oceanli.oceanmooc.app.business.course.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.oceanli.ocean.core.delegates.OceanDelegate;
import com.oceanli.ocean.core.net.RestClient;
import com.oceanli.ocean.core.net.callback.ISuccess;
import com.oceanli.oceanmooc.app.OmConstant;
import com.oceanli.oceanmooc.app.R;
import com.oceanli.oceanmooc.app.business.home.adapter.ChoicenessGridRecyclerViewAdapter;
import com.oceanli.oceanmooc.app.business.home.models.CourseVoModel;
import com.oceanli.oceanmooc.app.business.MainDelegate;
import com.oceanli.oceanmooc.app.other.utils.OmUtil;
import com.scwang.smartrefresh.header.PhoenixHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

/**
 * Created by ocean on 2018/9/25 Author :  ocean Email  :  348686686@qq.com
 */
public class CourseDelegate extends OceanDelegate {
    @BindView(R.id.iv_course_select)
    ImageView selectIv;
    @BindView(R.id.recycler_course_list)
    RecyclerView mRecyclerView;
    @BindView(R.id.smart_refresh_course)
    SmartRefreshLayout mSmartRefreshLayout;
    /**
     * 当前请求页码 初始化为第0页
     */
    private static Integer PAGE_NUM = 0;
    /**
     * 每页显示item数 默认为8 得到数据时判断，当json数据size小于该值，则下次上拉记载则不执行请求
     */
    private static Integer SIZE = 14;
    /**
     * 是否到底部，默认已经到最底
     */
    private static boolean IS_BOTTOM = false;
    private ChoicenessGridRecyclerViewAdapter mAdapter;
    private List<CourseVoModel.DataBean> mData;

    public static CourseDelegate newInstance() {
        Bundle bundle = new Bundle();
        CourseDelegate courseDelegate = new CourseDelegate();
        courseDelegate.setArguments(bundle);
        return courseDelegate;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_course;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initView(rootView);
    }

    private String[] optionsone = {"前端", "后端", "移动端", "游戏开发", "运维"};
    private String[][] optionsTwo = {{"HTML", "CSS", "Vue"}, {"Java", "Python", "PHP"}, {"Android", "IOS", "React Native"}, {"Unity", "Cocos2d",
            "C++"}, {"Linux", "自动化运维", "Shell"}};

    public void initView(View rootView) {
        mImmersionBar.setStatusBarView(_mActivity, rootView.findViewById(R.id.view_course_fill)); /* 分类选择框*/
        final OptionsPickerView optionsPickerView = new OptionsPickerBuilder(_mActivity, (options1, options2, options3, v) -> Toast.makeText
                (_mActivity, "op1:" + options1 + ",op2:" + options2 + ",op3:" + options3, Toast.LENGTH_SHORT).show()).build();/* 初始化分类数据(假数据)*/
        List<List<String>> twoData = new ArrayList<>();
        for (int i = 0; i < optionsTwo.length; i++) {
            List<String> oneList = new ArrayList<>();
            for (int j = 0; j < optionsTwo[i].length; j++) oneList.add(optionsTwo[i][j]);
            twoData.add(oneList);
        }
        optionsPickerView.setPicker(Arrays.asList(optionsone), twoData);
        selectIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                optionsPickerView.show();
            }
        });
        initRecycler();
        initRefresh();
    }

    public void initRecycler() {
        mData = new ArrayList<>();
        mAdapter = new ChoicenessGridRecyclerViewAdapter(R.layout.item_recycler_choiceness, mData);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(_mActivity, 2);
        gridLayoutManager.setOrientation(LinearLayout.VERTICAL);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        mAdapter.setOnItemClickListener((adapter, view, position) -> ((MainDelegate) getParentFragment()).startBrotherFragment
                (CourseParticularsDelegate.newInstance()));
        setCourseListData(true);
    }

    public void initRefresh() {
        mSmartRefreshLayout.setRefreshHeader(new PhoenixHeader(_mActivity));
        mSmartRefreshLayout.setOnRefreshListener(refreshLayout -> {
            PAGE_NUM = 0;
            SIZE = 14;
            IS_BOTTOM = false;
            setCourseListData(true);
            refreshLayout.finishRefresh();
        });
        mSmartRefreshLayout.setOnLoadMoreListener(refreshLayout -> {/* 上拉加载*/
            if (!IS_BOTTOM) {
                PAGE_NUM++;
                setCourseListData(false);
            } else OmUtil.toastInfo(_mActivity, "已经到底了!");
            refreshLayout.finishLoadMore();
        });
    }

    public void setCourseListData(boolean isFirst) {
        RestClient.builder().url(OmConstant.BASE_URL + OmConstant.REQUEST_URL_POST_RECOMMEND).params("page", PAGE_NUM).params("size", SIZE).success(response -> {
            CourseVoModel courseVoModel = OmUtil.getGson().fromJson(response, CourseVoModel.class);
            if (courseVoModel.getCode() == OmConstant.SUCCESS_CODE) {
                int dataCount = 0;
                if (courseVoModel.getData() != null) dataCount = courseVoModel.getData().size();
                if (dataCount > 0) IS_BOTTOM = false;
                if (isFirst) mData.clear();
                if (!IS_BOTTOM) {
                    for (int i = 0; i < courseVoModel.getData().size(); i++) mData.add(courseVoModel.getData().get(i));
                    mAdapter.notifyDataSetChanged();
                } else {
                }
                if (dataCount < SIZE) IS_BOTTOM = true;
            }
        }).build().post();
    }
}
