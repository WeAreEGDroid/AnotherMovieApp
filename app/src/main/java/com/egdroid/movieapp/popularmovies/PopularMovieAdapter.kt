package com.egdroid.presentation.popularmoviespresentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.egdroid.models.popularmovies.MovieUi
import com.egdroid.movieapp.R
import kotlinx.android.synthetic.main.popular_movie_row.view.*

class PopularMovieAdapter : RecyclerView.Adapter<PopularMovieAdapter.MovieViewHolder>() {

    private val moviesList: MutableList<MovieUi> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MovieViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie: MovieUi = moviesList[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = moviesList.size

    fun addPopularMovies(movies: List<MovieUi>) {
        moviesList.addAll(movies)
        notifyDataSetChanged()
    }

    class MovieViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
            RecyclerView.ViewHolder(inflater.inflate(R.layout.popular_movie_row, parent, false)) {

        fun bind(movie: MovieUi) {
            Glide.with(itemView).load("https://image.tmdb.org/t/p/w600_and_h900_bestv2/" + movie.posterPath).into(itemView.movieImageView)
            itemView.titleTextView.text = movie.title
            itemView.overviewTextView.text = movie.overview
        }

    }
}
