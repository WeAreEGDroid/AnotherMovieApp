package com.egdroid.features.topratedmovies.repository;

import android.content.Context;

import com.egdroid.datastore.local.movie.MovieDao;
import com.egdroid.datastore.local.movie.MovieDatabase;
import com.egdroid.datastore.remote.movie.MovieServiceFactory;
import com.egdroid.features.topratedmovies.service.TopRatedMovieService;
import com.egdroid.models.datasourcemodel.MovieDataSource;
import com.egdroid.models.mapper.MovieMapper;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;

public class TopRatedMovieRepository {

    private MovieMapper movieMapper;
    private MovieDao movieDao;
    private TopRatedMovieService service;

    public TopRatedMovieRepository(Context context) {
        movieMapper = new MovieMapper();
        movieDao = MovieDatabase.getInstance(context).movieDao();
        service = MovieServiceFactory.getInstance()
                .makeRetrofitService(context) // return Retrofit object
                .create(TopRatedMovieService.class); // return TopRatedMovieService object
    }


    public Flowable<List<MovieDataSource>> getRefreshedTopRatedMoviesList(
            int pageNumber
    ) {

        Maybe<List<MovieDataSource>> movieRemote = service.getTopRatedMovies(pageNumber)
                .map(movieResponse -> {
                            movieDao.insertAll(movieMapper.mapMovieRemoteToLocal(movieResponse.getTopRatedMovies()));
                            return movieMapper.mapRemoteToDataSource(movieResponse.getTopRatedMovies());
                        }
                );

        Maybe<List<MovieDataSource>> movieLocal = movieDao.getTopRatedMovies()
                .map(movieLocals ->
                        movieMapper.mapLocalToDataSource(movieLocals)
                );


        return Maybe.concat(movieLocal, movieRemote);

    }

}
