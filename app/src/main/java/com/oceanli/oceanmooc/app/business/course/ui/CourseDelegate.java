package com.oceanli.oceanmooc.app.business.course.ui;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.oceanli.ocean.core.delegates.OceanDelegate;
import com.oceanli.ocean.core.net.RestClient;
import com.oceanli.ocean.core.net.callback.ISuccess;
import com.oceanli.ocean.core.util.storage.OceanPreferences;
import com.oceanli.oceanmooc.app.OmConstant;
import com.oceanli.oceanmooc.app.R;
import com.oceanli.oceanmooc.app.business.course.models.CourseClassifyModel;
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
import butterknife.OnClick;

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
    @BindView(R.id.layout_course_skip_search)
    RelativeLayout skipSearchLayout;

    @OnClick(R.id.layout_course_skip_search)
    public void skipSearchOnClick(){
        ((MainDelegate) getParentFragment()).startBrotherFragment(CourseSearchDelegate.newInstance());
    }

    private OptionsPickerView optionsPickerView;
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
    /**
     * 默认分类ID 0 查询所有 规则：下拉刷新重置DEFAULT_CLASSIFY 上拉加载默认采用当前分类 分类选择器选择后为DEFAULT_CLASSIFY赋值 之后的请求都加上该字段
     */
    private static Integer DEFAULT_CLASSIFY = 0;
    private ChoicenessGridRecyclerViewAdapter mAdapter;
    private List<CourseVoModel.DataBean> mData;
    private List<CourseClassifyModel.DataBean.OneLevelBean> oneLevelBeanList;
    private List<List<CourseClassifyModel.DataBean.TwoLevelBean>> twoLevelBeanList;

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

//    private String[] optionsone = {"前端", "后端", "移动端", "游戏开发", "运维"};
//    private String[][] optionsTwo = {{"HTML", "CSS", "Vue"}, {"Java", "Python", "PHP"},
//            {"Android", "IOS", "React Native"}, {"Unity", "Cocos2d", "C++"}, {"Linux", "自动化运维",
//            "Shell"}};
    private List<String> optionsOne = new ArrayList<>();
    private List<List<String>> optionsTwo = new ArrayList<>();

    public void initView(View rootView) {
        mImmersionBar.setStatusBarView(_mActivity, rootView.findViewById(R.id.view_course_fill));
        /* 分类选择框*/
        optionsPickerView = new OptionsPickerBuilder(_mActivity,
                (options1, options2, options3, v) -> {
                    // todo 选择分类、更新列表
                    if (options1 == 0){
                        // 一级分类不限
                        DEFAULT_CLASSIFY = 0;
                    }else {
                        if (options2 == 0){
                            // 二级分类不限
                            DEFAULT_CLASSIFY = oneLevelBeanList.get(options1-1).getId();
                        }else {
                            DEFAULT_CLASSIFY = twoLevelBeanList.get(options1-1).get(options2-1).getId();
                        }
                    }
                    /**
                     * 每次筛选都将重新将页码置为1
                     * 每次筛选都会回到顶部
                     */
                    PAGE_NUM = 0;
                    setCourseListData(true);
                })
                .build();
        // 获取最新分类信息
        getAllClassify();
        selectIv.setOnClickListener(v -> optionsPickerView.show());
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
        mAdapter.bindToRecyclerView(mRecyclerView);
        mAdapter.setEmptyView(R.layout.layout_empty);
        mAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable(OmConstant.BUNDLE_COURSE,mData.get(position));
            ((MainDelegate) getParentFragment()).startBrotherFragment(OmUtil.isLoginSkip(OmConstant.SKIP_PARTICULARS,CourseParticularsDelegate
                    .newInstance(bundle)));
        });
        setCourseListData(true);
    }

    public void initRefresh() {
//        mSmartRefreshLayout.setRefreshHeader(new PhoenixHeader(_mActivity));
        mSmartRefreshLayout.setOnRefreshListener(refreshLayout -> {
            PAGE_NUM = 0;
            SIZE = 14;
            IS_BOTTOM = false;
            setCourseListData(true);
            // 获取最新分类信息
            getAllClassify();
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

    public void setCourseListData(boolean isBeforeClear) {
        RestClient.builder().url(OmConstant.BASE_URL + OmConstant.REQUEST_URL_POST_ALL_COURSES).loader(_mActivity).params("page", PAGE_NUM).params("size", SIZE).params("classify", DEFAULT_CLASSIFY).success(response -> {
            CourseVoModel courseVoModel = OmUtil.getGson().fromJson(response, CourseVoModel.class);
            if (courseVoModel.getCode() == OmConstant.SUCCESS_CODE) {
                int dataCount = 0;
                if (courseVoModel.getData() != null) dataCount = courseVoModel.getData().size();
                if (dataCount > 0) IS_BOTTOM = false;
                if (isBeforeClear) mData.clear();
                if (!IS_BOTTOM) {
                    for (int i = 0; i < courseVoModel.getData().size(); i++)
                        mData.add(courseVoModel.getData().get(i));
                    mAdapter.notifyDataSetChanged();
                } else {

                }
                if (dataCount < SIZE) IS_BOTTOM = true;
            }else {
                // 若分类下不存在课程，则重新刷新后 使用默认所有课程刷新UI
                DEFAULT_CLASSIFY = 0;
//                setCourseListData(true);
                OmUtil.toastWarning(_mActivity,"该分类下没有课程");
            }
        }).build().post();
    }


    @SuppressLint("NewApi")
    public void getAllClassify(){
        // TODO 增加数据库缓存
        RestClient.builder()
                .url(OmConstant.BASE_URL + OmConstant.REQUEST_URL_GET_CLASSIFY)
                .success(response -> {
//                    Toast.makeText(_mActivity, response, Toast.LENGTH_SHORT).show();
                    CourseClassifyModel classifyModel = OmUtil.getGson().fromJson(response,CourseClassifyModel.class);
                    if (classifyModel.getCode() == OmConstant.SUCCESS_CODE){
                        optionsOne.clear();
                        optionsTwo.clear();
                        CourseClassifyModel.DataBean dataBean = classifyModel.getData();
                        oneLevelBeanList = dataBean.getOneLevel();
                        twoLevelBeanList = dataBean.getTwoLevel();
                        /* 添加以及分类和二级分类不限 */
                        optionsOne.add("所有");
                        List<String> allTemp = new ArrayList<>();
                        allTemp.add("");
                        optionsTwo.add(allTemp);
                        for (int i = 0; i < oneLevelBeanList.size(); i++) {
                            optionsOne.add(oneLevelBeanList.get(i).getClassifyName());
                            List<String> temp = new ArrayList<>();
                            temp.add("不限");
                            for (int i1 = 0; i1 < twoLevelBeanList.get(i).size(); i1++) {
                                temp.add(twoLevelBeanList.get(i).get(i1).getClassifyName());
                            }
                            optionsTwo.add(temp);
                        }
                        optionsPickerView.setPicker(optionsOne,optionsTwo);
                    }
                })
                .build()
                .get();
    }
}
