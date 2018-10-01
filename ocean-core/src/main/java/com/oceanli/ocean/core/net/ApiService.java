package com.oceanli.ocean.core.net;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by ocean on 2018/6/20 Author :  ocean Email  :  348686686@qq.com
 */
public interface ApiService {
    @GET
    Call<String> get(@Url String url, @QueryMap HashMap<String, Object> params);

    @POST
    @FormUrlEncoded
    Call<String> post(@Url String url, @FieldMap HashMap<String, Object> params);

    @POST
    Call<String> postJson(@Url String url, @Body RequestBody requestBody);
}
