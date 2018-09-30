package com.oceanli.oceanmooc.app.business.home.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gongwen.marqueen.MarqueeView;
import com.oceanli.ocean.core.delegates.OceanDelegate;
import com.oceanli.oceanmooc.app.OmConfig;
import com.oceanli.oceanmooc.app.R;
import com.oceanli.oceanmooc.app.business.home.adapter.ChoicenessGridRecyclerViewAdapter;
import com.oceanli.oceanmooc.app.business.home.adapter.RecommendRecyclerViewAdapter;
import com.oceanli.oceanmooc.app.business.home.models.ChoicenessCourseModel;
import com.oceanli.oceanmooc.app.business.home.models.RecommendCourseModel;
import com.oceanli.oceanmooc.app.other.GlideImageLoader;
import com.oceanli.oceanmooc.app.other.models.OceanMarqueeItemModel;
import com.oceanli.oceanmooc.app.other.OceanMarqueeViewMF;
import com.oceanli.oceanmooc.app.other.utils.OmUtil;
import com.oceanli.oceanmooc.app.business.course.ui.CourseParticularsDelegate;
import com.oceanli.oceanmooc.app.business.MainDelegate;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

/**
 * Created by ocean on 2018/9/25
 * Author :  ocean
 * Email  :  348686686@qq.com
 */
public class HomeRecommendDelegate extends OceanDelegate {

    @BindView(R.id.banner_recommed)
    Banner mBanner;
    private String[] mtitles = {
            "Spring Data JPA整合",
            "组件化开发",
            "大数据精选"
    };

    @BindView(R.id.recycler_recommend)
    RecyclerView mRecommendRecycler;
    private RecommendRecyclerViewAdapter recommendRecyclerViewAdapter;
    private List<RecommendCourseModel> data;

    @BindView(R.id.marquee_recommed_ocean)
    MarqueeView<RelativeLayout,OceanMarqueeItemModel> modelMarqueeView;

    @BindView(R.id.recommend_refresh)
    SmartRefreshLayout smartRefreshLayout;


    @BindView(R.id.recycler_recommend_choiceness_grid)
    RecyclerView mChoicenessGridRecycler;
    private ChoicenessGridRecyclerViewAdapter choicenessGridRecyclerViewAdapter;
    private List<ChoicenessCourseModel> choicenessCourseModelList;

    @BindView(R.id.appbar_recommend)
    AppBarLayout appBarLayout;



    public static HomeRecommendDelegate newInstance(){
        Bundle bundle = new Bundle();
        HomeRecommendDelegate homeRecommendDelegate = new HomeRecommendDelegate();
        homeRecommendDelegate.setArguments(bundle);
        return homeRecommendDelegate;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_home_recommend;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initView(rootView);
    }

