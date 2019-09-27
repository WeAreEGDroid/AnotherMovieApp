package com.egdroid.presentation.popularmoviespresentation.popularmovies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.egdroid.features.popularmovies.usecase.PopularMovieUseCase
import com.egdroid.model.datasource.MovieDataSource
import io.reactivex.disposables.CompositeDisposable

class PopularMoviesViewModel(private val popularMovieUseCase: PopularMovieUseCase) : ViewModel() {

    private var disposable: CompositeDisposable = CompositeDisposable()

    fun getMovies(): LiveData<List<MovieDataSource>> {
        val movies = MutableLiveData<List<MovieDataSource>>()
        disposable.add(popularMovieUseCase.popularmovies
                .subscribe(
                        { movies.value = it },
                        { movies.value = null },
                        {}
                ))

        return movies
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}