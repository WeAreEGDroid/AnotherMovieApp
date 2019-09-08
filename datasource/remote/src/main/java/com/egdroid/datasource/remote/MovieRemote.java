package com.egdroid.datasource.remote;

import com.egdroid.models.remote.movie.MovieResponse;

public class MovieRemote {

    public MovieResponse getMovie(){
        return new MovieResponse(2);
    }

}
