package com.oceanli.oceanmooc.example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.oceanli.ocean.core.OceanUtils;

import java.util.Random;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("ocean", "onCreate: " + "项目初始化完成" + "打印:***" + OceanUtils.AUTHOR + "***");
        Toast.makeText(this, "hello ocean mooc", Toast.LENGTH_SHORT).show();
    }


    /**
     *
     * @param name
     * @param age
     * @return
     * @throws RuntimeException
     */
    public int save(String name,int age) throws RuntimeException{
        Log.d("ocean", "save: " + "姓名：" + name + "，年龄:" + age);
        return new Random().nextInt(age);
    }

}
