package com.egdroid.feature.upcomingmovies.rep;

import com.egdroid.datasource.local.MovieLocalDatabase;
import com.egdroid.datasource.remote.MovieRemote;
import com.egdroid.mapper.MovieMapper;
import com.egdroid.model.datasource.MovieDataSource;
import com.egdroid.models.local.movie.MovieLocal;
import com.egdroid.models.remote.movie.MovieResponse;

public class MovieRepo {

    public MovieDataSource getMovies() {

        MovieMapper movieMapper = new MovieMapper();

        // Room integration
        MovieLocal movieLocalDatabase = new MovieLocalDatabase().getMovie();

        // Retrofit integration
        MovieResponse movieRemote = new MovieRemote().getMovie();

        if (movieLocalDatabase != null) {
            return movieMapper.mapToDataSource(movieLocalDatabase);
        } else {
            return movieMapper.mapToDataSource(movieRemote);
        }

    }

}
