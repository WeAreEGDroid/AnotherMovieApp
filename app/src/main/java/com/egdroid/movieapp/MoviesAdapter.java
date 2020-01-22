package com.egdroid.movieapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.egdroid.models.datasourcemodel.MovieDataSource;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder>  {

    private Context context;
    private List<MovieDataSource> topRatedMovies;

    public MoviesAdapter(Context context, List<MovieDataSource> topRatedMovies) {
        this.context = context;
        this.topRatedMovies = topRatedMovies;
    }

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.movies_list_row, parent, false);
        return new ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final MovieDataSource movie = topRatedMovies.get(position);
        holder.movieNameTextView.setText(movie.getOriginalTitle());
        holder.movieOverViewTextView.setText(movie.getOverview());
        Picasso.get().load("https://image.tmdb.org/t/p/w500"+movie.getPosterPath()).into(holder.movieImageView);

    }

    @Override
    public int getItemCount() {
        return topRatedMovies.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.movieImageView)
        ImageView movieImageView;

        @BindView(R.id.movieNameTextView)
        TextView movieNameTextView;

        @BindView(R.id.movieOverViewTextView)
        TextView movieOverViewTextView;


        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
