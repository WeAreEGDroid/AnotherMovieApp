package com.egdroid.models.mapper;

import com.egdroid.models.datasourcemodel.MovieDataSource;
import com.egdroid.models.entitymodel.MovieEntity;
import com.egdroid.models.localmodel.MovieLocal;
import com.egdroid.models.remotemodel.MovieResponse;
import com.egdroid.models.uimodel.MovieUI;

import java.util.ArrayList;
import java.util.List;

public final class MovieMapper {

    public List<MovieLocal> mapMovieRemoteToLocal(List<MovieResponse.MovieRemoteData> remoteMovies) {
        List<MovieLocal> movieLocalList = new ArrayList<>();
        for (MovieResponse.MovieRemoteData remoteMovie : remoteMovies) {
            MovieLocal movieLocal = new MovieLocal(remoteMovie.getId(),
                    remoteMovie.getReleaseDate(),
                    remoteMovie.getOverview(),
                    remoteMovie.getVoteAverage(),
                    remoteMovie.getTitle(),
                    remoteMovie.getGenreIds(),
                    remoteMovie.getOriginalTitle(),
                    remoteMovie.getOriginalLanguage(),
                    remoteMovie.getBackdropPath(),
                    remoteMovie.getAdult(),
                    remoteMovie.getPosterPath(),
                    remoteMovie.getVideo(),
                    remoteMovie.getVoteCount(),
                    remoteMovie.getPopularity());
            movieLocalList.add(movieLocal);
        }
        return movieLocalList;
    }

    public MovieDataSource mapMovieRemoteToDataSource(MovieResponse.MovieRemoteData movieRemoteResponse) {
        return new MovieDataSource(
                movieRemoteResponse.getId(),
                movieRemoteResponse.getTitle(),
                movieRemoteResponse.getOriginalTitle(),
                movieRemoteResponse.getOverview(),
                movieRemoteResponse.getPosterPath());
    }

    // map list of MovieRemoteData to list of MovieDataSource
    public List<MovieDataSource> mapRemoteToDataSource(List<MovieResponse.MovieRemoteData> movieRemoteResponse) {
        List<MovieDataSource> movieDataSourceList = new ArrayList<>();
        for (MovieResponse.MovieRemoteData movieRemoteData : movieRemoteResponse) {
            MovieDataSource movieDataSource = new MovieDataSource(
                    movieRemoteData.getId(),
                    movieRemoteData.getTitle(),
                    movieRemoteData.getOriginalTitle(),
                    movieRemoteData.getOverview(),
                    movieRemoteData.getPosterPath());
            movieDataSourceList.add(movieDataSource);
        }
        return movieDataSourceList;
    }

    // map list of MovieRemoteData to list of MovieDataSource
    public List<MovieDataSource> mapLocalToDataSource(List<MovieLocal> movieLocalResponse) {
        List<MovieDataSource> movieDataSourceList = new ArrayList<>();
        for (MovieLocal movieLocalData : movieLocalResponse) {
            MovieDataSource movieDataSource = new MovieDataSource(
                    movieLocalData.movieId,
                    movieLocalData.title,
                    movieLocalData.originalTitle,
                    movieLocalData.overview,
                    movieLocalData.posterPath);
            movieDataSourceList.add(movieDataSource);
        }
        return movieDataSourceList;
    }

    // dummy
    public MovieDataSource mapMovieLocalToDataSource(MovieLocal movieLocalResponse) {
        return new MovieDataSource(
                movieLocalResponse.movieId,
                movieLocalResponse.title,
                movieLocalResponse.originalTitle,
                movieLocalResponse.overview,
                movieLocalResponse.posterPath
        );
    }

    // dummy
    public MovieEntity mapMovieDataSourceToEntity(MovieDataSource movieDataSource) {
        return new MovieEntity(
                movieDataSource.getId(),
                movieDataSource.getTitle(),
                movieDataSource.getOriginalTitle(),
                movieDataSource.getOverview(),
                movieDataSource.getPosterPath()
        );
    }

    public List<MovieEntity> mapMovieDataSourceToEntity(List<MovieDataSource> movieDataSources) {
        List<MovieEntity> movieEntityList = new ArrayList<>();
        for (MovieDataSource movieDataSource : movieDataSources) {
            MovieEntity movieEntity = new MovieEntity(
                    movieDataSource.getId(),
                    movieDataSource.getTitle(),
                    movieDataSource.getOriginalTitle(),
                    movieDataSource.getOverview(),
                    movieDataSource.getPosterPath()
            );
            movieEntityList.add(movieEntity);
        }
        return movieEntityList;
    }

    // dummy
    public MovieUI mapMovieEntityToViewModel(MovieEntity movieEntity) {
        return new MovieUI(
                movieEntity.getId(),
                movieEntity.getTitle(),
                movieEntity.getOriginalTitle(),
                movieEntity.getOverview(),
                movieEntity.getPosterPath()
        );
    }

    public List<MovieUI> mapMovieEntityToMovieUi(List<MovieEntity> movieEntities) {
        List<MovieUI> movieUIList = new ArrayList<>();
        for (MovieEntity movieEntity : movieEntities) {
            MovieUI movieUI = new MovieUI(
                    movieEntity.getId(),
                    movieEntity.getTitle(),
                    movieEntity.getOriginalTitle(),
                    movieEntity.getOverview(),
                    movieEntity.getPosterPath()
            );
            movieUIList.add(movieUI);
        }
        return movieUIList;
    }
}
