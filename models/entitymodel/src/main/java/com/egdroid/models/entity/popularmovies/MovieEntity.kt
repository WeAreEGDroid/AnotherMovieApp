package com.egdroid.models.popularmovies

data class MovieEntity(
        val id: Int,
        val title: String? = null,
        val overview: String? = null,
        val posterPath: String? = null

)
