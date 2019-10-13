package com.egdroid.movieapp;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.egdroid.features.topratedmovies.usecase.TopRatedMovieUseCase;
import com.egdroid.presentation.vm.TopRatedMovieViewModel;

import org.jetbrains.annotations.NotNull;

/**
 * Created at Tito on 2019-10-12
 */
public class ViewModelFactory implements ViewModelProvider.Factory {

    private TopRatedMovieUseCase topRatedMovieUseCase;

    ViewModelFactory(TopRatedMovieUseCase topRatedMovieUseCase) {
        this.topRatedMovieUseCase = topRatedMovieUseCase;
    }

    @SuppressWarnings("unchecked")
    @NotNull
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(TopRatedMovieViewModel.class)) {
            return (T) new TopRatedMovieViewModel(topRatedMovieUseCase);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }

}
