package com.oceanli.oceanmooc.app.test.delegates;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.oceanli.ocean.core.delegates.OceanDelegate;
import com.oceanli.ocean.core.net.RestClient;
import com.oceanli.ocean.core.net.callback.IFailure;
import com.oceanli.ocean.core.net.callback.ISuccess;
import com.oceanli.oceanmooc.app.R;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;

/**
 * Created by ocean on 2018/9/24
 * Author :  ocean
 * Email  :  348686686@qq.com
 */
public class VideoDelegate extends OceanDelegate {

    @BindView(R.id.detail_player)
    StandardGSYVideoPlayer videoPlayer;

    OrientationUtils orientationUtils;

    @Override
    public Object setLayout() {
        return R.layout.delegate_video;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, final View rootView) {
        RestClient.builder()
                .url("http://192.168.43.214:8088/home/getGuessLikeCourse")
                .params("page",0)
                .params("size",3)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        try {
                            Log.e("jass", "onSuccess: " + response);
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("data");
                            JSONObject realData = jsonArray.getJSONObject(0);
                            String videoUrl = realData.getString("videoUrl");
                            String imgUrl = realData.getString("imgUrl");
                            init(rootView,videoUrl,imgUrl);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        Log.e("jass", "onFailure: " + "失败?");
                    }
                })
                .build()
                .post();
    }

    private void init(View rootView,String videoUrl,String imgUrl) {

        String source1 = videoUrl;
        videoPlayer.setUp(source1, true, "测试视频");

        //增加封面
        ImageView imageView = new ImageView(getActivity());
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(R.drawable.a1);
        videoPlayer.setThumbImageView(imageView);
        //增加title
        videoPlayer.getTitleTextView().setVisibility(View.VISIBLE);
        //设置返回键
        videoPlayer.getBackButton().setVisibility(View.VISIBLE);
        //设置旋转
//        orientationUtils = new OrientationUtils(getActivity(), videoPlayer);
        //设置全屏按键功能,这是使用的是选择屏幕，而不是全屏
        videoPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoPlayer.startWindowFullscreen(_mActivity,false,true);
            }
        });
        //是否可以滑动调整
        videoPlayer.setIsTouchWiget(true);
        videoPlayer.startPlayLogic();
    }


    @Override
    public void onPause() {
        super.onPause();
        videoPlayer.onVideoPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        videoPlayer.onVideoResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        GSYVideoManager.releaseAllVideos();
        if (orientationUtils != null)
            orientationUtils.releaseListener();
    }


}
