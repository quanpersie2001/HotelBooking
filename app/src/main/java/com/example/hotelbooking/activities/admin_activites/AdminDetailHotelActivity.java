package com.example.hotelbooking.activities.admin_activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hotelbooking.R;
import com.example.hotelbooking.adapter.AdminRoomAdapter;
import com.example.hotelbooking.model.Room;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminDetailHotelActivity extends AppCompatActivity {

    ImageView imageHotel, ivBack;
    TextView tvName, tvName2, tvDescription, tvRank, tvAddress, tvEdit;

    DatabaseReference hotelRef, roomRef;
    RecyclerView recyclerView;


    String name, description, purl, address;
    String rank;
    private AdminRoomAdapter adminRoomAdapter;
    private String hotelId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_detail_hotel);

        imageHotel = findViewById(R.id.imageHotel);
        tvName = findViewById(R.id.tvName);
        tvName2 = findViewById(R.id.tvName2);
        tvDescription = findViewById(R.id.tvDescription);
        tvRank = findViewById(R.id.tvRank);
        tvAddress = findViewById(R.id.tvStatus);
        ivBack = findViewById(R.id.ivBack);
        tvEdit = findViewById(R.id.tvEdit);


        roomRef = FirebaseDatabase.getInstance().getReference().child("rooms");
        hotelRef = FirebaseDatabase.getInstance().getReference().child("hotel");



        String HotelKey = getIntent().getStringExtra("HotelKey");
        hotelId = getIntent().getStringExtra("HotelId");
        if (HotelKey != null && !HotelKey.isEmpty()){
            hotelRef.child(HotelKey).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()){
                        name = snapshot.child("name").getValue().toString();
                        purl = snapshot.child("purl").getValue().toString();
                        description = snapshot.child("description").getValue().toString();
                        address = snapshot.child("address").getValue().toString();
                        rank = String.valueOf(snapshot.child("rank").getValue());
                        tvDescription.setText(description);
                        tvRank.setText(rank);
                        tvAddress.setText(address);
                        tvName.setText(name);
                        tvName2.setText(name);
                        Glide.with(AdminDetailHotelActivity.this).load(purl).into(imageHotel);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }


        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        FirebaseRecyclerOptions<Room> options =
                new FirebaseRecyclerOptions.Builder<Room>()
                        .setQuery(roomRef.orderByChild("hotelID").equalTo(hotelId), Room.class)
                        .build();

        adminRoomAdapter = new AdminRoomAdapter(options);
        recyclerView.setAdapter(adminRoomAdapter);


    }

    @Override
    protected void onResume() {
        super.onResume();

        tvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AdminEditHotelActivity.class);
                startActivity(intent);
                finish();
            }
        });

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    private void insertRoom(){

    }

    @Override
    public void onStart() {
        super.onStart();
        adminRoomAdapter.startListening();
    }
    @Override
    public void onStop() {
        super.onStop();
        adminRoomAdapter.stopListening();}

}