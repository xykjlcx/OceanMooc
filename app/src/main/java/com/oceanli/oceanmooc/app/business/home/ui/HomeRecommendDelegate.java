package com.oceanli.oceanmooc.app.business.home.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gongwen.marqueen.MarqueeView;
import com.oceanli.ocean.core.delegates.OceanDelegate;
import com.oceanli.ocean.core.net.RestClient;
import com.oceanli.ocean.core.net.callback.ISuccess;
import com.oceanli.oceanmooc.app.OmConstant;
import com.oceanli.oceanmooc.app.R;
import com.oceanli.oceanmooc.app.business.home.adapter.ChoicenessGridRecyclerViewAdapter;
import com.oceanli.oceanmooc.app.business.home.adapter.RecommendRecyclerViewAdapter;
import com.oceanli.oceanmooc.app.business.home.models.CourseVoModel;
import com.oceanli.oceanmooc.app.business.home.models.HomeNoticesVo;
import com.oceanli.oceanmooc.app.other.GlideImageLoader;
import com.oceanli.oceanmooc.app.other.models.OceanMarqueeItemModel;
import com.oceanli.oceanmooc.app.other.OceanMarqueeViewMF;
import com.oceanli.oceanmooc.app.other.utils.OmUtil;
import com.oceanli.oceanmooc.app.business.course.ui.CourseParticularsDelegate;
import com.oceanli.oceanmooc.app.business.MainDelegate;
import com.orhanobut.logger.Logger;
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
import java.util.List;

import butterknife.BindView;

/**
 * Created by ocean on 2018/9/25 Author :  ocean Email  :  348686686@qq.com
 */
public class HomeRecommendDelegate extends OceanDelegate {
    @BindView(R.id.banner_recommed)
    Banner mBanner;
    @BindView(R.id.recycler_recommend)
    RecyclerView mRecommendRecycler;
    @BindView(R.id.marquee_recommed_ocean)
    MarqueeView<RelativeLayout, OceanMarqueeItemModel> modelMarqueeView;
    @BindView(R.id.recommend_refresh)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.recycler_recommend_choiceness_grid)
    RecyclerView mChoicenessGridRecycler;
    @BindView(R.id.appbar_recommend)
    AppBarLayout appBarLayout;
    private OceanMarqueeViewMF viewMF;
    private List<String> mBannerTitles = new ArrayList<>();
    private ChoicenessGridRecyclerViewAdapter mChoicenessGridRecyclerViewAdapter;
    private List<CourseVoModel.DataBean> mChoicenessCourseModelList;
    private RecommendRecyclerViewAdapter mRecommendRecyclerViewAdapter;
    private List<CourseVoModel.DataBean> mRecommendData;
    /**
     * 当前请求页码 初始化为第0页
     */
    private static Integer PAGE_NUM = 0;
    /**
     * 每页显示item数 默认为8 得到数据时判断，当json数据size小于该值，则下次上拉记载则不执行请求
     */
    private static Integer SIZE = 8;
    /**
     * 是否到底部，默认已经到最底
     */
    private static boolean IS_BOTTOM = false;

    public static HomeRecommendDelegate newInstance() {
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

    public void initView(View rootView) {
        initRefresh();
        initBanner();
        initMarquee();
        initRecommendRecycler(rootView);
        initChoicenessGridRecycler(rootView);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (smartRefreshLayout == null)
                    return;/*                Log.e("appbar", "onOffsetChanged: " + verticalOffset); ||isSlideToBottom(recyclerView)*/
                smartRefreshLayout.setEnabled(verticalOffset >= 0 || isSlideToBottom(mRecommendRecycler) ? true : false);
            }
        });
    }

    public void reSetData() {
        IS_BOTTOM = false;
        PAGE_NUM = 0;
        SIZE = 8;
        setBannerData();
        setMarqueeData(false);
        setChoicenessData();
        setRecommendRecyclerData(PAGE_NUM, SIZE, true);
        Logger.d("数据重新加载完成");
    }

    public void initRefresh() {
        smartRefreshLayout.setRefreshHeader(new ClassicsHeader(_mActivity));
        smartRefreshLayout.setRefreshFooter(new ClassicsFooter(_mActivity));
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {/* 下拉更新*/
                reSetData();
                refreshLayout.finishRefresh();
            }
        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {/* 上拉加载*/
                if (!IS_BOTTOM) setRecommendRecyclerData(++PAGE_NUM, SIZE, false);
                else OmUtil.toastInfo(_mActivity, "已经到底了!");
                refreshLayout.finishLoadMore();
            }
        });
    }

    public void initBanner() {
        mBanner.setImageLoader(new GlideImageLoader());
        mBanner.setBannerAnimation(Transformer.FlipHorizontal);
        mBanner.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE);
        mBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Bundle bundle = new Bundle();
                bundle.putSerializable(OmConstant.BUNDLE_COURSE,mBannerCourseData.get(position));
                Logger.e("jass:" + mBannerCourseData.get(position));
                ((MainDelegate) getParentFragment().getParentFragment()).startBrotherFragment(OmUtil.isLoginSkip(OmConstant.SKIP_PARTICULARS,CourseParticularsDelegate
                        .newInstance(bundle)));
            }
        });
        setBannerData();
    }

    public void initMarquee() {
        viewMF = new OceanMarqueeViewMF(_mActivity, getLayoutInflater());
        setMarqueeData(true);
    }

    public void initRecommendRecycler(View rootView) {
        mRecommendData = new ArrayList<>();
        mRecommendRecyclerViewAdapter = new RecommendRecyclerViewAdapter(R.layout.item_recycler_recommend, mRecommendData);
        LinearLayoutManager layoutManager = new LinearLayoutManager(_mActivity);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecommendRecycler.setLayoutManager(layoutManager);
        mRecommendRecycler.setAdapter(mRecommendRecyclerViewAdapter);
        mRecommendRecyclerViewAdapter.bindToRecyclerView(mRecommendRecycler);
        mRecommendRecyclerViewAdapter.setEmptyView(R.layout.layout_empty);
        mRecommendRecyclerViewAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        mRecommendRecyclerViewAdapter.isFirstOnly(false);
        mRecommendRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);/*                Log.e("appbar", "onScrolled: " + "------->isSlideToBottom:" +
                isSlideToBottom(recyclerView));*/
                if (isSlideToBottom(recyclerView)) smartRefreshLayout.setEnabled(true);
            }
        });
        mRecommendRecyclerViewAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {/* 猜你喜欢课程item点击*/
                Bundle bundle = new Bundle();
                bundle.putSerializable(OmConstant.BUNDLE_COURSE,mRecommendData.get(position));
                ((MainDelegate) getParentFragment().getParentFragment()).startBrotherFragment(OmUtil.isLoginSkip(OmConstant.SKIP_PARTICULARS,CourseParticularsDelegate
                        .newInstance(bundle)));
            }
        });
        setRecommendRecyclerData(PAGE_NUM, SIZE, true);
    }

    public void initChoicenessGridRecycler(View rootView) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(_mActivity, 2);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mChoicenessGridRecycler.setLayoutManager(gridLayoutManager);
