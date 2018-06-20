package com.oceanli.ocean.core.net;

import android.content.Context;

import com.oceanli.ocean.core.net.callback.IFailure;
import com.oceanli.ocean.core.net.callback.ISuccess;

import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by ocean on 2018/6/20
 * Author :  ocean
 * Email  :  348686686@qq.com
 */
public class RestClientBuilder implements IRestClientBuilder<RestClientBuilder> {

    private static final WeakHashMap<String,Object> PARAMS = RestCreator.getParams();
    private String mUrl = null;
    private RequestBody mBody;
    private IFailure mIFailure = null;
    private ISuccess mISuccess = null;
    private Context mContext = null;


    @Override
    public RestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    @Override
    public RestClientBuilder params(WeakHashMap<String, Object> params) {
        PARAMS.putAll(params);
        return this;
    }

    @Override
    public RestClientBuilder params(String key, Object value) {
        PARAMS.put(key,value);
        return this;
    }

    @Override
    public RestClientBuilder requestBody(String body) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"),body);
        return this;
    }

    @Override
    public RestClientBuilder failure(IFailure iFailure) {
        this.mIFailure = iFailure;
        return this;
    }

    @Override
    public RestClientBuilder success(ISuccess iSuccess) {
        this.mISuccess = iSuccess;
        return this;
    }


    @Override
    public RestClientBuilder loader(Context context) {
        this.mContext = context;
        // todo 等待loader类编写
        return this;
    }

    @Override
    public RestClient build() {
        return new RestClient(mUrl,
                PARAMS,
                mBody,
                mIFailure,
                mISuccess,
                mContext);
    }
}
