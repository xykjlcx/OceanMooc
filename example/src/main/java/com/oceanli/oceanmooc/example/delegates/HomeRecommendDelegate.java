package com.oceanli.oceanmooc.example.delegates;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.manager.RequestManagerRetriever;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gongwen.marqueen.MarqueeView;
import com.gongwen.marqueen.SimpleMF;
import com.gongwen.marqueen.SimpleMarqueeView;
import com.oceanli.ocean.core.delegates.OceanDelegate;
import com.oceanli.oceanmooc.example.R;
import com.oceanli.oceanmooc.example.adapter.RecommendRecyclerViewAdapter;
import com.oceanli.oceanmooc.example.models.Model;
import com.oceanli.oceanmooc.example.other.GlideImageLoader;
import com.oceanli.oceanmooc.example.other.OceanMarqueeItemModel;
import com.oceanli.oceanmooc.example.other.OceanMarqueeViewMF;
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
        banner = rootView.findViewById(R.id.banner);
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
        initRecycler(rootView);
        initMarquee(rootView);
    }

    public void initRecycler(View rootView){
        data = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Model model = new Model();
            model.setTitle("这是标题" + i);
            model.setContent("这是内容" + i);
            data.add(model);
        }
        recyclerView = rootView.findViewById(R.id.recommend_recyc);
        recommendRecyclerViewAdapter = new RecommendRecyclerViewAdapter(R.layout.recyler_item_recommend,data);
        //创建布局管理
        LinearLayoutManager layoutManager = new LinearLayoutManager(_mActivity);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recommendRecyclerViewAdapter);
        recommendRecyclerViewAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        recommendRecyclerViewAdapter.isFirstOnly(false);
    }

    public void initMarquee(View rootView){
        modelMarqueeView = rootView.findViewById(R.id.ocean_marquee);
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


}
