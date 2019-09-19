package com.egdroid.features.popularmovies.usecase

import android.content.Context
import com.egdroid.features.popularmovies.repository.PopularMoviesRepoFactory

object PopularMoviesUseCaseFactory {

    fun providePopularMoviesUseCase(context: Context): PopularMovieUseCase {
        return PopularMovieUseCase(PopularMoviesRepoFactory.providePopularMoviesRepo(context))
    }
}