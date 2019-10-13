package com.egdroid.features.topratedmovies.usecase;

import com.egdroid.features.topratedmovies.repository.TopRatedMovieRepository;
import com.egdroid.models.entitymodel.MovieEntity;
import com.egdroid.models.mapper.MovieMapper;

import java.util.List;

import io.reactivex.Flowable;

public class TopRatedMovieUseCase { //deals with TopRatedMovieRepo class

    private MovieMapper movieMapper;
    private TopRatedMovieRepository topRatedMovieRepository;

    public TopRatedMovieUseCase(
            TopRatedMovieRepository topRatedMovieRepository
    ) {
        movieMapper = new MovieMapper();
        this.topRatedMovieRepository = topRatedMovieRepository;
    }


    public Flowable<List<MovieEntity>> getRefreshedTopRatedMoviesList(
            int pageNumber
    ) {

        return topRatedMovieRepository.getRefreshedTopRatedMoviesList(
                pageNumber
        ).map(movieDataSources ->
                movieMapper.mapMovieDataSourceToEntity(movieDataSources)
        );

    }


}
