package com.egdroid.datasource.remote.movie

import android.content.Context
import com.egdroid.datasource.remote.utils.NetworkUtils
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.io.File
import java.util.concurrent.TimeUnit

object MoviesServiceFactory {

    fun provideRetrofitService(context: Context): PopularMovieService {
        return provideRetrofit(context).create(PopularMovieService::class.java)

    }

    fun provideRetrofit(context: Context): Retrofit {
        return Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/movie/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(provideOkHttpClient(context))
                .build()
    }

    private fun provideOkHttpClient(context: Context): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(provideLoggingInterceptor())
//                .addInterceptor(provideAuthInterceptor())
                .addInterceptor(provideCashingInterceptor())
                .addNetworkInterceptor(provideOfflineCashingInterceptor(context))
                .cache(provideCache(context))
                .build()
    }

    private fun provideLoggingInterceptor(): Interceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }

    private fun provideAuthInterceptor(): Interceptor {
        return Interceptor { chain ->
            val request = chain.request().newBuilder()
                    .addHeader("Application-Auth", "key")
                    .build()
            chain.proceed(request)
        }
    }

    private fun provideCache(context: Context): Cache? {
        var cache: Cache? = null
        try {
            // 10 MB for cache
            val file = File(context.cacheDir, "okhttp_cache")
            cache = Cache(file, 10 * 1024 * 1024)
        } catch (e: Exception) {
            Timber.w("Failed to create cache. Error: $e")
        }
        return cache
    }

    private fun provideCashingInterceptor(): Interceptor {
        return Interceptor { chain ->
            val response = chain.proceed(chain.request())
            val cashControl = CacheControl.Builder()
                    .maxAge(3, TimeUnit.MINUTES)
                    .build()
            response.newBuilder()
                    .header("Cache-Control", cashControl.toString())
                    .build()
        }
    }

    // even if cashing time passed and user don't have network fetch data from cache
    private fun provideOfflineCashingInterceptor(context: Context): Interceptor {
        return Interceptor { chain ->
            val request = chain.request()

            if (!NetworkUtils.isNetworkConnected(context)) {
                val cacheControl = CacheControl.Builder()
                        .maxStale(7, TimeUnit.DAYS)
                        .build()

                request.newBuilder()
                        .cacheControl(cacheControl)
                        .build()
            }
            chain.proceed(request)
        }
    }


}
