package com.egdroid.models.uimodel;

public class MovieUI {

    private final int id;
    private final String overview;
    private final String title;
    private final String originalTitle;
    private final String posterPath;

    public MovieUI(int id, String title, String originalTitle, String overview, String posterPath) {
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
