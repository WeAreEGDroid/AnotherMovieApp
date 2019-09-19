package com.egdroid.movieapp

import android.app.Application
import timber.log.Timber

class EgdoidApp : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}