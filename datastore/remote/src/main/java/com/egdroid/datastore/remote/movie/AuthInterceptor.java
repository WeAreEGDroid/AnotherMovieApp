package com.egdroid.datastore.remote.movie;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthInterceptor implements Interceptor {
    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request request = chain.request();

        HttpUrl url = request.url().newBuilder()
                .addQueryParameter("api_key", "7750fab01ae64d5fc0888afa21fa69fd")
                .addQueryParameter("language", "en-US")
                .build();

        request = request.newBuilder().url(url).build();
        return chain.proceed(request);


        // momken n3mel add ll header zay keda
        //  Request request = chain.request().newBuilder().addHeader(" "," ").build();
        //  return chain.proceed(request);
    }



}
