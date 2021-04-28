package com.example.hotelbooking.adminfragment;

import android.content.Intent;
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
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hotelbooking.R;
import com.example.hotelbooking.activities.ReSearchActivity;
import com.example.hotelbooking.adapter.AdminHotelAdapter;
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

public class AdminHomeFragment extends Fragment {

    RecyclerView recyclerView;
    DatabaseReference mReference;
    AdminHotelAdapter adminHotelAdapter;
    ImageView ivSearch;
    TextView tvName;
    DocumentReference df;
    FirebaseFirestore fStore;
    FirebaseAuth mAuth;
    FirebaseUser mCurrentUser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_home, container, false);

        tvName = view.findViewById(R.id.tvName);
        ivSearch = view.findViewById(R.id.ivSearch);
        recyclerView = view.findViewById(R.id.recyclerView);
        mReference =  FirebaseDatabase.getInstance().getReference().child("hotel");
        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

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
        adminHotelAdapter.startListening();
    }
    @Override
    public void onStop() {
        super.onStop();
        adminHotelAdapter.stopListening();
    }
}