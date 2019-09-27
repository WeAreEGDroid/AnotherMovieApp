package com.egdroid.datasource.remote.movie

import com.egdroid.models.remote.movie.popularmovie.MovieResponse
import io.reactivex.Single
import retrofit2.http.GET


interface PopularMovieService {

    @GET("popular")
    fun getMovies(): Single<MovieResponse>
}