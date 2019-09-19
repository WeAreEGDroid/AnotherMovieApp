package com.egdroid.mapper

import com.egdroid.model.datasource.MovieDataSource
import com.egdroid.models.local.movie.MovieLocal
import com.egdroid.models.remote.movie.popularmovie.MovieRemote

class MovieMapper {

    fun mapDataSourceToMovieLocal(movieDataSource: MovieDataSource): MovieLocal {
        return MovieLocal(
                movieDataSource.id!!,
                movieDataSource.title,
                movieDataSource.overview,
                movieDataSource.posterPath
        )
    }

    fun mapMovieLocalToDataSource(movieLocal: List<MovieLocal>): List<MovieDataSource> {
        val moviesDataSource = mutableListOf<MovieDataSource>()
        for (movie in movieLocal) {
            moviesDataSource.add(MovieDataSource(
                    movie.id,
                    movie.title,
                    movie.overview,
                    movie.posterPath
            ))
        }
        return moviesDataSource
    }

    fun mapMovieResponseToDataSource(moviesResponse: List<MovieRemote>): List<MovieDataSource> {
        val moviesDataSource = mutableListOf<MovieDataSource>()
        for (movie in moviesResponse) {
            moviesDataSource.add(MovieDataSource(
                    movie.id,
                    movie.title,
                    movie.overview,
                    movie.poster_path
            ))
        }
        return moviesDataSource
    }

    fun mapMovieResponseToLocalMovie(moviesResponse: List<MovieRemote>): List<MovieLocal> {
        val moviesDataSource = mutableListOf<MovieLocal>()
        for (movie in moviesResponse) {
            moviesDataSource.add(MovieLocal(
                    movie.id,
                    movie.title,
                    movie.overview,
                    movie.poster_path
            ))
        }
        return moviesDataSource
    }

    fun mapMovieDataSourceToMovieLocal(movieDataSource: MovieDataSource): MovieLocal {
        return MovieLocal(
                movieDataSource.id!!,
                movieDataSource.title,
                movieDataSource.overview,
                movieDataSource.posterPath
        )
    }
}
