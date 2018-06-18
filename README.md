# OceanMooc


> 最好的植树时间是十年前，其次是现在


#### 项目介绍
Ocean Mooc For Android项目为作者毕设项目集的Android客户端。  

项目刚刚启动，计划于6月底完成基础库、及部分业务逻辑编写。

#### 软件架构
- `软件架构说明`
    - ocean-annotations 元注解库
    - ocean-compiler    编译时注解处理
    - ocean-core        公共核心/类库
    - ocean-mooc        业务Module(慕课)


- `开发环境`
    - JDK Version       (10.0.1)
    - Androi Studio Version (3.1.3)
    - Gradle Version    (4.4)
    - Gradle Plugin     (3.1.3)   
    - 我的About Android Studio
        - ```Android Studio 3.1.3
             Build #AI-173.4819257, built on June 5, 2018
             JRE: 1.8.0_152-release-1024-b01 x86_64
             JVM: OpenJDK 64-Bit Server VM by JetBrains s.r.o
             Mac OS X 10.13.5
          ```
    - app的build.gradle
        ```
        apply plugin: 'com.android.application'
        
        android {
            compileSdkVersion 26
            defaultConfig {
                applicationId "com.oceanli.oceanmooc.example"
                minSdkVersion 21
                targetSdkVersion 26
                versionCode 1
                versionName "1.0"
                testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"
            }
            buildTypes {
                release {
                    minifyEnabled false
                    proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
                }
            }
        }
        
        dependencies {
            implementation fileTree(include: ['*.jar'], dir: 'libs')
            api project(':ocean-mooc')
            annotationProcessor project(':ocean-compiler')
        }

        ```
    
    
- `Code实例`  

    OceanUtil为公共类库module的类
    
    ```
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

    ```
#### 安装教程

1. 项目完成后编写

#### 使用说明

1. 项目完成后编写

#### 参与贡献

1. Fork 本项目
2. 新建 Feat_xxx 分支
3. 提交代码
4. 新建 Pull Request
