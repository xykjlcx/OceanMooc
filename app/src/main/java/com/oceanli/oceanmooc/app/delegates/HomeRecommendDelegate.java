package com.oceanli.oceanmooc.app.delegates;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gongwen.marqueen.MarqueeView;
import com.oceanli.ocean.core.delegates.OceanDelegate;
import com.oceanli.oceanmooc.app.R;
import com.oceanli.oceanmooc.app.adapter.GridRecyclerViewAdapter;
import com.oceanli.oceanmooc.app.adapter.RecommendRecyclerViewAdapter;
import com.oceanli.oceanmooc.app.models.GridCourseModel;
import com.oceanli.oceanmooc.app.models.Model;
import com.oceanli.oceanmooc.app.other.GlideImageLoader;
import com.oceanli.oceanmooc.app.other.OceanMarqueeItemModel;
import com.oceanli.oceanmooc.app.other.OceanMarqueeViewMF;
import com.scwang.smartrefresh.header.PhoenixHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ocean on 2018/9/25
 * Author :  ocean
 * Email  :  348686686@qq.com
 */
public class HomeRecommendDelegate extends OceanDelegate {

    private Banner banner;
    private String[] mtitles = {
            "Spring Data JPA整合",
            "组件化开发",
            "大数据精选"
    };

    private RecyclerView recyclerView;
    private RecommendRecyclerViewAdapter recommendRecyclerViewAdapter;
    private List<Model> data;

    private MarqueeView<RelativeLayout,OceanMarqueeItemModel> modelMarqueeView;

    private SmartRefreshLayout smartRefreshLayout;


    private RecyclerView gridRecyclerView;
    private GridRecyclerViewAdapter gridRecyclerViewAdapter;
    private List<GridCourseModel> gridCourseModelList;

    private AppBarLayout appBarLayout;



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

    public void initView(View rootView){
        banner = rootView.findViewById(R.id.banner_recommed);
        banner.setImageLoader(new GlideImageLoader());
        List<String> list = new ArrayList<>();
        list.add("https://www.skuimg.com/u/20180926/0940537.jpg");
        list.add("https://www.skuimg.com/u/20180926/09405332.jpg");
        list.add("https://www.skuimg.com/u/20180926/09405335.jpg");
        banner.setImages(list);
        banner.setBannerTitles(Arrays.asList(mtitles));
        banner.setBannerAnimation(Transformer.FlipHorizontal);
        banner.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE);
        banner.start();
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Toast.makeText(_mActivity, "点击了：" + position, Toast.LENGTH_SHORT).show();
            }
        });

        appBarLayout = rootView.findViewById(R.id.appbar_recommend);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (smartRefreshLayout == null) return;
                smartRefreshLayout.setEnableRefresh(verticalOffset >= 0||isSlideToBottom(recyclerView) ? true : false);
            }
        });

        initRefresh(rootView);
        initRecycler(rootView);
        initGridRecycler(rootView);
        initMarquee(rootView);
    }

    protected boolean isSlideToBottom(RecyclerView recyclerView) {
        if (recyclerView == null) return false;
        if (recyclerView.computeVerticalScrollExtent() + recyclerView.computeVerticalScrollOffset() >= recyclerView.computeVerticalScrollRange())
            return true;
        return false;
    }



    public void initRecycler(View rootView){
        data = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            Model model = new Model();
            model.setTitle("这是标题" + i);
            model.setContent("这是内容" + i);
            data.add(model);
        }
        recyclerView = rootView.findViewById(R.id.recycler_recommend);
        recommendRecyclerViewAdapter = new RecommendRecyclerViewAdapter(R.layout.item_recycler_recommend,data);
        //创建布局管理
        LinearLayoutManager layoutManager = new LinearLayoutManager(_mActivity);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recommendRecyclerViewAdapter);
        recommendRecyclerViewAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
//        recommendRecyclerViewAdapter.isFirstOnly(false);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.e("ocean", "onScrolled: " + "------->isSlideToBottom:" + isSlideToBottom(recyclerView));
                if (isSlideToBottom(recyclerView)){
                    smartRefreshLayout.setEnableRefresh(true);
                }
            }
        });
    }

    public void initMarquee(View rootView){
        modelMarqueeView = rootView.findViewById(R.id.marquee_recommed_ocean);
        OceanMarqueeViewMF viewMF = new OceanMarqueeViewMF(_mActivity,getLayoutInflater());
        List<OceanMarqueeItemModel> marqueeItemModelList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            OceanMarqueeItemModel itemModel = new OceanMarqueeItemModel();
            itemModel.setTitleOne("这是跑马灯标题1->" + i);
            itemModel.setTitleTwo("这是跑马灯标题2->" + i);
            marqueeItemModelList.add(itemModel);
        }
        viewMF.setData(marqueeItemModelList);
        modelMarqueeView.setMarqueeFactory(viewMF);
        modelMarqueeView.startFlipping();
        // todo 优化跑马灯，对接服务器数据
    }

    public void initRefresh(View rootView){
        smartRefreshLayout = rootView.findViewById(R.id.recommend_refresh);
        smartRefreshLayout.setRefreshHeader(new PhoenixHeader(_mActivity));
        smartRefreshLayout.setEnableLoadMore(false);
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                data.clear();
                for (int i = 0; i < 3; i++) {
                    Model model = new Model();
                    model.setTitle("这是重新刷新的标题：" + i);
                    model.setContent("这是重新刷新的内容:" + i);
                    data.add(model);
                }
                refreshLayout.finishRefresh();
                recommendRecyclerViewAdapter.notifyDataSetChanged();
            }
        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                for (int i = 0; i < 3; i++) {
                    Model model = new Model();
                    model.setTitle("这是上拉加载的标题：" + i);
                    model.setContent("这是上拉加载的内容：" + i);
                    data.add(model);
                }
                recommendRecyclerViewAdapter.notifyDataSetChanged();
                refreshLayout.finishLoadMore();
            }
        });
    }

    public void initGridRecycler(View rootView){
        gridRecyclerView = rootView.findViewById(R.id.recycler_recommend_choiceness_grid);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(_mActivity,2);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        gridRecyclerView.setLayoutManager(gridLayoutManager);
        gridCourseModelList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            GridCourseModel model = new GridCourseModel();
            model.setId(i);
            model.setCourseName("课程" + i);
            model.setCourseDesc("这是描述" + i);
            model.setPrice("价格" + i);
            gridCourseModelList.add(model);
        }
        gridRecyclerViewAdapter = new GridRecyclerViewAdapter(R.layout.item_recycler_choiceness,gridCourseModelList);
        gridRecyclerView.setAdapter(gridRecyclerViewAdapter);
        gridRecyclerViewAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        gridRecyclerViewAdapter.isFirstOnly(false);
    }


}
