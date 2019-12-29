package com.egdroid.models.localmodel;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity(tableName = "movies")
public class MovieLocal {

    @PrimaryKey
    @ColumnInfo(name = "movie_id")
    public int movieId;

    //To store the fields in table, room must have access to that field.
    // You can either make the field public or provide public getter and setter for the field
    @ColumnInfo(name = "release_date")
    public String releaseDate;

    @ColumnInfo(name = "overview")
    public String overview;

    @ColumnInfo(name = "vote_average")
    public double voteAverage;

    public String title;

    //the entity class can have only primitive java data types which will be saved in respective columns by Room DB
    //when we need to store object as is in one column, we use "Type Converters"
    public List<Integer> genreIds;

    @ColumnInfo(name = "original_title")
    public String originalTitle;

    @ColumnInfo(name = "original_language")
    public String originalLanguage;

    @ColumnInfo(name = "backdrop_path")
    public String backdropPath;

    public boolean adult;

    @ColumnInfo(name = "poster_path")
    public String posterPath;

    public boolean video;

    @ColumnInfo(name = "vote_count")
    public int voteCount;

    public double popularity;

    public MovieLocal(int movieId, String releaseDate, String overview, double voteAverage, String title, List<Integer> genreIds, String originalTitle, String originalLanguage, String backdropPath, boolean adult, String posterPath, boolean video, int voteCount, double popularity) {
        this.movieId = movieId;
        this.releaseDate = releaseDate;
        this.overview = overview;
        this.voteAverage = voteAverage;
        this.title = title;
        this.genreIds = genreIds;
        this.originalTitle = originalTitle;
        this.originalLanguage = originalLanguage;
        this.backdropPath = backdropPath;
        this.adult = adult;
        this.posterPath = posterPath;
        this.video = video;
        this.voteCount = voteCount;
        this.popularity = popularity;
    }

}

