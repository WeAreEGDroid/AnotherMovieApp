package com.egdroid.presentation.popularmoviespresentation.popularmovies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.egdroid.features.popularmovies.usecase.PopularMovieUseCase
import com.egdroid.mapper.MovieMapper
import com.egdroid.models.popularmovies.MovieUi
import io.reactivex.disposables.CompositeDisposable

class PopularMoviesViewModel(private val popularMovieUseCase: PopularMovieUseCase,
                             private val mapper: MovieMapper) : ViewModel() {

    private var disposable: CompositeDisposable = CompositeDisposable()

    fun getMovies(): LiveData<List<MovieUi>> {
        val movies = MutableLiveData<List<MovieUi>>()
        disposable.add(popularMovieUseCase.popularmovies
                .subscribe(
                        { movies.value = mapper.mapMovieEntityToMovieUi(it) },
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