package com.egdroid.movieapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.egdroid.presentation.popularmoviespresentation.PopularMoviesFragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val key1 = String(Base64.decode(getNativeKey1(), Base64.DEFAULT))
//        Timber.w(key1)


        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.container, PopularMoviesFragment())
        transaction.commit()
    }
}
