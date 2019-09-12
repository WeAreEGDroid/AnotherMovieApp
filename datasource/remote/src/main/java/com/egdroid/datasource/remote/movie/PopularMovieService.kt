package com.egdroid.datasource.remote.movie

import com.egdroid.models.remote.movie.popularmovie.MovieResponse
import retrofit2.Call
import retrofit2.http.GET


interface PopularMovieService {

    @GET("popular?api_key=828157095a54ed8f68b8882624ed7660&language=en-US&page=1")
    fun getMovies(): Call<MovieResponse>
}