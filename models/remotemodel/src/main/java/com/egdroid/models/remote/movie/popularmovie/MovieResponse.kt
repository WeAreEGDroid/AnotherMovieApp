package com.egdroid.models.remote.movie.popularmovie

data class MovieResponse(
        val page: Int? = null,
        val totalPages: Int? = null,
        val results: List<MovieRemote>? = null,
        val totalResults: Int? = null
)
