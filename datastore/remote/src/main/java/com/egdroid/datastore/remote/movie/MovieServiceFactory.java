package com.egdroid.datastore.remote.movie;

import android.content.Context;
import com.egdroid.datastore.remote.BuildConfig;

import java.io.File;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//contains the initialization of Retrofit
public class MovieServiceFactory {

    private static final MovieServiceFactory serviceInstance = new MovieServiceFactory();

    public static MovieServiceFactory getInstance() {
        return serviceInstance;
    }

    public Retrofit makeRetrofitService(Context context) {
        return new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org")
                .addConverterFactory(GsonConverterFactory.create())
                .client(makeOkHttpClient(context))
                .build();
    }

     /*OkHttp interceptors work as a chain, hence the name chain for the class you receive in the
  interceptor method. The interceptors are run in order of addition for requests and in reverse-order
  of addition for responses. For example, if you add first the logging interceptor and then the
  authentication one, you'll be running first the logging one for requests and then the authentication one.
  This means you won't see the authentication headers of the request
  (or any other authentication field for that matter) logged because they're added after you've logged the request.
   By contrast, if you add the logging interceptor at the end, you'll see everything that was added by
   the previous interceptors.
   ---> In the end, you're not running the request multiple times if you call chain.proceed(chain.request),
   you're simply passing it along the chain and waiting for the response.
 */

    private OkHttpClient makeOkHttpClient(Context context) {
        return new OkHttpClient().newBuilder()
                .addInterceptor(new AuthInterceptor())
                // Application interceptors : Are definitely invoked once, even if the HTTP response is served from the cache
                .addInterceptor(new OfflineCachingInterceptor(context))
                // Network Interceptors: Are not invoked if their response is cached which short-circuit the network
//                .addNetworkInterceptor(new OnlineCachingInterceptor())
                .addInterceptor(makeLoggingInterceptor())
                .cache(makeCacheFile(context))
                .build();

        // we add offlineCaching first then onlineCaching to get the response from online first, then offline
        // So: for the first call: if there is a network & no caching , make a connection & get the response
        // & cache it for a 60 sec by ( onLineCache ).

        // for the second call: if this call within the 60 sec (whether there is a network or not),
        // show the response from the cache without making the connection by ( onLineCache )
        // if this call after the 60 sec: check if there is a network, then make a new connection by ( onLineCache )
        // else if there isn't a network, ( onLineCache ) will fail & the condition in the ( offlineCache )
        // will be true , so get the existed response that was stored by ( onLineCache ) &
        // show it for a given period of time --> 2 days

        // for the third call: if there is a network, then make a new connection by ( onLineCache )
        // else if there isn't a network & this call within the 2 days, then show the response from the offlineCache
        // else if there isn't a network & this call after the 2 days, then the offlineCache will fail.

        // **Note** You can't cache POST requests with OkHttp’s cache
    }

    private Interceptor makeLoggingInterceptor() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        if (!BuildConfig.DEBUG)
            logging.level(HttpLoggingInterceptor.Level.NONE); // to avoid log responses in the release version
        else
            logging.level(HttpLoggingInterceptor.Level.BODY);
        return logging;
    }

    private Cache makeCacheFile(Context context) {
        int cacheSize = 10 * 1024 * 1024; // 10 MB
        File file = new File(context.getCacheDir(), "responses");
        return new Cache(file, cacheSize);
    }

}