package com.example.hotelbooking.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.bumptech.glide.Glide;
import com.example.hotelbooking.R;
import com.example.hotelbooking.userfragment.UserLocationFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminDetailHotelActivity extends AppCompatActivity {

    ImageView imageHotel, ivBack;
    TextView tvName, tvName2, tvDescription, tvRank, tvAddress;

    DatabaseReference ref;

    String name, description, purl, address;
    String rank;

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

        ref = FirebaseDatabase.getInstance().getReference().child("hotel");
        String HotelKey = getIntent().getStringExtra("HotelKey");
        ref.child(HotelKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    name = snapshot.child("name").getValue().toString();
                    purl = snapshot.child("purl").getValue().toString();
                    description = snapshot.child("description").getValue().toString();
                    address = snapshot.child("address").getValue().toString();
                    rank = snapshot.child("rank").getValue().toString();
                    tvDescription.setText(description);
                    tvRank.setText(rank);
                    tvAddress.setText(address);
                    tvName.setText(name);
                    tvName2.setText(name);
                    Glide.with(imageHotel.getContext()).load(purl).into(imageHotel);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AdminMainActivity.class));
                finish();
            }
        });

        tvAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new UserLocationFragment();
                Bundle bundle = new Bundle();
                fragment.setArguments(bundle);
                ((FragmentActivity) v.getContext()).getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.detail_layout, fragment)
                        .commit();
            }
        });


    }
}