package com.egdroid.mapper

object MapperFactory {

    fun providePopularMovieMapper(): MovieMapper {
        return MovieMapper()
    }
}