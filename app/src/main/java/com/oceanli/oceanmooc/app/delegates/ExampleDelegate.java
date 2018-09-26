package com.oceanli.oceanmooc.app.delegates;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.oceanli.ocean.core.delegates.OceanDelegate;
import com.oceanli.ocean.core.net.callback.IFailure;
import com.oceanli.ocean.core.ui.launch.ISkipListener;
import com.oceanli.ocean.core.ui.launch.OceanLaunchView;
import com.oceanli.ocean.core.net.RestClient;
import com.oceanli.ocean.core.net.callback.ISuccess;
import com.oceanli.oceanmooc.app.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ocean on 2018/6/20
 * Author :  ocean
 * Email  :  348686686@qq.com
 */
public class ExampleDelegate extends OceanDelegate {

    private AppCompatButton test_Btn;
    private OceanLaunchView oceanLaunchView;


    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        test_Btn = rootView.findViewById(R.id.test_Btn);
        test_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RestClient.builder()
                        .url("")
                        .success(new ISuccess() {
                            @Override
                            public void onSuccess(String response) {
//                                Toast.makeText(getContext(), "net success" + response, Toast.LENGTH_SHORT).show();
                                Log.e("ocean", "onSuccess: " + response);
                            }
                        })
                        .failure(new IFailure() {
                            @Override
                            public void onFailure() {

                            }
                        })
                        .loader(getActivity())
                        .build()
                        .get();
            }
        });
        oceanLaunchView = rootView.findViewById(R.id.test_launch);
        int[] resources = {R.drawable.shadow_bottom,R.drawable.shadow_right};
        oceanLaunchView.setImgResource(resources);
        oceanLaunchView.setSkipListener(new ISkipListener() {
            @Override
            public void onClick() {
                Toast.makeText(getActivity(), "被点击跳过", Toast.LENGTH_SHORT).show();
            }
        });
        oceanLaunchView.launch();
    }


}
