package com.oceanli.ocean.core.net;

import android.content.Context;
import android.util.Log;

import com.oceanli.ocean.core.net.callback.IFailure;
import com.oceanli.ocean.core.net.callback.ISuccess;

import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ocean on 2018/6/20
 * Author :  ocean
 * Email  :  348686686@qq.com
 */
public final class RestClient {

    String TAG = "ocean";

    private final static WeakHashMap<String,Object> PARAMS = RestCreator.getParams();
    private final String URL;
    private final RequestBody BODY;
    private final IFailure FAILURE;
    private final ISuccess SUCCESS;
    private final Context CONTEXT;

    RestClient(String url,
                      Map<String, Object> params,
                      RequestBody requestBody,
                      IFailure failure,
                      ISuccess success,
                      Context context) {
        this.URL = url;
        this.BODY = requestBody;
        this.FAILURE = failure;
        this.SUCCESS = success;
        this.CONTEXT = context;
    }

    // 返回建造者
    public static RestClientBuilder builder() {
        return new RestClientBuilder();
    }

    private final void request(HttpMethod method){
        ApiService apiService = RestCreator.getApiServices();
        Call<String> call = null;
        switch (method){
            case GET:
                call = apiService.get(URL,PARAMS);
                break;
            case POST:
                break;
            case POSTJSON:
                break;
            case PUT:
                break;
            case UPLOAD:
                break;
        }
        if (call != null){
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    String result = response.body().toString();
                    SUCCESS.onSuccess(result);
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    FAILURE.onFailure();
                }
            });
        }
    }

    public final void get(){
        Log.e(TAG, "get: 1");
        request(HttpMethod.GET);
        Log.e(TAG, "get: 2");

    }

    public final void post(){
        Log.e(TAG, "post: ");
    }

    public final void postJson(){
        Log.e(TAG, "postJson: ");
    }

}
