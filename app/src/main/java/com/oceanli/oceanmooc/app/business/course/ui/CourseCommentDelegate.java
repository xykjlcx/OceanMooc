package com.oceanli.oceanmooc.app.business.course.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.oceanli.ocean.core.delegates.OceanDelegate;
import com.oceanli.ocean.core.event.OceanMessageEvent;
import com.oceanli.ocean.core.net.RestClient;
import com.oceanli.oceanmooc.app.OmConstant;
import com.oceanli.oceanmooc.app.R;
import com.oceanli.oceanmooc.app.business.course.adapter.CourseSectionCommentRecyclerViewAdapter;
import com.oceanli.oceanmooc.app.business.course.models.NetCommentModel;
import com.oceanli.oceanmooc.app.business.course.models.SectionChildModel;
import com.oceanli.oceanmooc.app.business.course.models.SectionCommentModel;
import com.oceanli.oceanmooc.app.business.home.models.CourseVoModel;
import com.oceanli.oceanmooc.app.business.user.models.NetUserModel;
import com.oceanli.oceanmooc.app.other.utils.OmUtil;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ocean on 2018/9/28 Author :  ocean Email  :  348686686@qq.com
 */
public class CourseCommentDelegate extends OceanDelegate {
    @BindView(R.id.recycler_course_comment)
    RecyclerView recyclerView;
    @BindView(R.id.tv_comment_send)
    TextView sendCommentTv;
    @BindView(R.id.et_comment_content)
    EditText commentContentEt;
    @BindView(R.id.refresh_course_comment)
    SmartRefreshLayout commentRefresh;

    private static Integer PAGE_NUM = 0;
    private static Integer SIZE = 10;
    // 默认sectionId
    private Integer DEF_SECTION_ID = -1;

    /**
     * 发送评论
     * @param view
     */
    @OnClick(R.id.tv_comment_send)
    public void sendCommentOnClick(View view){
        String content = commentContentEt.getText().toString();
        // todo 读取用户id、获取课程id、章节id
        if (userDataBean != null){
            // todo 这里的sectionId未来要改为particulars当前正在播放章节的id
            requestAddComment(userDataBean.getId(),receiverCourseData.getId(),DEF_SECTION_ID,content);
        }
    }

    private CourseSectionCommentRecyclerViewAdapter mCommentRecyclerViewAdapter;
    private List<SectionCommentModel> mData;
    private NetUserModel.DataBean userDataBean = null;
    private CourseVoModel.DataBean receiverCourseData = null;

    public static CourseCommentDelegate newInstance() {
        CourseCommentDelegate courseCommentDelegate = new CourseCommentDelegate();
        return courseCommentDelegate;
    }

    public static CourseCommentDelegate newInstance(Bundle bundle) {
        CourseCommentDelegate courseCommentDelegate = new CourseCommentDelegate();
        courseCommentDelegate.setArguments(bundle);
        return courseCommentDelegate;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_course_comment;
    }

    @Override
    public void onMessageEvent(OceanMessageEvent event) {
        if (event.getMsg().equals("skipSection")){
            // todo 接收到当前正在播放的章节
            SectionChildModel childModel = (SectionChildModel) event.getData();
            DEF_SECTION_ID = childModel.getId();
        }
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        userDataBean = OmUtil.getCacheUserInfo();
        receiverCourseData = (CourseVoModel.DataBean) getArguments().getSerializable(OmConstant.BUNDLE_COURSE);
        initRefresh();
        initConmentRecycle();
    }

    public void initRefresh(){
        commentRefresh.setRefreshHeader(new ClassicsHeader(_mActivity));
        commentRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                if (receiverCourseData != null){
                    PAGE_NUM = 0;
                    SIZE = 10;
                    requestNetComments(receiverCourseData.getId(),PAGE_NUM,SIZE);
                }
                refreshLayout.finishRefresh();
            }
        });
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
        mCommentRecyclerViewAdapter.bindToRecyclerView(recyclerView);
        mCommentRecyclerViewAdapter.setEmptyView(R.layout.layout_empty);
        if (receiverCourseData != null){
            requestNetComments(receiverCourseData.getId(),PAGE_NUM,SIZE);
        }
    }

    @SuppressLint("NewApi")
    public void requestNetComments(int courseId, int page, int size){
        Logger.e("请求前查看page：" + page + "，size:" + size);
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
                        if (dataBeanList == null
                                || dataBeanList.isEmpty()){
                            // 评论列表为空
                        }else {
                           // 不支持lamba表达式 更换为普通for循环
                            for (int i = 0; i < dataBeanList.size(); i++) {
                                NetCommentModel.DataBean dataBean = dataBeanList.get(i);
                                SectionCommentModel sectionCommentModel = new SectionCommentModel();
                                sectionCommentModel.setId(dataBean.getId());
                                sectionCommentModel.setHeadImgUrl(dataBean.getUserHeadImgUrl());
                                sectionCommentModel.setUserName(dataBean.getUserName());
                                sectionCommentModel.setContent(dataBean.getCommentContent());
                                sectionCommentModel.setTime(dataBean.getCommentTime());
                                mData.add(sectionCommentModel);
                            }
                        }
                        mCommentRecyclerViewAdapter.notifyDataSetChanged();
                    }else {
//                        OmUtil.toastWarning(_mActivity,commentModel.getMsg());
                    }
                })
                .build()
                .post();
    }

    public void requestAddComment(int userId,int courseId,int sectionId,String content){
        RestClient.builder()
                .url(OmConstant.BASE_URL + OmConstant.REQUEST_URL_POST_ADD_COMMENTS)
                .params("userId",userId)
                .params("courseId",courseId)
                .params("sectionId",sectionId)
                .params("content",content)
                .loader(_mActivity)
                .success(response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        if (jsonObject.getInt("code") == OmConstant.SUCCESS_CODE){
                            requestNetComments(courseId,PAGE_NUM,SIZE);
                            OmUtil.toastSuccess(_mActivity,"评论成功");
                            commentContentEt.setText("");
                        }else {
                            // 未发表任何评论？
                            OmUtil.toastWarning(_mActivity,jsonObject.getString("msg"));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                })
                .build()
                .post();
    }

}
