package com.oceanli.ocean.core.net;

import android.util.Log;

import com.oceanli.ocean.core.app.ConfigType;
import com.oceanli.ocean.core.app.Ocean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by ocean on 2018/6/20
 * Author :  ocean
 * Email  :  348686686@qq.com
 */
public final class RestCreator {

    /**
     * 参数容器
     */
    private static final class ParamsHolder {
        private static final HashMap<String,Object> PARAMS = new HashMap<>();
    }

    // 单例
    public static HashMap<String,Object> getParams() {
        return ParamsHolder.PARAMS;
    }

    /**
     * 构建OKHttp
     */
    private static final class OKHttpHolder {
        private static final int TIME_OUT = 10;
        private static final OkHttpClient.Builder BUILDER = new OkHttpClient.Builder();
        private static final ArrayList<Interceptor> INTERCEPTORS = Ocean.getConfiguration(ConfigType.INTERCEPTOR.name());

        private static OkHttpClient.Builder addInterceptor() {
            if (INTERCEPTORS != null && !INTERCEPTORS.isEmpty()){
                for (Interceptor interceptor : INTERCEPTORS) {
                    BUILDER.addInterceptor(interceptor);
                }
            }
            return BUILDER;
        }

        private static final OkHttpClient OK_HTTP_CLIENT = addInterceptor()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();
    }

    /**
     * 构建全局retrofit client
     */
    private static final class RetrofitHolder {
        private static final String BASE_URL = (String) Ocean.getConfiguration(ConfigType.API_HOST.name());
        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OKHttpHolder.OK_HTTP_CLIENT)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
    }

    /**
     * Service 接口
     */
    private static final class ApiServiceHolder {
        private static final ApiService API_SERVICE =
                RetrofitHolder.RETROFIT_CLIENT.create(ApiService.class);
    }

    public static ApiService getApiServices() {
        return ApiServiceHolder.API_SERVICE;
    }

}
