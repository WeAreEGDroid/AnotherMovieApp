package com.egdroid.datasource.local.movie

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.egdroid.models.local.movie.MovieLocal
import io.reactivex.Completable
import io.reactivex.Maybe

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies")
    fun getAllMovies(): Maybe<List<MovieLocal>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllMovies(movies: List<MovieLocal>)

    @Query("DELETE FROM movies")
    fun deleteAllMovies(): Completable
}