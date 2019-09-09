package com.egdroid.models.local.movie

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieLocal(
        val overview: String? = null,
        val originalLanguage: String? = null,
        val originalTitle: String? = null,
        val video: Boolean? = null,
        val title: String? = null,
        val genreIds: List<Int?>? = null,
        val posterPath: String? = null,
        val backdropPath: String? = null,
        val releaseDate: String? = null,
        val popularity: Double? = null,
        val voteAverage: Double? = null,
        @PrimaryKey(autoGenerate = false)
        val id: Int,
        val adult: Boolean? = null,
        val voteCount: Int? = null
)
