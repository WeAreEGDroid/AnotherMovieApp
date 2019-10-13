package com.egdroid.presentation.injector;

import android.content.Context;

import com.egdroid.features.topratedmovies.repository.TopRatedMovieRepository;
import com.egdroid.features.topratedmovies.usecase.TopRatedMovieUseCase;

/**
 * Created at Tito on 2019-10-12
 */
public class TopRatedMovieInjector {

    public static TopRatedMovieUseCase providesTopRatedUseCase(
            Context context
    ) {
        return new TopRatedMovieUseCase(providesTopRatedMovieRepository(
                context));
    }

    private static TopRatedMovieRepository providesTopRatedMovieRepository(
            Context context
    ) {
        return new TopRatedMovieRepository(
                context
        );
    }

}
