package com.bwei.ssp.home_work;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by lenovo on 2018/1/3.
 */

public class LoggingInterceptor implements Interceptor{
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        HttpUrl url =request.url().newBuilder()
                .addQueryParameter("source","android")
                .build();
        return chain.proceed(request);
    }
}
