package com.egdroid.datastore.local.movie;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.egdroid.models.localmodel.MovieLocal;

import java.util.List;

import io.reactivex.Maybe;

// Contains the methods used for accessing the database , it can be an interface or an abstract class
@Dao
public interface MovieDao {

    @Query("SELECT * FROM movies")
    Maybe<List<MovieLocal>> getTopRatedMovies();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(MovieLocal movieLocal);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<MovieLocal> movieLocals);
}
