package com.example.android.testing2;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MovieSelectActivity extends AppCompatActivity {
    ViewPager viewPagerSelectMovie;
    Button btnMovieInfoActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_select);

        viewPagerSelectMovie = (ViewPager) findViewById(R.id.ViewPagerSelectMovie);
        btnMovieInfoActivity = (Button) findViewById(R.id.btnMovieInfo);

        VPSelectMovie VPMovie = new VPSelectMovie(this);
        viewPagerSelectMovie.setAdapter(VPMovie);

        final Intent intent = new Intent(MovieSelectActivity.this, MovieInfoActivity.class);
        intent.putExtra("CURRENTMOVIE", "MOVIE1");

        viewPagerSelectMovie.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {

                if (i == 0) {
                    intent.putExtra("CURRENTMOVIE", "MOVIE1");
                } else if (i == 1) {
                    intent.putExtra("CURRENTMOVIE", "MOVIE2");
                } else if (i == 2){
                    intent.putExtra("CURRENTMOVIE", "MOVIE3");
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });


        btnMovieInfoActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(intent);
            }
        });
    }


}
