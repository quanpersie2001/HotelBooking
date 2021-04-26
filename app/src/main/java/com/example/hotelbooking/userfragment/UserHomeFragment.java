package com.example.hotelbooking.userfragment;

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
import com.example.hotelbooking.adapter.UserHotelAdapter;
import com.example.hotelbooking.model.Hotel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserHomeFragment extends Fragment {

   RecyclerView recyclerView;
   DatabaseReference mReference;
   UserHotelAdapter userHotelAdapter;
   EditText txtSearch;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_home, container, false);

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

        userHotelAdapter = new UserHotelAdapter(options);

        recyclerView.setAdapter(userHotelAdapter);

        return view;
    }
    @Override
    public void onStart() {
        super.onStart();
        userHotelAdapter.startListening();
    }
    @Override
    public void onStop() {
        super.onStop();
        userHotelAdapter.stopListening();
    }
}