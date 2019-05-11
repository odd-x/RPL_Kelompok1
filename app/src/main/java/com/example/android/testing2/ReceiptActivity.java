package com.example.android.testing2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class ReceiptActivity extends AppCompatActivity {

    String currentmovie, currentcinema, currenttime, currentuser, currentpayment, snacks;
    ArrayList<String> seats;
    FirebaseAuth mAuth;
    TextView txtMovie, txtSeat, txtTime, txtUser, txtSnack, txtUserMoney, orderID, price;
    ImageView cinema, receiptQR;
    Button btnReceiptConfirm;
    String popcorn, cola;
    FirebaseFirestore fst = FirebaseFirestore.getInstance();
    int totalpayment, currentduit, tempcola, temppopcorn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt);

        mAuth = FirebaseAuth.getInstance();

        txtUser = findViewById(R.id.txtReceiptUsr);
        txtMovie = findViewById(R.id.txtReceiptMovieName);
        txtTime = findViewById(R.id.txtReceiptMovieTime);
        txtSeat = findViewById(R.id.txtReceiptSeatlist);
        cinema = findViewById(R.id.imgReceiptCinema);
        receiptQR = findViewById(R.id.receiptQrPlaceholder);
        orderID = findViewById(R.id.txtOrderId);
        txtSnack = findViewById(R.id.txtReceiptSnack);

        popcorn = getIntent().getStringExtra("POPCORN");
        cola = getIntent().getStringExtra("COLA");

        if (cola.equals("") && (popcorn.equals(""))) {
            tempcola = 0;
            temppopcorn = 0;
        } else {

            tempcola = Integer.parseInt(cola);
            temppopcorn = Integer.parseInt(popcorn);

        }
        snacks = "Popcorn " + popcorn + ", Coca-cola " + cola;
        txtSnack.setText(snacks);

        currentuser = mAuth.getCurrentUser().getEmail();
        txtUser.setText(currentuser);

        currentmovie = getIntent().getStringExtra("CURRENTMOVIE");
        if (currentmovie.equals("MOVIE1")) {
            txtMovie.setText(R.string.nameSaekano);
        } else if (currentmovie.equals("MOVIE2")) {
            txtMovie.setText(R.string.namePikachu);
        } else if (currentmovie.equals("MOVIE3")) {
            txtMovie.setText(R.string.nameSeishun);
        }

        currentcinema = getIntent().getStringExtra("CURRENTCINEMA");
        currenttime = getIntent().getStringExtra("CURRENTTIME");

        txtTime.setText(currenttime);


        if (currentcinema.equals("XXI")) {
            cinema.setImageResource(R.drawable.xxilogo);
        } else if (currentcinema.equals("CGV")) {
            cinema.setImageResource(R.drawable.cgvlogo);
        }

        seats = getIntent().getStringArrayListExtra("CURRENTSEAT");


        final StringBuilder listString = new StringBuilder();
        for (String s : seats)
            if (seats.size() < 2) {
                listString.append(s);

            } else {
                listString.append(s + ",");
            }
        txtSeat.setText(listString);
//

        txtUserMoney = findViewById(R.id.txtReceiptCurrMoney);
        fst.collection("USERDATA").document(currentuser).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            String money = "Rp " + documentSnapshot.get("currentmoney").toString() + ",-";
                            txtUserMoney.setText(money);
                            currentduit = Integer.parseInt(documentSnapshot.get("currentmoney").toString());
                        }
                    }
                });

        currentpayment = getIntent().getStringExtra("CURRENTPAYMENT");
        for (int i = 0; i < seats.size(); i++) {
            totalpayment = totalpayment + 10000;
        }

        int hargacola = 5000;
        int hargapopcorn = 10000;

        totalpayment = totalpayment + (hargacola * tempcola) + (hargapopcorn * temppopcorn);

        String harga = "RP " + totalpayment + ",- ";
