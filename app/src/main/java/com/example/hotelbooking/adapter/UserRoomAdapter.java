package com.example.hotelbooking.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hotelbooking.R;
import com.example.hotelbooking.model.Room;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class UserRoomAdapter extends FirebaseRecyclerAdapter<Room, UserRoomAdapter.viewHolder> {


    public UserRoomAdapter(@NonNull FirebaseRecyclerOptions<Room> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull viewHolder holder, int position, @NonNull Room model) {

        holder.tvRoomName.setText(model.getName());
        holder.tvRoomPrice.setText((int) model.getPrice());
//        Glide.with(holder.roomImage.getContext()).load(model.getPurl()).into(holder.roomImage);

    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_room, parent, false);

        return new viewHolder(view);
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        ImageView roomImage;
        TextView tvRoomName, tvRoomPrice;
        FirebaseAuth mAuth;
        FirebaseFirestore fStore;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            roomImage = itemView.findViewById(R.id.roomImage);
            tvRoomName = itemView.findViewById(R.id.tvRoomName);
            tvRoomPrice = itemView.findViewById(R.id.tvRoomPrice);

            mAuth = FirebaseAuth.getInstance();
            fStore = FirebaseFirestore.getInstance();
        }
    }
}
