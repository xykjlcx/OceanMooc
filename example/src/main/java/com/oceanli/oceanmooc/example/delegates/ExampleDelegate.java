package com.oceanli.oceanmooc.example.delegates;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.oceanli.ocean.core.app.ConfigType;
import com.oceanli.ocean.core.app.Ocean;
import com.oceanli.ocean.core.delegates.OceanDelegate;
import com.oceanli.ocean.core.net.RestClient;
import com.oceanli.ocean.core.net.callback.ISuccess;
import com.oceanli.oceanmooc.example.R;

/**
 * Created by ocean on 2018/6/20
 * Author :  ocean
 * Email  :  348686686@qq.com
 */
public class ExampleDelegate extends OceanDelegate {

    private AppCompatButton test_Btn;


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
                                Toast.makeText(getContext(), "net success" + response, Toast.LENGTH_SHORT).show();
                                Log.e("ocean", "onSuccess: " + response);
                            }
                        })
                        .build()
                        .get();
            }
        });
    }


}
