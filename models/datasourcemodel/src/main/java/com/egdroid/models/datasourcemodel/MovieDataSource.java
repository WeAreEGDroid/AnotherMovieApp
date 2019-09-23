package com.egdroid.models.datasourcemodel;

public class MovieDataSource {

    private int id;
    private String overview;
    private String title;
    private String originalTitle;
    private String posterPath;

    public MovieDataSource( int id, String title, String originalTitle, String overview, String posterPath) {
        this.id = id;
        this.overview = overview;
        this.title = title;
        this.originalTitle = originalTitle;
        this.posterPath = posterPath;
    }

    public int getId() {
        return id;
    }

    public String getOverview() {
        return overview;
    }

    public String getTitle() {
        return title;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getPosterPath() {
        return posterPath;
    }
}
