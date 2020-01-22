package com.egdroid.datastore.local.movie;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.egdroid.models.localmodel.MovieLocal;

import java.util.List;

// Room data access object to do operations on the movies table
// Contains the methods used for accessing the database , it can be an interface or an abstract class
@Dao
public interface MovieDao {

    /* wrapped around [LiveData] so when a new row added to the db it will be observed automatically
    at the UI to avoid pulling the db and have better lifecycle support
    * */
    @Query("SELECT * FROM movies")
    public List<MovieLocal> getTopRatedMovies(); // MovieLocal must be in type of @Entity to be used in @Dao
           //LiveData<List<MovieLocal>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(MovieLocal movieLocal);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(MovieLocal ... movieLocals);
}
