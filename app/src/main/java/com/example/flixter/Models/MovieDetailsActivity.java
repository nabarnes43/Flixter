package com.example.flixter.Models;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.flixter.R;

import org.parceler.Parcels;


public class MovieDetailsActivity extends AppCompatActivity  {

    // the movie to display
    Movie movie;

    TextView tvTitleDescription;
    ImageView imageView;
    TextView tvOverviewDescription;
    RatingBar rbVoteAverage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);


        // resolve the view objects
        tvTitleDescription = (TextView) findViewById(R.id.tvTitleDescription);
        tvOverviewDescription = (TextView) findViewById(R.id.tvOverviewDescription);
        rbVoteAverage = (RatingBar) findViewById(R.id.rbVoteAverage);
        imageView = (ImageView)findViewById(R.id.imageView);





        // unwrap the movie passed in via intent, using its simple name as a key
        movie = (Movie) Parcels.unwrap(getIntent().getParcelableExtra(Movie.class.getSimpleName()));
        Log.d("MovieDetailsActivity", String.format("Showing details for '%s'", movie.getTitle()));

        // set the title and overview
        tvTitleDescription.setText(movie.getTitle());
        tvOverviewDescription.setText(movie.getOverview());

        // vote average is 0..10, convert to 0..5 by dividing by 2
        float voteAverage = movie.getVoteAverage().floatValue();
        rbVoteAverage.setRating(voteAverage / 2.0f);

        //Set the image'

        String imageURL;
        //if phone is in landscape do backdrop image
        //else do the regular
        if (imageView.getContext().getResources().getConfiguration().orientation== Configuration.ORIENTATION_LANDSCAPE) {
            imageURL = movie.getBackdropPath();
        } else {
            imageURL = movie.getPosterPath();
        }
        Glide.with(imageView.getContext()).load(imageURL).placeholder(R.drawable.flicks_movie_placeholder).into(imageView);

    }
}