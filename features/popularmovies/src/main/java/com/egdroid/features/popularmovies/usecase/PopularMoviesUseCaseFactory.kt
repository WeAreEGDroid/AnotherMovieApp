package com.egdroid.features.popularmovies.usecase

import android.content.Context
import com.egdroid.features.popularmovies.repository.PopularMoviesRepoFactory
import com.egdroid.mapper.MapperFactory

object PopularMoviesUseCaseFactory {

    fun providePopularMoviesUseCase(context: Context): PopularMovieUseCase {
        return PopularMovieUseCase(PopularMoviesRepoFactory.providePopularMoviesRepo(context),
                MapperFactory.providePopularMovieMapper())
    }
}