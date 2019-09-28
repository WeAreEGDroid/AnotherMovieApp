package com.egdroid.features.topratedmovies.repository;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.egdroid.datastore.local.movie.MovieDao;
import com.egdroid.datastore.local.movie.MovieDatabase;
import com.egdroid.datastore.remote.movie.MovieServiceFactory;
import com.egdroid.features.topratedmovies.service.TopRatedMovieService;
import com.egdroid.models.datasourcemodel.MovieDataSource;
import com.egdroid.models.localmodel.MovieLocal;
import com.egdroid.models.mapper.MovieMapper;
import com.egdroid.models.remotemodel.MovieResponse;

import java.util.List;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopRatedMovieRepository {

    private Context context;
    private MovieMapper movieMapper;
    private MovieDao movieDao;
    private TopRatedMovieService service;

    public TopRatedMovieRepository(Context context) {
        this.context = context;
        movieMapper = new MovieMapper();
        movieDao = MovieDatabase.getInstance(context).movieDao();
        service = MovieServiceFactory.getInstance()
                .makeRetrofitService(context) // return Retrofit object
                .create(TopRatedMovieService.class); // return TopRatedMovieService object
    }


    public LiveData<List<MovieDataSource>> getRefreshedTopRatedMoviesList() {

        refreshRemoteTopRatedMovies();
        return getLocalTopRatedMovies();


        // get the data from DB & send it
        // get the data from remote
        // replace the new remote data in the local
        // send the diff if exist

        //   1-      LiveData<List<MovieDataSource>> localMovies = getLocalTopRatedMovies();
        //   2-     LiveData<List<MovieDataSource>> remoteMovies = refreshRemoteTopRatedMovies();

        // if(remoteMovies.getValue().size()>localMovies.getValue().size())
        //  update localMovies then show it;
        // else
        // show the existed localMovies;
    }

    private LiveData<List<MovieDataSource>> refreshRemoteTopRatedMovies() {

        MutableLiveData<List<MovieDataSource>> remoteMovies = new MutableLiveData<>();

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
                        List<MovieResponse.MovieRemoteData> moviesList = response.body().getTopRatedMovies();
                        remoteMovies.postValue(movieMapper.mapRemoteToDataSource(moviesList));

                       /* Instead of returning data from web service call, we are returning data
                        from db and making change to db via web service call in background.
                        Whenever data in db will change, observers will be notified to make changes in UI */

                        //update the db once fresh data is available
                        Executors.newSingleThreadExecutor().execute(new Runnable() {
                            @Override
                            //Cannot access database on the main thread since it may potentially lock the UI
                            // for a long period of time, so we need to run the db access operations in new thread (Executor).
                            public void run() {

                                for (MovieResponse.MovieRemoteData remoteMovie : moviesList) {
                                    MovieLocal localMovie = getMovieData(remoteMovie);
                                    movieDao.insert(localMovie);
                                }

                            }
                        });

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

        return remoteMovies;
    }


    private LiveData<List<MovieDataSource>> getLocalTopRatedMovies() {
        MutableLiveData<List<MovieDataSource>> localMovies = new MutableLiveData<>();
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            //Cannot access database on the main thread since it may potentially lock the UI
            // for a long period of time, so we need to run the db access operations in new thread (Executor).
            public void run() {
                localMovies.postValue(movieMapper.mapLocalToDataSource(movieDao.getTopRatedMovies()));
            }
        });
        return localMovies;
    }


    private MovieLocal getMovieData(MovieResponse.MovieRemoteData remoteMovie) {
        return new MovieLocal(remoteMovie.getId(),
                remoteMovie.getReleaseDate(),
                remoteMovie.getOverview(),
                remoteMovie.getVoteAverage(),
                remoteMovie.getTitle(),
                remoteMovie.getGenreIds(),
                remoteMovie.getOriginalTitle(),
                remoteMovie.getOriginalLanguage(),
                remoteMovie.getBackdropPath(),
                remoteMovie.getAdult(),
                remoteMovie.getPosterPath(),
                remoteMovie.getVideo(),
                remoteMovie.getVoteCount(),
                remoteMovie.getPopularity());
    }

}
