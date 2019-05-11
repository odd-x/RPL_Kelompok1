package com.example.android.testing2;

import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class ListAdapter extends FirestoreRecyclerAdapter<List, ListAdapter.listHolder> {

    public ListAdapter(@NonNull FirestoreRecyclerOptions<List> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull listHolder holder, int position, @NonNull List model) {
        holder.title.setText(model.getTitle());
        holder.seat.setText(model.getSeat());
        holder.code.setText(model.getCode());
        holder.logo.setImageResource(model.getLogo());
    }

    @NonNull
    @Override
    public listHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.book_item, viewGroup, false);

        return new listHolder(v);
    }

    class listHolder extends RecyclerView.ViewHolder {
        TextView title, seat, code;
        ImageView logo;

        public listHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.listMovieTitle);
            seat = itemView.findViewById(R.id.listMovieSeat);
            code = itemView.findViewById(R.id.listMoviePaid);
            logo = itemView.findViewById(R.id.listCinemaLogo);


        }
    }
}
