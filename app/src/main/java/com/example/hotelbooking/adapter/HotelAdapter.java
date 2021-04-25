package com.example.hotelbooking.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hotelbooking.R;
import com.example.hotelbooking.activities.UserDetailHotelActivity;
import com.example.hotelbooking.model.Hotel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;


public class HotelAdapter extends FirebaseRecyclerAdapter<Hotel, HotelAdapter.viewHolder> {


    public HotelAdapter(@NonNull FirebaseRecyclerOptions<Hotel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull viewHolder holder, int position, @NonNull Hotel model) {

        holder.tvName.setText(model.getName());
        holder.tvPrice.setText(model.getPrice());
        holder.tvAddress.setText(model.getAddress());
        Glide.with(holder.hotelImage.getContext()).load(model.getPurl()).into(holder.hotelImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), UserDetailHotelActivity.class);
                intent.putExtra("HotelKey", getRef(position).getKey());
                v.getContext().startActivity(intent);
            }
        });

    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_hotel, parent, false);

        return new viewHolder(view);
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        ImageView hotelImage;
        TextView tvName, tvPrice, tvAddress;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            hotelImage = itemView.findViewById(R.id.hotelImage);
            tvName = itemView.findViewById(R.id.tvName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvAddress = itemView.findViewById(R.id.tvAddress);

        }

    }
}
