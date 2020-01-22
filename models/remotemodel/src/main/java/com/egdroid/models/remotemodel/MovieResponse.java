package com.egdroid.models.remotemodel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieResponse {

    @SerializedName("results")
    private final List<MovieRemoteData> topRatedMovies = null;
    @SerializedName("total_pages")
    private final int totalPages = 0;
    @SerializedName("total_results")
    private final int totalResults = 0;
    @SerializedName("page")
    private final int page = 0;

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
        private final String releaseDate = null;
        @SerializedName("overview")
        private final String overview = null;
        @SerializedName("vote_average")
        private final double voteAverage = 0.0;
        @SerializedName("title")
        private final String title = null;
        @SerializedName("genre_ids")
        private final List<Integer> genreIds = null;
        @SerializedName("original_title")
        private final String originalTitle = null;
        @SerializedName("original_language")
        private final String originalLanguage = null;
        @SerializedName("backdrop_path")
        private final String backdropPath = null;
        @SerializedName("adult")
        private final boolean adult = false;
        @SerializedName("id")
        private final int id = 0;
        @SerializedName("poster_path")
        private final String posterPath = null;
        @SerializedName("video")
        private final boolean video = false;
        @SerializedName("vote_count")
        private final int voteCount = 0;
        @SerializedName("popularity")
        private final double popularity = 0.0;

        public String getReleaseDate() {
            return releaseDate;
        }

        public boolean isAdult() {
            return adult;
        }

        public boolean isVideo() {
            return video;
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