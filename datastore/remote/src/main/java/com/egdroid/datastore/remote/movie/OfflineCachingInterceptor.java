package com.egdroid.datastore.remote.movie;

import android.content.Context;

import com.egdroid.base.Utils;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class OfflineCachingInterceptor implements Interceptor {

    private Context context;

    public OfflineCachingInterceptor(Context context) {
        this.context = context;
    }

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request request = chain.request();
        /*
         *  If there is no Internet, get the cache that was stored 7 days ago.
         *  If the cache is older than 7 days, then discard it,
         *  and indicate an error in fetching the response --> will return "onFailure"
         *  The 'max-stale' attribute is responsible for this behavior.
         *
         *  The 'only-if-cached' attribute indicates to not retrieve new data; fetch the cache only instead.
         *
         *  maxStale indicates that even if the “freshness” time given by maxAge expires,
         *  the cache will still use this response for a given period of time.
         */
        if (!Utils.isNetworkAvailable(context)) {
// In contrast to the onLineCachingInterceptor, we’ll modify the "Request" object.
// hena mn2darsh n3tamed 3la el "response" w nAdd feh el "header" 3lshan msh hyb2a fe "response" asln,
// since there isn't network to call the webservice fa bn3mel Add ll header fe el "Request" since
// en hwa keda keda hytb3et bn2ol 7afez 3la el data ely m3ak for 2 days
            CacheControl cacheControl = new CacheControl.Builder()
                    .maxStale(2, TimeUnit.DAYS)
                    .build();

            request = request.newBuilder()
                    .header("Cache-Control", cacheControl.toString()) // == .cacheControl(cacheControl)
                    .build();
        }
        return chain.proceed(request);
    }
}
