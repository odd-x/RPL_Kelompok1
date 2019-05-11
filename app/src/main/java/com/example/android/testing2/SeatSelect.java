package com.example.android.testing2;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SeatSelect extends AppCompatActivity {

    private final static String TAG = "SeatSelect";


    Button next, nextfood;
    TextView txtCurrentMovie, txtCurrMovTime;
    ImageView theatrelogo;
    ConstraintLayout constraintLayout;
    String currentmovie, currentcinema, currenttime, currentpayment, movie1, movie2, movie3;
    ToggleButton a1, a2, a3, a4, a5, b1, b2, b3, b4, b5, c1, c2, c3, c4, c5;
    FirebaseFirestore fst = FirebaseFirestore.getInstance();
    ArrayList<String> SeatSelected = new ArrayList<String>();

    CollectionReference cr;
    DocumentReference dra1, dra2, dra3, dra4, dra5, drb1, drb2, drb3, drb4, drb5, drc1, drc2, drc3, drc4, drc5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat_select);
        constraintLayout = findViewById(R.id.clseatselect);
        final Intent intent = new Intent(this, GetFoodActivity.class);
        currentpayment = getIntent().getStringExtra("CURRENTPAYMENT");
        intent.putExtra("CURRENTPAYMENT", currentpayment);
        theatrelogo = findViewById(R.id.imgCurrCinema);
        currentmovie = getIntent().getStringExtra("CURRENTMOVIE");
        intent.putExtra("CURRENTMOVIE", currentmovie);
        currentcinema = getIntent().getStringExtra("CURRENTCINEMA");
        intent.putExtra("CURRENTCINEMA", currentcinema);
        currenttime = getIntent().getStringExtra("CURRENTTIME");
        intent.putExtra("CURRENTTIME", currenttime);
        txtCurrentMovie = findViewById(R.id.txtCurrentMovie);
        txtCurrMovTime = findViewById(R.id.txtCurrMovTime);
        txtCurrMovTime.setText(currenttime);
        movie1 = "Saekano";
        movie2 = "Pikachu";
        movie3 = "Aobuta";
        if (currentcinema.equals("XXI")) {
            theatrelogo.setImageResource(R.drawable.xxilogo);
        } else if (currentcinema.equals("CGV")) {
            theatrelogo.setImageResource(R.drawable.cgvlogo);
        }
        Toast.makeText(this, currentcinema, Toast.LENGTH_SHORT).show();


        if (currentmovie.equals("MOVIE1")) {
            txtCurrentMovie.setText(R.string.nameSaekano);
            constraintLayout.setBackgroundResource(R.drawable.background1);
            cr = fst.collection(currentcinema).document("Saekano")
                    .collection(currenttime);
            dra1 = cr.document("A1");
            dra2 = cr.document("A2");
            dra3 = cr.document("A3");
            dra4 = cr.document("A4");
            dra5 = cr.document("A5");
            drb1 = cr.document("B1");
            drb1 = cr.document("B1");
            drb2 = cr.document("B2");
            drb3 = cr.document("B3");
            drb4 = cr.document("B4");
            drb5 = cr.document("B5");
            drc1 = cr.document("C1");
            drc2 = cr.document("C2");
            drc3 = cr.document("C3");
            drc4 = cr.document("C4");
            drc5 = cr.document("C5");


        } else if (currentmovie.equals("MOVIE2")) {
            txtCurrentMovie.setText(R.string.namePikachu);
            constraintLayout.setBackgroundResource(R.drawable.background2);
            cr = fst.collection(currentcinema).document("Pikachu")
                    .collection(currenttime);
            dra1 = cr.document("A1");
            dra2 = cr.document("A2");
            dra3 = cr.document("A3");
            dra4 = cr.document("A4");
            dra5 = cr.document("A5");
            drb1 = cr.document("B1");
            drb1 = cr.document("B1");
            drb2 = cr.document("B2");
            drb3 = cr.document("B3");
            drb4 = cr.document("B4");
            drb5 = cr.document("B5");
            drc1 = cr.document("C1");
            drc2 = cr.document("C2");
            drc3 = cr.document("C3");
            drc4 = cr.document("C4");
            drc5 = cr.document("C5");
        } else if (currentmovie.equals("MOVIE3")) {
            txtCurrentMovie.setText(R.string.nameSeishun);
            constraintLayout.setBackgroundResource(R.drawable.background3);
            cr = fst.collection(currentcinema).document("Aobuta")
                    .collection(currenttime);
            dra1 = cr.document("A1");
            dra2 = cr.document("A2");
            dra3 = cr.document("A3");
            dra4 = cr.document("A4");
            dra5 = cr.document("A5");
            drb1 = cr.document("B1");
            drb1 = cr.document("B1");
            drb2 = cr.document("B2");
            drb3 = cr.document("B3");
            drb4 = cr.document("B4");
            drb5 = cr.document("B5");
            drc1 = cr.document("C1");
            drc2 = cr.document("C2");
            drc3 = cr.document("C3");
            drc4 = cr.document("C4");
            drc5 = cr.document("C5");
        } else {
            String strbk = "FAILEDINITIALIZE";
            txtCurrentMovie.setText(strbk);
        }


        a1 = findViewById(R.id.btnSeat1);
        a2 = findViewById(R.id.btnSeat2);
        a3 = findViewById(R.id.btnSeat3);
        a4 = findViewById(R.id.btnSeat4);
        a5 = findViewById(R.id.btnSeat5);
        b1 = findViewById(R.id.btnSeat6);
        b2 = findViewById(R.id.btnSeat7);
        b3 = findViewById(R.id.btnSeat8);
        b4 = findViewById(R.id.btnSeat9);
        b5 = findViewById(R.id.btnSeat10);
        c1 = findViewById(R.id.btnSeat11);
        c2 = findViewById(R.id.btnSeat12);
        c3 = findViewById(R.id.btnSeat13);
        c4 = findViewById(R.id.btnSeat14);
        c5 = findViewById(R.id.btnSeat15);


        check(dra1, a1);
        check(dra2, a2);
        check(dra3, a3);
        check(dra4, a4);
        check(dra5, a5);
        check(drb1, b1);
        check(drb2, b2);
        check(drb3, b3);
        check(drb4, b4);
        check(drb5, b5);
        check(drc1, c1);
        check(drc2, c2);
        check(drc3, c3);
        check(drc4, c4);
        check(drc5, c5);

        a1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pick(a1, "A1");
            }
        });

        a2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pick(a2, "A2");
            }
        });

        a3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pick(a3, "A3");
            }
        });

        a4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pick(a4, "A4");
            }
        });

        a5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pick(a5, "A5");
            }
        });


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pick(b1, "B1");
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pick(b2, "B2");
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pick(b3, "B3");
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pick(b4, "B4");
            }
        });

        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pick(b5, "B5");
            }
        });


        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pick(c1, "C1");
            }
        });

        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pick(c2, "C2");
            }
        });

        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pick(c3, "C3");
            }
        });

        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pick(c4, "C4");
            }
        });

        c5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pick(c5, "C5");
            }
        });

        next = findViewById(R.id.btnBuyFood);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("CURRENTSEAT", SeatSelected);
                startActivity(intent);


            }
        });

    }

    public void test(Object o) {
        Map<String, Object> seat = new HashMap<>();
        seat.put("user", o.toString());
        seat.put("testing", true);

        if (currentmovie.equals("MOVIE1")) {
            fst.collection(currentcinema).document("Saekano")
                    .collection(currenttime).document("Currseat").set(seat)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(SeatSelect.this, currentcinema + " " + movie1, Toast.LENGTH_SHORT).show();
                        }
                    });

        } else if (currentmovie.equals("MOVIE2")) {

            fst.collection(currentcinema).document("Pikachu")
                    .collection(currenttime).document("CURRENTSEAT").set(seat)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(SeatSelect.this, currentcinema + " " + movie2, Toast.LENGTH_SHORT).show();
                        }
                    });
        } else if (currentmovie.equals("MOVIE3")) {

            fst.collection(currentcinema).document("Aobuta")
                    .collection(currenttime).document("CURRENTSEAT").set(seat)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(SeatSelect.this, currentcinema + " " + movie3, Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Toast.makeText(SeatSelect.this, "rip", Toast.LENGTH_SHORT).show();
        }


    }


    public void pick(ToggleButton tog, String varname) {
        if (tog.isChecked()) {
            SeatSelected.add(varname);
            Toast.makeText(this, SeatSelected.get(0), Toast.LENGTH_SHORT).show();
        }
        if (!tog.isChecked()) {
            SeatSelected.remove(varname);
            if (SeatSelected.isEmpty()) {
                Toast.makeText(this, "ISEMPTY", Toast.LENGTH_SHORT).show();
            }
        }
    }


    public void check(DocumentReference drx, final ToggleButton tx) {
        drx.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        tx.setEnabled(false);
                    } else {
                        Log.d(TAG, "Document does not exist!");
                    }
                } else {
                    Log.d(TAG, "Failed with: ", task.getException());
                }
            }
        });
    }

}
