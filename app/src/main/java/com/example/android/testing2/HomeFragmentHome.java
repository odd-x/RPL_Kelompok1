package com.example.android.testing2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import javax.xml.transform.Result;

public class HomeFragmentHome extends Fragment {
    Button btnOrder;
    TextView name, cash;

    FirebaseAuth mAuth;
    FirebaseFirestore fst;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.home_fragment_home, container, false);

        mAuth = FirebaseAuth.getInstance();
        String curr = mAuth.getCurrentUser().getEmail();
//        name = result.findViewById(R.id.homename);?
//        name.setText(curr);
//        cash = result.findViewById(R.id.homeduit);
        fst = FirebaseFirestore.getInstance();

        fst.collection("USERDATA").document(curr).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
//                            cash.setText(documentSnapshot.get("currentmoney").toString());
                        }
                    }
                });

        ViewPager pager = (ViewPager) result.findViewById(R.id.ViewPagerHome);
        btnOrder = (Button) result.findViewById(R.id.btnOrder);
        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MovieSelectActivity.class);
                startActivity(intent);
            }
        });
        pager.setAdapter(buildAdapter());
        return (result);
    }

    private PagerAdapter buildAdapter() {
        return (new vpMainAdapter(getActivity(), getChildFragmentManager()));
    }

}
