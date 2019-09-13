package com.egdroid.DataSource.local;

import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieLocal { // from Room

    @SerializedName("release_date")
    public String releaseDate;

    @SerializedName("overview")
    public String overview;

    @SerializedName("vote_average")
    public double voteAverage;

    @SerializedName("title")
    public String title;

    @SerializedName("genre_ids")
    public List<Integer> genreIds;

    @SerializedName("original_title")
    public String originalTitle;

    @SerializedName("original_language")
    public String originalLanguage;

    @SerializedName("backdrop_path")
    public String backdropPath;

    @SerializedName("adult")
    public boolean adult;

    @PrimaryKey
    @SerializedName("id")
    public int id;

    @SerializedName("poster_path")
    public String posterPath;

    @SerializedName("video")
    public boolean video;

    @SerializedName("vote_count")
    public int voteCount;

    @SerializedName("popularity")
    public double popularity;
}
