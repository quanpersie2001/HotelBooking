package com.example.hotelbooking.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotelbooking.R;
import com.example.hotelbooking.adapter.SearchHotelAdapter;
import com.example.hotelbooking.model.Hotel;
import com.example.hotelbooking.model.HotelFilter;
import com.example.hotelbooking.model.Room;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ReSearchActivity extends AppCompatActivity {

    ImageView ivBack, ivFilter;
    EditText txtSearch;
    RecyclerView recyclerView;
    DatabaseReference mReference;
    private SearchHotelAdapter adapter;
    private DatabaseReference hotelRef;
    private HotelFilter hotelFilter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_re_search);

        ivBack = findViewById(R.id.ivBack);
        ivFilter = findViewById(R.id.ivFilter);
        txtSearch = findViewById(R.id.txtSearch);
        recyclerView = findViewById(R.id.recyclerView);

        mReference = FirebaseDatabase.getInstance().getReference().child("hotel");
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new SearchHotelAdapter(new ArrayList<>(),hotelFilter);
        recyclerView.setAdapter(adapter);
        FirebaseDatabase.getInstance().getReference().child("hotel").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    List<Hotel> hotels = new ArrayList<>();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        String id = snapshot.child("id").getValue().toString();
                        String name = snapshot.child("name").getValue().toString();
                        String purl = snapshot.child("purl").getValue().toString();
                        String description = snapshot.child("description").getValue().toString();
                        String address = snapshot.child("address").getValue().toString();
                        String price = snapshot.child("price").getValue().toString();
                        double rank = Double.parseDouble(snapshot.child("rank").getValue().toString());
                        Hotel hotel = new Hotel(id, purl, name, price, address, description, rank);
                        hotel.setKey(snapshot.getKey());
                        hotels.add(hotel);
                    }
                    adapter.changeData(hotels);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        searchFunction();
    }

    private void searchFunction() {
        txtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        ivFilter.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), FilterActivity.class);
            if (hotelFilter == null){
                hotelFilter = new HotelFilter();
            }
            intent.putExtra("filter", hotelFilter);
            startActivityForResult(intent, 100);
        });
        ivBack.setOnClickListener(v -> onBackPressed());

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 100 && data != null) {
                hotelFilter = (HotelFilter) data.getSerializableExtra("filter");
            }
        }
    }
}