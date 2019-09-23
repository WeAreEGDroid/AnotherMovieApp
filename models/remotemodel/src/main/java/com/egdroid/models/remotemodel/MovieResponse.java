package com.egdroid.models.remotemodel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieResponse {

    @SerializedName("results")
    private List<MovieRemoteData> topRatedMovies;
    @SerializedName("total_pages")
    private int totalPages;
    @SerializedName("total_results")
    private int totalResults;
    @SerializedName("page")
    private int page;

    public List<MovieRemoteData> getTopRatedMovies() {
        return topRatedMovies;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public int getPage() {
        return page;
    }

    public class MovieRemoteData {

        @SerializedName("release_date")
        private String releaseDate;
        @SerializedName("overview")
        private String overview;
        @SerializedName("vote_average")
        private double voteAverage;
        @SerializedName("title")
        private String title;
        @SerializedName("genre_ids")
        private List<Integer> genreIds;
        @SerializedName("original_title")
        private String originalTitle;
        @SerializedName("original_language")
        private String originalLanguage;
        @SerializedName("backdrop_path")
        private String backdropPath;
        @SerializedName("adult")
        private boolean adult;
        @SerializedName("id")
        private int id;
        @SerializedName("poster_path")
        private String posterPath;
        @SerializedName("video")
        private boolean video;
        @SerializedName("vote_count")
        private int voteCount;
        @SerializedName("popularity")
        private double popularity;

        public String getReleaseDate() {
            return releaseDate;
        }

        public String getOverview() {
            return overview;
        }

        public double getVoteAverage() {
            return voteAverage;
        }

        public String getTitle() {
            return title;
        }

        public List<Integer> getGenreIds() {
            return genreIds;
        }

        public String getOriginalTitle() {
            return originalTitle;
        }

        public String getOriginalLanguage() {
            return originalLanguage;
        }

        public String getBackdropPath() {
            return backdropPath;
        }

        public boolean getAdult() {
            return adult;
        }

        public int getId() {
            return id;
        }

        public String getPosterPath() {
            return posterPath;
        }

        public boolean getVideo() {
            return video;
        }

        public int getVoteCount() {
            return voteCount;
        }

        public double getPopularity() {
            return popularity;
        }

    }
}