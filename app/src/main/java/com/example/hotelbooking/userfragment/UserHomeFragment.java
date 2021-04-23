package com.example.hotelbooking.userfragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.hotelbooking.R;
import com.example.hotelbooking.adapter.HotelAdapter;
import com.example.hotelbooking.databinding.ActivityUserMainBinding;
import com.example.hotelbooking.model.HotelModel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class UserHomeFragment extends Fragment {

   RecyclerView recyclerView;
   DatabaseReference mReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_home, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        mReference =  FirebaseDatabase.getInstance().getReference().child("hotel");

        ArrayList<HotelModel> list = new ArrayList<>();


        HotelAdapter hotelAdapter = new HotelAdapter(list, getActivity());

        recyclerView.setAdapter(hotelAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        FirebaseRecyclerOptions<HotelModel> options =
                new FirebaseRecyclerOptions.Builder<HotelModel>()
                        .setQuery(mReference, HotelModel.class)
                        .build();

        return view;
    }
}