package com.egdroid.features.topratedmovies.repository;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.egdroid.datastore.remote.movie.MovieServiceFactory;
import com.egdroid.features.topratedmovies.service.TopRatedMovieService;
import com.egdroid.models.datasourcemodel.MovieDataSource;
import com.egdroid.models.mapper.MovieMapper;
import com.egdroid.models.remotemodel.MovieResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopRatedMovieRepository {

    private Context context;
    private MovieMapper movieMapper;

    public TopRatedMovieRepository(Context context) {
        this.context = context;
        movieMapper = new MovieMapper();
    }

    public LiveData<List<MovieDataSource>> getTopRatedMovies() {

        MutableLiveData<List<MovieDataSource>> movies = new MutableLiveData<>();

        TopRatedMovieService service = MovieServiceFactory.getInstance()
                .makeRetrofitService(context) // return Retrofit object
                .create(TopRatedMovieService.class); // return TopRatedMovieService object

        service.getTopRatedMovies(1).enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {

                if (response.raw().cacheResponse() != null) {
                    // true: response was served from cache
                    Log.w("response", "served from cache");
                }

                if (response.raw().networkResponse() != null) {
                    // true: response was served from network/server
                    Log.w("response", "served from network");
                }
                // You might ask, couldn't I've done this in a single if-else case?
                // No, because both methods can return true! This happens when Retrofit and OkHttp
                // made a conditional request. That means the cached version wasn't guaranteed to be valid anymore,
                // but the server responded with a 304 - Not Modified header, which indicates that
                // the cached version is still valid. So, the resource content came from cache,
                // but the headers came from the server.

                if (response.isSuccessful()) { //Successful may return body or errorBody
                    if (response.body() != null) {
                        movies.postValue(movieMapper.mapRemoteToDataSource(response.body().getTopRatedMovies()));
                    } else
                        Log.w("error_occurred!", String.valueOf(response.errorBody()));
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Toast.makeText(context, "Failed!", Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });

        return movies;
    }
}
