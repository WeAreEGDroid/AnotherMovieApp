package com.egdroid.mapper;

import com.egdroid.model.datasource.MovieDataSource;
import com.egdroid.models.local.movie.MovieLocal;
import com.egdroid.models.remote.movie.MovieResponse;

public class MovieMapper {

    public MovieDataSource mapToDataSource(MovieResponse movieResponse){
        return new MovieDataSource(movieResponse.id);
    }

    public MovieDataSource mapToDataSource(MovieLocal movieLocal){
        return new MovieDataSource(movieLocal.id);
    }

}
