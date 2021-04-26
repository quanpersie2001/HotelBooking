package com.example.hotelbooking.adminfragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.hotelbooking.R;
import com.example.hotelbooking.adapter.AdminHotelAdapter;
import com.example.hotelbooking.adapter.UserHotelAdapter;
import com.example.hotelbooking.model.Hotel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminHomeFragment extends Fragment {

    RecyclerView recyclerView;
    DatabaseReference mReference;
    AdminHotelAdapter adminHotelAdapter;
    EditText txtSearch;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_home, container, false);

        txtSearch = view.findViewById(R.id.txtSearch);
        txtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        recyclerView = view.findViewById(R.id.recyclerView);
        mReference =  FirebaseDatabase.getInstance().getReference().child("hotel");
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        FirebaseRecyclerOptions<Hotel> options =
                new FirebaseRecyclerOptions.Builder<Hotel>()
                        .setQuery(mReference, Hotel.class)
                        .build();

        adminHotelAdapter = new AdminHotelAdapter(options);

        recyclerView.setAdapter(adminHotelAdapter);

        return view;
    }
    @Override
    public void onStart() {
        super.onStart();
        adminHotelAdapter.startListening();
    }
    @Override
    public void onStop() {
        super.onStop();
        adminHotelAdapter.stopListening();
    }
}