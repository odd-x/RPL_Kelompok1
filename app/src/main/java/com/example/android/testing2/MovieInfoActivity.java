package com.example.android.testing2;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class MovieInfoActivity extends YouTubeBaseActivity {
    YouTubePlayerView mYoutubePlayerView;
    YouTubePlayer.OnInitializedListener mOnitializedListener;
    Button btnSelectSeat;
    TextView MovieName, MovieDesc;
    ConstraintLayout cl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_info);
        cl = (ConstraintLayout) findViewById(R.id.MOVIEINFOLAYOUT);
        mYoutubePlayerView = findViewById(R.id.youtubePlayer);

        final Intent intent = new Intent(MovieInfoActivity.this, CinemaHourSelectActivity.class);
        final String currentmovie = getIntent().getStringExtra("CURRENTMOVIE");
        intent.putExtra("CURRENTMOVIE", currentmovie);
        mOnitializedListener = new YouTubePlayer.OnInitializedListener() {
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youtubePlayer, boolean b) {


                if (currentmovie.equals("MOVIE1")) {
                    youtubePlayer.loadVideo("ATqx58rHsPA");
                    cl.setBackgroundResource(R.drawable.background1);
                } else if (currentmovie.equals("MOVIE2")) {
                    youtubePlayer.loadVideo("1roy4o4tqQM");
                    cl.setBackgroundResource(R.drawable.background2);


                } else if (currentmovie.equals("MOVIE3")) {
                    youtubePlayer.loadVideo("DiLbXhcLT6g");
                    cl.setBackgroundResource(R.drawable.background3);

                }

            }

            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Toast.makeText(MovieInfoActivity.this, "Fail ", Toast.LENGTH_SHORT).show();

            }
        };

        mYoutubePlayerView.initialize(YouTubeConfig.getApiKey(), mOnitializedListener);


        //

        MovieName = (TextView) findViewById(R.id.txtMovieName);
        MovieDesc = (TextView) findViewById(R.id.txtMovieDesc);

        if (currentmovie.equals("MOVIE1")) {
            MovieName.setText(R.string.nameSaekano);
            MovieDesc.setText(R.string.descSaekano);

        } else if (currentmovie.equals("MOVIE2")) {

            MovieName.setText(R.string.namePikachu);
            MovieDesc.setText(R.string.descPikachu);


        } else if (currentmovie.equals("MOVIE3")) {
            MovieName.setText(R.string.nameSeishun);
            MovieDesc.setText(R.string.descSeishun);

        }


        btnSelectSeat = findViewById(R.id.btnSelectSeat);
        btnSelectSeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//
                startActivity(intent);
            }
        });


    }
}
