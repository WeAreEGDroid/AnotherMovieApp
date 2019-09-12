package com.egdroid.models.local.movie

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieLocal(
        @PrimaryKey(autoGenerate = false)
        val id: Int,
        val title: String? = null,
        val overview: String? = null,
        val posterPath: String? = null

)
