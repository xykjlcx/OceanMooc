package com.oceanli.oceanmooc.example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.oceanli.ocean.core.OceanUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("ocean", "onCreate: " + "项目初始化完成" + "打印:***" + OceanUtils.AUTHOR + "***");
        Toast.makeText(this, "hello world for first module application!", Toast.LENGTH_SHORT).show();
    }
}
