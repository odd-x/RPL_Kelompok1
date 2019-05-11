package com.example.android.testing2;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.net.URI;

public class LoginActivity extends AppCompatActivity {

    EditText email, pass;
    VideoView bg;
    TextView reg, h1;
    Button login;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        getSupportActionBar().hide();

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        email = (EditText) findViewById(R.id.log_username);
        pass = (EditText) findViewById(R.id.log_password);

        bg = (VideoView) findViewById(R.id.bgVideo);
        reg = (TextView) findViewById(R.id.textRegister);
        login = (Button) findViewById(R.id.btnLogin);


        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();

        h1 = findViewById(R.id.h1);
        h1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str1 = "testing@mail.com";
                String str2 = "testing";
                email.setText(str1);
                pass.setText(str2);
            }
        });


        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.loginbg);
        bg.setVideoURI(uri);
        bg.start();
        bg.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });
    }

    public boolean check() {
        if (email.getText().toString().equals("")) {
            email.setError("Fill Email");
            email.requestFocus();
            return false;
        }
        if (pass.getText().toString().equals("")) {
            pass.setError("Fill Password");
            pass.requestFocus();
            return false;
        }
        return true;
    }

    public void login(View view) {
        if (check()) {
            new AsyncTask<Void, Void, Boolean>() {
                @Override
                protected Boolean doInBackground(Void... voids) {
                    mAuth.signInWithEmailAndPassword(email.getText().toString(), pass.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                        finish();
                                    } else {
                                        Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                    return null;
                }

                @Override
                protected void onPreExecute() {
                    Toast.makeText(LoginActivity.this, "Sign In...", Toast.LENGTH_SHORT).show();

                }

                @Override
                protected void onPostExecute(Boolean aBoolean) {
                    super.onPostExecute(aBoolean);
                }
            }.execute();
        }
    }

    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }
}

