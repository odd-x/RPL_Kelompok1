package com.example.android.testing2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class HomeFragmentTopUp extends Fragment {

    Button btnAdd;
    TextView saldo;
    EditText box;
    FirebaseAuth mAuth;
    FirebaseFirestore fst;
    int currmon;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.home_fragment_topup, container, false);

        mAuth = FirebaseAuth.getInstance();
        final String curr = mAuth.getCurrentUser().getEmail();
        saldo = result.findViewById(R.id.txtTopUpCurr);
        fst = FirebaseFirestore.getInstance();
        fst.collection("USERDATA").document(curr).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            String mid = documentSnapshot.get("currentmoney").toString();
                            currmon = Integer.parseInt(documentSnapshot.get("currentmoney").toString());
                            String money = "Rp " + mid + ",-";
                            saldo.setText(money);
                        }
                    }
                });

        box = result.findViewById(R.id.edAddSaldo);
        btnAdd = result.findViewById(R.id.btnAddSaldo);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int duit = Integer.parseInt(box.getText().toString());
                int finalduit = currmon + duit;
                fst.collection("USERDATA").document(curr).update("currentmoney", finalduit);
                fst.collection("USERDATA").document(curr).get()
                        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                if (documentSnapshot.exists()) {
                                    String mid = documentSnapshot.get("currentmoney").toString();
                                    currmon = Integer.parseInt(documentSnapshot.get("currentmoney").toString());
                                    String money = "Rp " + mid + ",-";
                                    saldo.setText(money);
                                }
                            }
                        });
            }
        });
        return result;
    }
}
