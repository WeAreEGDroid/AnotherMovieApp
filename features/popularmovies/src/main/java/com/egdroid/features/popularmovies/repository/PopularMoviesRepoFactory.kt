package com.egdroid.features.popularmovies.repository

import android.content.Context
import com.egdroid.datasource.local.movie.LocalMovieFactory.provideDatabase
import com.egdroid.datasource.remote.movie.MoviesServiceFactory.provideRetrofitService
import com.egdroid.mapper.MapperFactory.providePopularMovieMapper

object PopularMoviesRepoFactory {

    fun providePopularMoviesRepo(context: Context): PopularMovieRepo {
        return PopularMovieRepo(provideDatabase(context), provideRetrofitService(context), providePopularMovieMapper())
    }
}