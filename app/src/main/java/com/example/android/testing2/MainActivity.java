package com.example.android.testing2;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    TextView SlideMoney, SlideUser;
    FirebaseAuth mAuth;
    String currentUsername;
    FirebaseFirestore fst;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        fst = FirebaseFirestore.getInstance();
        currentUsername = mAuth.getCurrentUser().getEmail();


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView nv = findViewById(R.id.nav_view);
        nv.setNavigationItemSelectedListener(this);

        View headerview = nv.getHeaderView(0);

        SlideUser = headerview.findViewById(R.id.SlidingBarName);
        SlideUser.setText(currentUsername);

        SlideMoney = headerview.findViewById(R.id.SlidingBarMoney);
        fst.collection("USERDATA").document(currentUsername).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            String mid = documentSnapshot.get("currentmoney").toString();
                            String money = "Rp " + mid + ",-";
                            SlideMoney.setText(money);
                        }
                    }
                });
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar
                , R.string.navigation_open, R.string.navigation_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new HomeFragmentHome()).commit();
            nv.setCheckedItem(R.id.nav_home);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new HomeFragmentHome()).commit();
                break;
            case R.id.nav_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new HomeFragmentProfile()).commit();
                break;
            case R.id.nav_topup:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new HomeFragmentTopUp()).commit();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }


}


//
//        super.onCreate(savedInstanceState);
//                setContentView(R.layout.activity_main);
//
//                BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
//                bottomNav.setOnNavigationItemSelectedListener(navListener);
//
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                new HomeFragmentHome()).commit();
//                }
//
//private BottomNavigationView.OnNavigationItemSelectedListener navListener =
//        new BottomNavigationView.OnNavigationItemSelectedListener() {
//@Override
//public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//        Fragment selectFragment = null;
//
//        switch (menuItem.getItemId()) {
//        case R.id.nav_profile:
//        selectFragment = new HomeFragmentProfile();
//        break;
//        case R.id.nav_home:
//        selectFragment = new HomeFragmentHome();
//        break;
//        case R.id.nav_search:
//        selectFragment = new HomeFragmentTopUp();
//        break;
//        }
//        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//        selectFragment).commit();
//        return true;
//        }
//        };





