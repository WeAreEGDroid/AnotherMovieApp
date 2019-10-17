package com.egdroid.features.popularmovies.usecase

import com.egdroid.features.popularmovies.repository.PopularMovieRepo
import com.egdroid.mapper.MovieMapper

class PopularMovieUseCase(repo: PopularMovieRepo, providePopularMovieMapper: MovieMapper) {

    val popularmovies = repo.getMovies().map {
        providePopularMovieMapper.mapMovieDataSourceToMovieEntity(it)
    }

}