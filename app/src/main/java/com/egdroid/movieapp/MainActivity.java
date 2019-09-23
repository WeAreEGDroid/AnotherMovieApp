package com.egdroid.movieapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.egdroid.features.topratedmovies.repository.TopRatedMovieRepository;
import com.egdroid.models.datasourcemodel.MovieDataSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.topRatedMoviesRecyclerView)
    RecyclerView topRatedMoviesRecyclerView;
    MoviesAdapter moviesAdapter;
    TopRatedMovieRepository topRatedMovieRepository;
    List<MovieDataSource> topRatedMoviesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeViews();
    }

    private void initializeViews() {
        ButterKnife.bind(this);

        topRatedMovieRepository = new TopRatedMovieRepository(MainActivity.this);
        topRatedMoviesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        topRatedMoviesRecyclerView.setHasFixedSize(true);
        topRatedMoviesRecyclerView.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(this), DividerItemDecoration.VERTICAL));

        topRatedMovieRepository.getTopRatedMovies().observe(this, movieDataSources -> {
            if (movieDataSources != null && !movieDataSources.isEmpty()) {
                moviesAdapter = new MoviesAdapter(MainActivity.this, movieDataSources);
                topRatedMoviesRecyclerView.setAdapter(moviesAdapter);
            }
        });
    }
}
