package com.egdroid.features.popularmovies.usecase

import com.egdroid.features.popularmovies.repository.PopularMovieRepo

class PopularMovieUseCase(repo: PopularMovieRepo) {

    val popularmovies = repo.getMovies()

}