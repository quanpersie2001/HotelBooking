package com.example.hotelbooking.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hotelbooking.R;
import com.example.hotelbooking.activities.user_activities.UserBookNowActivity;
import com.example.hotelbooking.model.Room;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminRoomAdapter extends FirebaseRecyclerAdapter<Room, AdminRoomAdapter.viewHolder> {


    public AdminRoomAdapter(@NonNull FirebaseRecyclerOptions<Room> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull viewHolder holder, int position, @NonNull Room model) {

        holder.tvRoomName.setText(model.getName());
        holder.tvRoomPrice.setText(model.getPrice());
        Glide.with(holder.roomImage.getContext()).load(model.getPurl()).into(holder.roomImage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), UserBookNowActivity.class);
                intent.putExtra("RoomKey", getRef(position).getKey());
                v.getContext().startActivity(intent);
            }
        });


    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_room, parent, false);

        return new viewHolder(view);
    }

//    public void deleteItem(int position) {
//        DatabaseReference df = FirebaseDatabase.getInstance().getReference().child(getRef(position).getKey()).removeValue();
//    }

    public static class viewHolder extends RecyclerView.ViewHolder{

        public ImageView roomImage;
        public TextView tvRoomName, tvRoomPrice;


        public viewHolder(@NonNull View itemView) {
            super(itemView);
            roomImage = itemView.findViewById(R.id.roomImage);
            tvRoomName = itemView.findViewById(R.id.tvRoomName);
            tvRoomPrice = itemView.findViewById(R.id.tvRoomPrice);

        }
    }
}
