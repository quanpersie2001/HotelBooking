package com.example.hotelbooking.userfragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hotelbooking.R;
import com.example.hotelbooking.activities.ReSearchActivity;
import com.example.hotelbooking.adapter.UserHotelAdapter;
import com.example.hotelbooking.model.Hotel;
import com.example.hotelbooking.utils.Utils;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class UserHomeFragment extends Fragment {

   RecyclerView recyclerView;
   DatabaseReference mReference;
   UserHotelAdapter userHotelAdapter;
   ImageView ivSearch;
   TextView tvName;
   DocumentReference df;
   FirebaseFirestore fStore;
   FirebaseAuth mAuth;
   FirebaseUser mCurrentUser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_user_home, container, false);
        tvName = view.findViewById(R.id.tvName);
        ivSearch = view.findViewById(R.id.ivSearch);
        recyclerView = view.findViewById(R.id.recyclerView);
        mReference =  FirebaseDatabase.getInstance().getReference().child("hotel");
        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();


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
    public void onResume() {
        super.onResume();

        mCurrentUser = mAuth.getCurrentUser();
        df = fStore.collection("users").document(mCurrentUser.getUid());
        df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                String username = documentSnapshot.getString(Utils.USER_NAME);
                tvName.setText(username);
            }
        });
        ivSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ReSearchActivity.class);
                startActivity(intent);
            }
        });

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