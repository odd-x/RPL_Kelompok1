package com.example.android.testing2;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ToggleButton;

public class CinemaHourSelectActivity extends AppCompatActivity {

    ViewPager VPSelectCinema;
    ConstraintLayout cl;
    Button btnConfirm;
    ToggleButton btn1030, btn1430, cinepay, cash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cinema_hour_select);
        cl = (ConstraintLayout) findViewById(R.id.layout_cinemahour);

        final Intent intent = new Intent(CinemaHourSelectActivity.this, SeatSelect.class);
        final String currentmovie = getIntent().getStringExtra("CURRENTMOVIE");
        intent.putExtra("CURRENTMOVIE", currentmovie);
        intent.putExtra("CURRENTCINEMA", "CGV");

        if (currentmovie.equals("MOVIE1")) {
            cl.setBackgroundResource(R.drawable.background1);
        } else if (currentmovie.equals("MOVIE2")) {
            cl.setBackgroundResource(R.drawable.background2);
        } else if (currentmovie.equals("MOVIE3")) {
            cl.setBackgroundResource(R.drawable.background3);
        }

        VPSelectCinema = findViewById(R.id.VPSelectTH);

        vpCinemaAdapter vpCA = new vpCinemaAdapter(this);
        VPSelectCinema.setAdapter(vpCA);

        VPSelectCinema.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                if (i == 0) {
                    intent.putExtra("CURRENTCINEMA", "CGV");
                } else if (i == 1) {
                    intent.putExtra("CURRENTCINEMA", "XXI");
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        btn1030 = findViewById(R.id.btn1030);
        btn1430 = findViewById(R.id.btn1430);

        btn1030.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn1430.setChecked(false);
                intent.putExtra("CURRENTTIME", "1030");
            }
        });

        btn1430.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn1030.setChecked(false);
                intent.putExtra("CURRENTTIME", "1430");

            }
        });

        cinepay = findViewById(R.id.btnSelectCinepay);
        cash = findViewById(R.id.btnSelectCash);

        cinepay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cash.setChecked(false);
                intent.putExtra("CURRENTPAYMENT", "cinepay");
            }
        });

        cash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cinepay.setChecked(false);
                intent.putExtra("CURRENTPAYMENT", "cash");
            }
        });


        btnConfirm = findViewById(R.id.btnConfirmCineHour);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });

    }
}
