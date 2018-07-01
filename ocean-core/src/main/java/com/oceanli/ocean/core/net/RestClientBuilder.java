package com.oceanli.ocean.core.net;

import android.content.Context;

import com.oceanli.ocean.core.app.Ocean;
import com.oceanli.ocean.core.net.callback.IFailure;
import com.oceanli.ocean.core.net.callback.IRequest;
import com.oceanli.ocean.core.net.callback.ISuccess;
import com.oceanli.ocean.core.ui.LoaderStyle;
import com.oceanli.ocean.core.ui.OceanLoader;

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
    private IRequest mIRequest = null;
    private LoaderStyle mLoadStyle = null;
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
    public RestClientBuilder request(IRequest iRequest) {
        this.mIRequest = iRequest;
        return this;
    }

    @Override
    public RestClientBuilder loader(Context context, LoaderStyle style) {
        this.mContext = context;
        this.mLoadStyle = style;
        return this;
    }

    public RestClientBuilder loader(Context context) {
        this.mContext = context;
        this.mLoadStyle = OceanLoader.DEFAULT_LOADING_STYLE;
        return this;
    }

    @Override
    public RestClient build() {
        return new RestClient(mUrl,
                PARAMS,
                mBody,
                mIFailure,
                mISuccess,
                mIRequest,
                mLoadStyle,
                mContext);
    }
}
