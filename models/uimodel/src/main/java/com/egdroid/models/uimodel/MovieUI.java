package com.egdroid.models.uimodel;

public class MovieUI {

    private int id;
    private String overview;
    private String title;
    private String originalTitle;
    private String posterPath;

    public MovieUI(int id, String title, String originalTitle, String overview, String posterPath) {
        this.id = id;
        this.overview = overview;
        this.title = title;
        this.originalTitle = originalTitle;
        this.posterPath = posterPath;
    }

}
