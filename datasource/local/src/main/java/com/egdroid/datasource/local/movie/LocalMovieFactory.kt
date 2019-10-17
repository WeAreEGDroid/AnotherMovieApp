package com.egdroid.datasource.local.movie

import android.content.Context

object LocalMovieFactory {

    fun provideDatabase(context: Context): MovieLocalDatabase {
        return MovieLocalDatabase.getDatabase(context)
    }
}
