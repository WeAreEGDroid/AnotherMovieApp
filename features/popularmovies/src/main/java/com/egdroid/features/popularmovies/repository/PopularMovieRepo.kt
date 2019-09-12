package com.egdroid.features.popularmovies.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.egdroid.datasource.local.movie.MovieLocalDatabase
import com.egdroid.datasource.remote.movie.PopularMovieService
import com.egdroid.mapper.MovieMapper
import com.egdroid.model.datasource.MovieDataSource
import com.egdroid.models.remote.movie.popularmovie.MovieResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.concurrent.thread

class PopularMovieRepo(private val database: MovieLocalDatabase,
                       private val popularMovieService: PopularMovieService,
                       private val mapper: MovieMapper) {

    fun getLocalMovies(): LiveData<List<MovieDataSource>> {
        getRemoteMovies()
        return Transformations.map(database.movieDao().getAllMovies()) {
            mapper.mapMovieLocalToDataSource(it)
        }
    }

    private fun getRemoteMovies() {
        popularMovieService.getMovies().enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>,
                                    response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    thread {
                        database.movieDao().deleteAllMovies()
                        database.movieDao()
                                .insertAllMovies(mapper.mapMovieResponseToLocalMovie(response.body().results!!))
                    }
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable?) {}
        })
    }
}