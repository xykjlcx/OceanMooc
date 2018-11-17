# OceanMooc

> 最好的植树时间是十年前，其次是现在

## 项目介绍
Ocean Mooc For Android项目为作者毕设项目集的Android客户端。  

## 软件架构
- `软件架构说明`
    - ocean-annotations 元注解库
    - ocean-compiler    编译时注解处理
    - ocean-core        公共核心/类库
    - ocean-mooc        业务Module(慕课)

![image](http://oceanbucket.oss-cn-beijing.aliyuncs.com/3-4Android%E7%AB%AF%E6%9E%B6%E6%9E%84.png)

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
        
## 网络请求示例

![image](http://oceanbucket.oss-cn-beijing.aliyuncs.com/android-%E7%BD%91%E7%BB%9C%E8%AF%B7%E6%B1%82%E7%A4%BA%E4%BE%8B.png)
    
## 运行效果

![image](http://oceanbucket.oss-cn-beijing.aliyuncs.com/app1.jpg)
![image](http://oceanbucket.oss-cn-beijing.aliyuncs.com/app2.jpg)
![image](http://oceanbucket.oss-cn-beijing.aliyuncs.com/app3.jpg)
![image](http://oceanbucket.oss-cn-beijing.aliyuncs.com/app4.jpg)
![image](http://oceanbucket.oss-cn-beijing.aliyuncs.com/app5.jpg)
![image](http://oceanbucket.oss-cn-beijing.aliyuncs.com/app6.jpg)

## 参与贡献

1. Fork 本项目
2. 新建 Feat_xxx 分支
3. 提交代码
4. 新建 Pull Request

## 未来

> 整个项目，从服务端、到Web端和Android端，包括作者整理、撰写论文，差不多一共用了一个半月。所以项目有很多不成熟的地方，代码写的也不够优雅，在2.0版本中，我们会解决这些问题，并新增一些功能。

![image](http://oceanbucket.oss-cn-beijing.aliyuncs.com/%E9%A1%B9%E7%9B%AE%E8%AE%A1%E5%88%92%20%282%29.png)

### 技术交流

- QQ交流群***708261041***，加群后可以获得开发文档、毕设论文、疑问解答
- 或者添加我的个人QQ：348686686，请注明来意

### 商业授权

- 本项目为开源项目，遵循MIT协议
- 商用请联系作者授权。

### 点滴支持，将是我不解的动力
![image](https://raw.githubusercontent.com/xykjlcx/om-admin-vue/master/readme-img/pay-ali.jpg)

---

![image](https://raw.githubusercontent.com/xykjlcx/om-admin-vue/master/readme-img/pay-weixin.png)
