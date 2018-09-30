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
import com.oceanli.oceanmooc.app.R;
import com.oceanli.oceanmooc.app.business.home.adapter.ChoicenessGridRecyclerViewAdapter;
import com.oceanli.oceanmooc.app.business.home.models.ChoicenessCourseModel;
import com.oceanli.oceanmooc.app.business.MainDelegate;
import com.scwang.smartrefresh.header.PhoenixHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

/**
 * Created by ocean on 2018/9/25
 * Author :  ocean
 * Email  :  348686686@qq.com
 */
public class CourseDelegate extends OceanDelegate{

    @BindView(R.id.iv_course_select)
    ImageView selectIv;
    @BindView(R.id.recycler_course_list)
    RecyclerView mRecyclerView;
    private ChoicenessGridRecyclerViewAdapter mAdapter;
    private List<ChoicenessCourseModel> mData;

    @BindView(R.id.smart_refresh_course)
    SmartRefreshLayout mSmartRefreshLayout;

    public static CourseDelegate newInstance(){
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

    private String[] optionsone = {
            "前端",
            "后端",
            "移动端",
            "游戏开发",
            "运维"
    };
    private String[][] optionsTwo = {
            {
                "HTML",
                    "CSS",
                    "Vue"
            },
            {
                "Java",
                    "Python",
                    "PHP"
            },
            {
                "Android",
                    "IOS",
                    "React Native"
            },
            {
                "Unity",
                    "Cocos2d",
                    "C++"
            },
            {
                "Linux",
                    "自动化运维",
                    "Shell"
            }
    };

    public void initView(View rootView){
        mImmersionBar.setStatusBarView(_mActivity,rootView.findViewById(R.id.view_course_fill));
        // 分类选择框
        final OptionsPickerView optionsPickerView = new OptionsPickerBuilder(_mActivity, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                Toast.makeText(_mActivity, "op1:" + options1 + ",op2:" + options2 + ",op3:" + options3, Toast.LENGTH_SHORT).show();
            }
        }).build();
        // 初始化分类数据(假数据)
        List<List<String>> twoData = new ArrayList<>();
        for (int i = 0; i < optionsTwo.length; i++) {
            List<String> oneList = new ArrayList<>();
            for (int j = 0; j < optionsTwo[i].length; j++) {
                oneList.add(optionsTwo[i][j]);
            }
            twoData.add(oneList);
        }
        optionsPickerView.setPicker(
                Arrays.asList(optionsone),
                twoData
        );
        selectIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                optionsPickerView.show();
            }
        });
        initRecycler(rootView);
        initRefresh();
    }

    public List<ChoicenessCourseModel> getCourseData(){
        List<ChoicenessCourseModel> list = new ArrayList<>();
        final String[] courseNames = {
                "乔布斯的设计艺术",
                "Spring Boot开发入门",
                "Spark大数据处理",
                "Python机器学习"
        };
        final String desc = "本课程是年度最佳课程，采用模块化讲解，循序渐进的输出知识，为了让学生更好的接收";
        for (int i = 0; i < 30; i++) {
            ChoicenessCourseModel choicenessCourseModel = new ChoicenessCourseModel();
            choicenessCourseModel.setCourseName(courseNames[i % 4]);
            choicenessCourseModel.setCourseDesc(desc);
            choicenessCourseModel.setPrice("￥99");
            list.add(choicenessCourseModel);
        }
        return list;
    }


    public void initRecycler(View rootView){
        mData = getCourseData();
        mAdapter = new ChoicenessGridRecyclerViewAdapter(R.layout.item_recycler_choiceness,mData);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(_mActivity,2);
        gridLayoutManager.setOrientation(LinearLayout.VERTICAL);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ((MainDelegate)getParentFragment()).startBrotherFragment(CourseParticularsDelegate.newInstance());
            }
        });
    }

    public void initRefresh(){
        mSmartRefreshLayout.setRefreshHeader(new PhoenixHeader(_mActivity));
    }

}
