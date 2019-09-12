package com.egdroid.presentation.popularmoviespresentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.egdroid.features.popularmovies.usecase.PopularMoviesUseCaseFactory
import kotlinx.android.synthetic.main.fragment_popular_movies.view.*

/**
 * A simple [Fragment] subclass.
 */
class PopularMoviesFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_popular_movies, container, false)

        val popularMovieUseCase = PopularMoviesUseCaseFactory.providePopularMoviesUseCase(activity!!)
        val popularMovieAdapter = PopularMoviePresentationFactory.providePopularMoviesAdapter()
        view.popularMoviesRecyclerView.setLayoutManager(LinearLayoutManager(activity))
        view.popularMoviesRecyclerView.adapter = popularMovieAdapter

        popularMovieUseCase.popularmovies.observe(this, Observer {
            if (it != null) {
                popularMovieAdapter.addPopularMovies(it)
            }
        })
        return view
    }
}