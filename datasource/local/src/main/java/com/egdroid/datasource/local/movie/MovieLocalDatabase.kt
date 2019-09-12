package com.egdroid.datasource.local.movie

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.egdroid.models.local.movie.MovieLocal

@Database(entities = [MovieLocal::class], version = 1)
@TypeConverters(DataConverter::class)
abstract class MovieLocalDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: MovieLocalDatabase? = null

        fun getDatabase(context: Context): MovieLocalDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        MovieLocalDatabase::class.java,
                        "movies_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
