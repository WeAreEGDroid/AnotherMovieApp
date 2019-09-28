package com.egdroid.features.topratedmovies.service;
import com.egdroid.models.remotemodel.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

//contains retrofit functions which is responsible for retrieving the apis
public interface TopRatedMovieService {

    @GET("/3/movie/top_rated")
    Call<MovieResponse> getTopRatedMovies(@Query("page")int pageNumber);
}
