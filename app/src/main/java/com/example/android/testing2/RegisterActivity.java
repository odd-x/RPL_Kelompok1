package com.example.android.testing2;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    EditText email, pass, nama, address;
    FirebaseAuth mAuth;
    FirebaseFirestore fst;
    Button onreg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        nama = (EditText) findViewById(R.id.txt_reg_name);
        email = (EditText) findViewById(R.id.txt_reg_email);
        pass = (EditText) findViewById(R.id.txt_reg_pass);
        address = (EditText) findViewById(R.id.txt_reg_address);

    }

    public void regist(View view) {
        if (check()) {
            mAuth = FirebaseAuth.getInstance();
            mAuth.createUserWithEmailAndPassword(email.getText().toString(), pass.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = mAuth.getCurrentUser();
                                UserProfileChangeRequest userProfileChangeRequest = new UserProfileChangeRequest.Builder()
                                        .setDisplayName(nama.getText().toString()).build();

                                fst = FirebaseFirestore.getInstance();
                                HashMap<String, Object> isidata = new HashMap<>();
                                isidata.put("nama", nama.getText().toString());
                                isidata.put("address", address.getText().toString());
                                isidata.put("currentmoney", 0);
                                fst.collection("USERDATA").document(email.getText().toString())
                                        .set(isidata);
                                user.updateProfile(userProfileChangeRequest);
                                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));

                                finish();
                            }
                        }
                    });
        }
    }


    public boolean check() {
        if (nama.getText().toString().equals("")) {
            nama.setError("Nama Tidak boleh kosong");
            nama.requestFocus();
            return false;
        }
        if (email.getText().toString().equals("")) {
            email.setError("Email Tidak boleh kosong");
            email.requestFocus();
            return false;
        }

        if (pass.getText().toString().equals("")) {
            pass.setError("Password Tidak boleh kosong");
            pass.requestFocus();
            return false;
        }
        if (pass.getText().toString().length() < 6) {
            Toast.makeText(RegisterActivity.this, "Panjang kurang dari 6 karakter", Toast.LENGTH_SHORT);
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}


