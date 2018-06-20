package com.oceanli.ocean.core.net;

import android.content.Context;

import com.oceanli.ocean.core.net.callback.IFailure;
import com.oceanli.ocean.core.net.callback.ISuccess;

import java.util.Map;
import java.util.WeakHashMap;

/**
 * Created by ocean on 2018/6/20
 * Author :  ocean
 * Email  :  348686686@qq.com
 */
public interface IRestClientBuilder<T> {

    T url(String url);

    T params(WeakHashMap<String,Object> params);

    T params(String key,Object value);

    T requestBody(String body);

    T failure(IFailure iFailure);

    T success(ISuccess iSuccess);

    T loader(Context context);

    RestClient build();

}