//        mChoicenessCourseModelList = mockChoicenessRecyclerData();    // 假数据
        mChoicenessCourseModelList = new ArrayList<>();
        mChoicenessGridRecyclerViewAdapter = new ChoicenessGridRecyclerViewAdapter(R.layout.item_recycler_choiceness, mChoicenessCourseModelList);
        mChoicenessGridRecycler.setAdapter(mChoicenessGridRecyclerViewAdapter);
        mChoicenessGridRecyclerViewAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        mChoicenessGridRecyclerViewAdapter.isFirstOnly(false);
        mChoicenessGridRecyclerViewAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {/* 精选课程item点击*/
                Bundle bundle = new Bundle();
                bundle.putSerializable(OmConstant.BUNDLE_COURSE,mChoicenessCourseModelList.get(position));
                ((MainDelegate) getParentFragment().getParentFragment()).startBrotherFragment(OmUtil.isLoginSkip(OmConstant.SKIP_PARTICULARS,CourseParticularsDelegate
                        .newInstance(bundle)));
            }
        });
        setChoicenessData();
    }


    private List<CourseVoModel.DataBean> mBannerCourseData = new ArrayList<>();

    public void setBannerData() {
        final List<String> bannerImgUrls = new ArrayList<>();
        RestClient.builder().url(OmConstant.BASE_URL + OmConstant.REQUEST_URL_GET_BANNER).success(new ISuccess() {
            @Override
            public void onSuccess(String response) {
                CourseVoModel courseVoModel = OmUtil.getGson().fromJson(response, CourseVoModel.class);
                if (courseVoModel.getCode() == OmConstant.SUCCESS_CODE) {
                    List<CourseVoModel.DataBean> dataBeanList = courseVoModel.getData();
                    if (dataBeanList != null
                            && !dataBeanList.isEmpty()){
                        bannerImgUrls.clear();
                        mBannerTitles.clear();
                        mBannerCourseData.clear();
                        for (int i = 0; i < dataBeanList.size(); i++) {
                            CourseVoModel.DataBean dataBean = dataBeanList.get(i);
                            mBannerCourseData.add(dataBean);
                            bannerImgUrls.add(dataBean.getImgUrl() + OmConstant.IMG_COMPRESS_URL);
                            mBannerTitles.add(dataBean.getCourseName());
                        }
                        mBanner.update(bannerImgUrls, mBannerTitles);
                    }else {
                        // 获取不到轮播数据
                    }
                }
            }
        }).build().get();
    }

    private final List<OceanMarqueeItemModel> marqueeItemModelList = new ArrayList<>();

    public void setMarqueeData(final boolean isFirst) {
        RestClient.builder().url(OmConstant.BASE_URL + OmConstant.REQUEST_URL_GET_NOTICES).success(new ISuccess() {
            @Override
            public void onSuccess(String response) {
                HomeNoticesVo noticesVo = OmUtil.getGson().fromJson(response, HomeNoticesVo.class);
                if (noticesVo.getCode() == OmConstant.SUCCESS_CODE) {
                    marqueeItemModelList.clear();
                    for (int i = 0; i < noticesVo.getData().size(); i++) {
                        HomeNoticesVo.DataBean dataBean = noticesVo.getData().get(i);
                        OceanMarqueeItemModel itemModel = new OceanMarqueeItemModel();
                        itemModel.setTitleOne(dataBean.getContentOne());
                        itemModel.setTitleTwo(dataBean.getContentTwo());
                        marqueeItemModelList.add(itemModel);
                    }
                    viewMF.setData(marqueeItemModelList);
                    if (!modelMarqueeView.isFlipping()) {
                        modelMarqueeView.setMarqueeFactory(viewMF);
                        modelMarqueeView.startFlipping();
                    }
                }
            }
        }).build().get();
    }

    public void setChoicenessData() {
        RestClient.builder().url(OmConstant.BASE_URL + OmConstant.REQUEST_URL_GET_CHOICENESS).success(new ISuccess() {
            @Override
            public void onSuccess(String response) {
                CourseVoModel choicenessCourseModel = OmUtil.getGson().fromJson(response, CourseVoModel.class);
                if (choicenessCourseModel.getCode() == OmConstant.SUCCESS_CODE) {
                    mChoicenessCourseModelList.clear();
                    for (int i = 0; i < choicenessCourseModel.getData().size(); i++)
                        mChoicenessCourseModelList.add(choicenessCourseModel.getData().get(i));
                    mChoicenessGridRecyclerViewAdapter.notifyDataSetChanged();
                }
            }
        }).build().get();
    }


    public void setRecommendRecyclerData(int page, int size, final boolean isFirst) {
        RestClient.builder().url(OmConstant.BASE_URL + OmConstant.REQUEST_URL_POST_RECOMMEND).loader(_mActivity).success(new ISuccess() {
            @Override
            public void onSuccess(String response) {
                CourseVoModel courseVoModel = OmUtil.getGson().fromJson(response, CourseVoModel.class);
                if (courseVoModel.getCode() == OmConstant.SUCCESS_CODE) {
                    int dataCount = 0;
                    if (courseVoModel.getData() != null) dataCount = courseVoModel.getData().size();
                    if (dataCount > 0) IS_BOTTOM = false;
                    if (isFirst) mRecommendData.clear();
                    if (!IS_BOTTOM) {
                        for (int i = 0; i < courseVoModel.getData().size(); i++) {/*                                    if (isFirst){ mRecommendData.set(i,recommendCourseModel.getData().get(i)); }else { mRecommendData.add(recommendCourseModel.getData().get(i)); }*/
                            mRecommendData.add(courseVoModel.getData().get(i));
                        }
                        mRecommendRecyclerViewAdapter.notifyDataSetChanged();
                    } else {
                    }
                    if (dataCount < SIZE) IS_BOTTOM = true;
                }
            }
        }).params("page", page).params("size", size).build().post();
    }

    protected boolean isSlideToBottom(RecyclerView recyclerView) {
        if (recyclerView == null) return false;
        if (recyclerView.computeVerticalScrollExtent() + recyclerView.computeVerticalScrollOffset() >= recyclerView.computeVerticalScrollRange())
            return true;
        return false;
    }


}
