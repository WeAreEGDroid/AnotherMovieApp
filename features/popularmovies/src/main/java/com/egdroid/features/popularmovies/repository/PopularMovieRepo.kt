package com.egdroid.features.popularmovies.repository

import com.egdroid.datasource.local.movie.MovieLocalDatabase
import com.egdroid.datasource.remote.movie.PopularMovieService
import com.egdroid.mapper.MovieMapper
import com.egdroid.model.datasource.MovieDataSource
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class PopularMovieRepo(private val database: MovieLocalDatabase,
                       private val popularMovieService: PopularMovieService,
                       private val mapper: MovieMapper) {

    fun getMovies(): Flowable<List<MovieDataSource>> {
        return Flowable.merge(getLocalMovies(), getRemoteMovies())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    private fun getLocalMovies(): Flowable<List<MovieDataSource>> {
        return database.movieDao().getAllMovies().map {
            mapper.mapMovieLocalToDataSource(it)
        }.toFlowable()
    }

    private fun getRemoteMovies(): Flowable<List<MovieDataSource>> {
        return popularMovieService.getMovies().doOnSuccess {
            database.movieDao().deleteAllMovies()
                    .doOnComplete {
                        database.movieDao()
                                .insertAllMovies(mapper.mapMovieResponseToLocalMovie(it.results!!))
                    }
        }.map {
            mapper.mapMovieResponseToDataSource(it.results!!)
        }.toFlowable()
    }
}