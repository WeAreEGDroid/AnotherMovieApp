package com.egdroid.mapper;


import com.egdroid.models.datasourcemodel.MovieDataSource;
import com.egdroid.models.localmodel.MovieLocal;
import com.egdroid.models.remotemodel.MovieResponse;

public class MovieMapper {

    public MovieDataSource mapToDataSource(MovieResponse movieResponse){
        return new MovieDataSource(movieResponse.id);
    }

    public MovieDataSource mapToDataSource(MovieLocal movieLocal){
        return new MovieDataSource(movieLocal.id);
    }
}
