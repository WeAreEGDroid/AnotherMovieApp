package com.egdroid.datastore.local.movie;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.egdroid.models.localmodel.MovieLocal;

import java.util.Objects;

// Every time you make changes to db schema by changing fields in entity class (MovieLocal),
// version number needs to be increased
@Database(entities = {MovieLocal.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class MovieDatabase extends RoomDatabase {

    private static MovieDatabase movieDatabase;

    public abstract MovieDao movieDao();

    public static MovieDatabase getInstance(Context context){
        if(movieDatabase == null){
            movieDatabase =Room.databaseBuilder(Objects.requireNonNull(context)
                    .getApplicationContext(), MovieDatabase.class, "AnotherMovieApp-db")
                    .build();
        }
        return movieDatabase;
    }

    public static void destroyInstance() {
        movieDatabase = null;
    }

}