    public void initBanner(){
        mBanner.setImageLoader(new GlideImageLoader());
        List<String> list = new ArrayList<>();
        list.add("http://img.ui.cn/data/file/2/7/2/1815272.jpg" + OmConfig.IMG_COMPRESS_URL);
        list.add("http://pevcw8o7e.bkt.clouddn.com/134032655.jpg" + OmConfig.IMG_COMPRESS_URL);
        list.add("http://pevcw8o7e.bkt.clouddn.com/318887695.jpg" + OmConfig.IMG_COMPRESS_URL);
        mBanner.setImages(list);
        mBanner.setBannerTitles(Arrays.asList(mtitles));
        mBanner.setBannerAnimation(Transformer.FlipHorizontal);
        mBanner.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE);
        mBanner.start();
        mBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                ((MainDelegate)getParentFragment().getParentFragment()).startBrotherFragment(
                        OmUtil.isLoginSkip(CourseParticularsDelegate.newInstance())
                );
            }
        });
    }

    public void initView(View rootView){
        initBanner();
        initRefresh(rootView);
        initRecycler(rootView);
        initGridRecycler(rootView);
        initMarquee(rootView);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (smartRefreshLayout == null) return;
                Log.e("appbar", "onOffsetChanged: " + verticalOffset);
                // ||isSlideToBottom(recyclerView)
                smartRefreshLayout.setEnabled(verticalOffset >= 0||isSlideToBottom(mRecommendRecycler) ?  true : false);
            }
        });
    }

    protected boolean isSlideToBottom(RecyclerView recyclerView) {
        if (recyclerView == null) return false;
        if (recyclerView.computeVerticalScrollExtent() + recyclerView.computeVerticalScrollOffset() >= recyclerView.computeVerticalScrollRange())
            return true;
        return false;
    }


    public List<RecommendCourseModel> getRecommentData(){
        List<RecommendCourseModel> list = new ArrayList<>();
        final String[] courseNames = {
                "乔布斯的设计艺术",
                "Spring Boot开发入门",
                "Spark大数据处理",
                "Python机器学习"
        };
        final String desc = "本课程是年度最佳课程，采用模块化讲解，循序渐进的输出知识，为了让学生更好的接收";
        for (int i = 0; i < 30; i++) {
            RecommendCourseModel recommendCourseModel = new RecommendCourseModel();
            recommendCourseModel.setTitle(courseNames[i % 4]);
            recommendCourseModel.setContent(desc);
            list.add(recommendCourseModel);
        }
        return list;
    }


    public void initRecycler(View rootView){
        data = getRecommentData();
        recommendRecyclerViewAdapter = new RecommendRecyclerViewAdapter(R.layout.item_recycler_recommend,data);
        //创建布局管理
        LinearLayoutManager layoutManager = new LinearLayoutManager(_mActivity);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecommendRecycler.setLayoutManager(layoutManager);
        mRecommendRecycler.setAdapter(recommendRecyclerViewAdapter);
        recommendRecyclerViewAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        recommendRecyclerViewAdapter.isFirstOnly(false);
        mRecommendRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.e("appbar", "onScrolled: " + "------->isSlideToBottom:" + isSlideToBottom(recyclerView));
                if (isSlideToBottom(recyclerView)){
                    smartRefreshLayout.setEnabled(true);
                }
            }
        });
        recommendRecyclerViewAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                // 猜你喜欢课程item点击
                ((MainDelegate)getParentFragment().getParentFragment()).startBrotherFragment(
                        OmUtil.isLoginSkip(CourseParticularsDelegate.newInstance())
                );
            }
        });
    }

    public void initMarquee(View rootView){
        OceanMarqueeViewMF viewMF = new OceanMarqueeViewMF(_mActivity,getLayoutInflater());
        List<OceanMarqueeItemModel> marqueeItemModelList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            OceanMarqueeItemModel itemModel = new OceanMarqueeItemModel();
            itemModel.setTitleOne("关于常州信息职业技术学院");
            itemModel.setTitleTwo("12栋121荣获五星级宿舍");
            marqueeItemModelList.add(itemModel);
        }
        viewMF.setData(marqueeItemModelList);
        modelMarqueeView.setMarqueeFactory(viewMF);
        modelMarqueeView.startFlipping();
        // todo 优化跑马灯，对接服务器数据
    }

    public void initRefresh(View rootView){
        smartRefreshLayout.setRefreshHeader(new ClassicsHeader(_mActivity));
        smartRefreshLayout.setRefreshFooter(new ClassicsFooter(_mActivity));
        // 尽量优化 下拉触发导致的滑动冲突
//        smartRefreshLayout.setHeaderHeight(150);
//        smartRefreshLayout.setEnableAutoLoadMore(true);
//        smartRefreshLayout.setEnableOverScrollBounce(false);
//        smartRefreshLayout.setEnableLoadMoreWhenContentNotFull(false);
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                data.clear();
                for (int i = 0; i < 8; i++) {
                    RecommendCourseModel recommendCourseModel = new RecommendCourseModel();
                    recommendCourseModel.setTitle("这是重新刷新的标题：" + i);
                    recommendCourseModel.setContent("这是重新刷新的内容:" + i);
                    data.add(recommendCourseModel);
                }
                refreshLayout.finishRefresh();
                recommendRecyclerViewAdapter.notifyDataSetChanged();
            }
        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                for (int i = 0; i < 5; i++) {
                    RecommendCourseModel recommendCourseModel = new RecommendCourseModel();
                    recommendCourseModel.setTitle("这是上拉加载的标题：" + i);
                    recommendCourseModel.setContent("这是上拉加载的内容：" + i);
                    data.add(recommendCourseModel);
                }
                recommendRecyclerViewAdapter.notifyDataSetChanged();
                refreshLayout.finishLoadMore();
            }
        });
    }

    public void initGridRecycler(View rootView){
        GridLayoutManager gridLayoutManager = new GridLayoutManager(_mActivity,2);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mChoicenessGridRecycler.setLayoutManager(gridLayoutManager);
        choicenessCourseModelList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            ChoicenessCourseModel model = new ChoicenessCourseModel();
            model.setId(i);
            model.setCourseName("课程" + i);
            model.setCourseDesc("这是描述" + i);
            model.setPrice("价格" + i);
            choicenessCourseModelList.add(model);
        }
        choicenessGridRecyclerViewAdapter = new ChoicenessGridRecyclerViewAdapter(R.layout.item_recycler_choiceness, choicenessCourseModelList);
        mChoicenessGridRecycler.setAdapter(choicenessGridRecyclerViewAdapter);
        choicenessGridRecyclerViewAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        choicenessGridRecyclerViewAdapter.isFirstOnly(false);
        choicenessGridRecyclerViewAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                // 精选课程item点击
                ((MainDelegate)getParentFragment().getParentFragment()).startBrotherFragment(
                        OmUtil.isLoginSkip(CourseParticularsDelegate.newInstance())
                );
            }
        });
    }


}
