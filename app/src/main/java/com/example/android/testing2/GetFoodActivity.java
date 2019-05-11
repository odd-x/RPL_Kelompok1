package com.example.android.testing2;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.Toast;

import java.util.ArrayList;

public class GetFoodActivity extends AppCompatActivity {
    EditText col, pop;
    ConstraintLayout constraintLayout;
    ImageView currcin;
    Button next;

    String CURRENTPAYMENT;
    String CURRENTMOVIE;
    ArrayList<String> CURRENTSEAT;
    String CURRENTTIME;
    String CURRENTCINEMA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_food);
        final Intent intent = new Intent(GetFoodActivity.this, ReceiptActivity.class);

        col = (EditText) findViewById(R.id.quantCol);
        pop = (EditText) findViewById(R.id.quantPop);

        CURRENTPAYMENT = getIntent().getStringExtra("CURRENTPAYMENT");
        CURRENTMOVIE = getIntent().getStringExtra("CURRENTMOVIE");
        CURRENTSEAT = getIntent().getStringArrayListExtra("CURRENTSEAT");
        CURRENTTIME = getIntent().getStringExtra("CURRENTTIME");
        CURRENTCINEMA = getIntent().getStringExtra("CURRENTCINEMA");

        intent.putExtra("CURRENTPAYMENT", CURRENTPAYMENT);
        intent.putExtra("CURRENTMOVIE", CURRENTMOVIE);
        intent.putExtra("CURRENTSEAT", CURRENTSEAT);
        intent.putExtra("CURRENTTIME", CURRENTTIME);
        intent.putExtra("CURRENTCINEMA", CURRENTCINEMA);

        currcin = findViewById(R.id.imgCurrCinemaFood);




        if (CURRENTCINEMA.equals("XXI")) {
            currcin.setImageResource(R.drawable.xxilogo);
        } else if (CURRENTCINEMA.equals("CGV")) {
            currcin.setImageResource(R.drawable.cgvlogo);
        }

        constraintLayout = findViewById(R.id.ConstraintFood);

        if (CURRENTMOVIE.equals("MOVIE1")) {
            constraintLayout.setBackgroundResource(R.drawable.background1);
        } else if (CURRENTMOVIE.equals("MOVIE2")) {
            constraintLayout.setBackgroundResource(R.drawable.background2);
        } else if (CURRENTMOVIE.equals("MOVIE3")) {
            constraintLayout.setBackgroundResource(R.drawable.background3);
        }

        next = findViewById(R.id.btnFoodReceipt);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String c = col.getText().toString();
                final String p = pop.getText().toString();

                intent.putExtra("COLA", c);
                intent.putExtra("POPCORN", p);
//                Toast.makeText(GetFoodActivity.this, c + " " + p, Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });

    }
}
