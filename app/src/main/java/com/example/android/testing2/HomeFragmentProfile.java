package com.example.android.testing2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class HomeFragmentProfile extends Fragment {

    TextView name, userid, saldo;

    FirebaseAuth mAuth;
    FirebaseFirestore fst;
    Button btnWatched, logout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.home_fragment_profile, container, false);

        mAuth = FirebaseAuth.getInstance();
        String curr = mAuth.getCurrentUser().getEmail();
        String nem = mAuth.getCurrentUser().getDisplayName();

        userid = result.findViewById(R.id.txtProfUsr);
        userid.setText(curr);
        name = result.findViewById(R.id.txtProfName);
        name.setText(nem);
        saldo = result.findViewById(R.id.ProfSaldo);

        Button logout = result.findViewById(R.id.btnLogout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent wew = new Intent(getActivity(),LoginActivity.class);
                startActivity(wew);
            }
        });

        fst = FirebaseFirestore.getInstance();
        fst.collection("USERDATA").document(curr).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            String mid = documentSnapshot.get("currentmoney").toString();
                            String money = "Rp " + mid + ",-";
                            saldo.setText(money);
                        }
                    }
                });
        btnWatched = result.findViewById(R.id.btnBookedSeats);
        btnWatched.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ListActivity.class);
                startActivity(intent);
            }
        });
        return result;
    }
}