//
        price = findViewById(R.id.txtReceiptPrice);
        price.setText(harga);

        btnReceiptConfirm = findViewById(R.id.btnReceiptConfirm);

        btnReceiptConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (currentpayment.equals("cinepay")) {
                    if (totalpayment > currentduit) {
                        Toast.makeText(ReceiptActivity.this, "Pembayaran dialihkan ke Cash!", Toast.LENGTH_SHORT).show();
                        currentpayment = "cash";
                    } else {
                        currentduit = currentduit - totalpayment;
                        fst.collection("USERDATA").document(currentuser).update("currentmoney", currentduit);
//                        Toast.makeText(ReceiptActivity.this, "money updated", Toast.LENGTH_SHORT).show();
                    }
                }
                for (final String x : seats) {

                    final Map<String, Object> seat = new HashMap<>();
                    seat.put("user", currentuser);
                    seat.put("done", false);
                    seat.put("type of payment", currentpayment);
                    seat.put("popcorn", popcorn);
                    seat.put("cola", cola);

                    if (currentmovie.equals("MOVIE1")) {
                        final String qr = (currentuser + "/" + currentcinema + "/Saekano/" + currenttime + "/" + x + "/p" + popcorn + "c" + cola).trim();
                        seat.put("code", qr);
                        fst.collection(currentcinema).document("Saekano")
                                .collection(currenttime).document(x).set(seat)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                                        try {
                                            BitMatrix bitMatrix = multiFormatWriter.encode(qr, BarcodeFormat.QR_CODE, 500, 500);
                                            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                                            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                                            receiptQR.setImageBitmap(bitmap);
                                        } catch (WriterException e) {
                                            e.printStackTrace();
                                        }
                                        Toast.makeText(ReceiptActivity.this, "Ordered!, your order ID is " + qr, Toast.LENGTH_SHORT).show();
                                        orderID.setText(qr);
                                        seat.put("seat", x);
                                        seat.put("title", getApplicationContext().getString(R.string.nameSaekano));

                                        fst.collection("USERDATA").document(currentuser).collection("BOOKEDSEATS").add(seat);
                                    }
                                });

                    } else if (currentmovie.equals("MOVIE2")) {
                        final String qr = (currentuser + "/" + currentcinema + "/Pikachu/" + currenttime + "/" + x + "/p" + popcorn + "c" + cola).trim();
                        seat.put("code", qr);
                        fst.collection(currentcinema).document("Pikachu")
                                .collection(currenttime).document(x).set(seat)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                                        try {
                                            BitMatrix bitMatrix = multiFormatWriter.encode(qr, BarcodeFormat.QR_CODE, 500, 500);
                                            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                                            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                                            receiptQR.setImageBitmap(bitmap);
                                        } catch (WriterException e) {
                                            e.printStackTrace();
                                        }
                                        Toast.makeText(ReceiptActivity.this, "Ordered!, your order ID is " + qr, Toast.LENGTH_SHORT).show();
                                        orderID.setText(qr);
                                        seat.put("seat", x);
                                        seat.put("title",getApplicationContext().getString(R.string.namePikachu));

                                        fst.collection("USERDATA").document(currentuser).collection("BOOKEDSEATS").add(seat);
                                    }
                                });
                    } else if (currentmovie.equals("MOVIE3")) {
                        final String qr = (currentuser + "/" + currentcinema + "/Aobuta/" + currenttime + "/" + x + "/p" + popcorn + "c" + cola).trim();
                        seat.put("code", qr);
                        fst.collection(currentcinema).document("Aobuta")
                                .collection(currenttime).document(x).set(seat)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                                        try {
                                            BitMatrix bitMatrix = multiFormatWriter.encode(qr, BarcodeFormat.QR_CODE, 500, 500);
                                            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                                            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                                            receiptQR.setImageBitmap(bitmap);
                                        } catch (WriterException e) {
                                            e.printStackTrace();
                                        }
                                        Toast.makeText(ReceiptActivity.this, "Ordered!, your order ID is " + qr, Toast.LENGTH_SHORT).show();
                                        orderID.setText(qr);
                                        seat.put("seat", x);
                                        seat.put("title", getApplicationContext().getString(R.string.nameSeishun));

                                        fst.collection("USERDATA").document(currentuser).collection("BOOKEDSEATS").document().set(seat);
                                    }
                                });
                    }

                }

                String bcks = "Back to Home";
                btnReceiptConfirm.setText(bcks);
                btnReceiptConfirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ReceiptActivity.this, MainActivity.class);
                        startActivity(intent);
                    }


                });
            }
        });
    }
}


