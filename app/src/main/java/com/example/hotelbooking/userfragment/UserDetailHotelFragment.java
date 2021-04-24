package com.example.hotelbooking.userfragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.hotelbooking.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UserDetailHotelFragment extends Fragment {

    ImageView imageHotel;
    TextView tvName, tvName2, tvDescription;
    Button btnPickRoom;

    DatabaseReference ref;

    String name, description, purl;



    public UserDetailHotelFragment() {
        // Required empty public constructor
    }

    public UserDetailHotelFragment(String name, String description, String purl) {

        this.name = name;
        this.description = description;
        this.purl = purl;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_detail_hotel, container, false);

        imageHotel = view.findViewById(R.id.imageHotel);
        tvName = view.findViewById(R.id.tvName);
        tvName2 = view.findViewById(R.id.tvName2);
        tvDescription = view.findViewById(R.id.tvDescription);
        btnPickRoom = view.findViewById(R.id.btnPickRoom);

        ref = FirebaseDatabase.getInstance().getReference().child("hotel");
        String HotelKey = getArguments().getString("HotelKey");

        ref.child(HotelKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    name = snapshot.child("name").getValue().toString();
                    purl = snapshot.child("purl").getValue().toString();

                    tvName.setText(name);
                    tvName2.setText(name);
                    Glide.with(imageHotel.getContext()).load(purl).into(imageHotel);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return view;
    }
}