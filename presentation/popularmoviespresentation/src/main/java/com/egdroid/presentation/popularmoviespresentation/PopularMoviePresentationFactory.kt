package com.egdroid.presentation.popularmoviespresentation

import android.content.Context
import com.egdroid.features.popularmovies.usecase.PopularMoviesUseCaseFactory
import com.egdroid.presentation.popularmoviespresentation.popularmovies.PopularMoviesViewModel

object PopularMoviePresentationFactory {

    fun providePopularMoviesAdapter(): PopularMovieAdapter {
        return PopularMovieAdapter()
    }

    fun providePopularMoviesViewModel(context: Context): PopularMoviesViewModel {
        return PopularMoviesViewModel(PopularMoviesUseCaseFactory.providePopularMoviesUseCase(context))
    }
}