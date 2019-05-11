package com.example.android.testing2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class ListActivity extends AppCompatActivity {
    private FirebaseFirestore fst = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    String currentuser = mAuth.getCurrentUser().getEmail();
    private ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        setUpRecyclerView();
        Toast.makeText(this, currentuser, Toast.LENGTH_SHORT).show();
    }

    private void setUpRecyclerView(){
//        Query query = fst.collection("CGV").document("Pikachu")
//                .collection("1430").whereEqualTo("user",currentuser);
        Query query = fst.collection("USERDATA").document(currentuser).collection("BOOKEDSEATS");
        FirestoreRecyclerOptions<List> options = new FirestoreRecyclerOptions.Builder<List>()
                .setQuery(query, List.class)
                .build();

        adapter = new ListAdapter(options);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
