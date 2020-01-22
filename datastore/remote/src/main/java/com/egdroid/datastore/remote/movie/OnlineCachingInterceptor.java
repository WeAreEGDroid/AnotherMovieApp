package com.egdroid.datastore.remote.movie;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Response;

public class OnlineCachingInterceptor implements Interceptor {
    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        /*
         *  If there is Internet, get the cache that was stored 60 seconds ago.
         *  If the cache is older than 60 seconds, then discard it,
         *  and indicate an error in fetching the response --> will return "onFailure"
         *  The 'max-age' attribute is responsible for this behavior.
         */
        CacheControl cacheControl = new CacheControl.Builder()
                .maxAge(1, TimeUnit.SECONDS)
                .build();
        //  to implement online response cache --> retrofit caches by "response" headers like :
        //  Cache-Control:max-age=120 ,only-if-cached ,max-stale
        // y3ne lazem n3mel el request el awel w b3den lma yge el response bn2ol enna 3wzen n3mlo cache
        return response.newBuilder()
                // re-write response header if it contains "Pragma" or "Cache-Control" to force
                // use of cache ... bs hena el Headers ely rag3a asln mafehash

                // .removeHeader("Pragma")
                // .removeHeader("Cache-Control")
                .header("Cache-Control", cacheControl.toString())
                .build();
    }
}
