package com.example.android.testing2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;
import com.github.paolorotolo.appintro.model.SliderPage;
import com.google.firebase.FirebaseApp;

public class IntroActivity extends AppIntro {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getSupportActionBar().hide();

        FirebaseApp.initializeApp(this);

        SliderPage sd = new SliderPage();
        sd.setTitle("Welcome to CinemaXX");
        sd.setDescription("The best way to book your cinema experience!");
        sd.setImageDrawable(R.drawable.tut1);
        sd.setBgColor(ContextCompat.getColor(getApplicationContext(), R.color.tut1));
        addSlide(AppIntroFragment.newInstance(sd));

        SliderPage sd1 = new SliderPage();
        sd1.setTitle("Book your seat");
        sd1.setDescription("Choose your favorite place");
        sd1.setImageDrawable(R.drawable.tut2);
        sd1.setBgColor(ContextCompat.getColor(getApplicationContext(), R.color.tut2));
        addSlide(AppIntroFragment.newInstance(sd1));

        SliderPage sd2 = new SliderPage();
        sd2.setTitle("Book your snack");
        sd2.setDescription("Choose your best friend");
        sd2.setImageDrawable(R.drawable.tut3);
        sd2.setBgColor(ContextCompat.getColor(getApplicationContext(), R.color.tut3));
        addSlide(AppIntroFragment.newInstance(sd2));

        SliderPage sd3 = new SliderPage();
        sd3.setTitle("Enjoy!");
        sd.setDescription("Experience your movie the way it meant to be");
        sd3.setImageDrawable(R.drawable.tut4);
        sd3.setBgColor(ContextCompat.getColor(getApplicationContext(), R.color.tut4));
        addSlide(AppIntroFragment.newInstance(sd3));
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
//        Toast.makeText(this, "Done pressed", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
//        Toast.makeText(this, "Skip pressed", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }
}
