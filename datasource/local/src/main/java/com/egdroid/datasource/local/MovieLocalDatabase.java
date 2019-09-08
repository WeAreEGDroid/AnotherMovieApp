package com.egdroid.datasource.local;

import com.egdroid.models.local.movie.MovieLocal;

public class MovieLocalDatabase {

    public MovieLocal getMovie() {
        return new MovieLocal(12);
    }

}
