package com.egdroid.movieapp;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.egdroid.features.topratedmovies.repository.TopRatedMovieRepository;
import com.egdroid.models.uimodel.MovieUI;
import com.egdroid.presentation.injector.TopRatedMovieInjector;
import com.egdroid.presentation.vm.Resource;
import com.egdroid.presentation.vm.Status;
import com.egdroid.presentation.vm.TopRatedMovieViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.topRatedMoviesRecyclerView)
    RecyclerView topRatedMoviesRecyclerView;
    MoviesAdapter moviesAdapter;
    TopRatedMovieRepository topRatedMovieRepository;

    TopRatedMovieViewModel topRatedMovieViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeViews();
        initializeViewModel();
        topRatedMovieViewModel.getRefreshedTopRatedMoviesList(
                1
        ).observe(this, listResource -> {
            switch (listResource.getStatus()){
                case LOADING:
                    Toast.makeText(this, "LOADING", Toast.LENGTH_SHORT).show();
                    break;
                case SUCCESS:
                    moviesAdapter.submitList(listResource.getData());
                    break;
                case ERROR:
                    Toast.makeText(this, listResource.getError().getMessage(), Toast.LENGTH_SHORT).show();
                    break;
            }
        });
    }

    private void initializeViewModel() {
        topRatedMovieViewModel = ViewModelProviders.of(this, new ViewModelFactory(
                TopRatedMovieInjector.providesTopRatedUseCase(MainActivity.this)
        )).get(TopRatedMovieViewModel.class);
    }

    private void initializeViews() {
        ButterKnife.bind(this);

        topRatedMovieRepository = new TopRatedMovieRepository(MainActivity.this);
        topRatedMoviesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        topRatedMoviesRecyclerView.setHasFixedSize(true);
        topRatedMoviesRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        moviesAdapter = new MoviesAdapter();
        topRatedMoviesRecyclerView.setAdapter(moviesAdapter);
    }
}
