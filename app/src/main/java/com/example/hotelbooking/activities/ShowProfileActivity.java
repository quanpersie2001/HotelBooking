package com.example.hotelbooking.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.icu.util.ULocale;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hotelbooking.R;
import com.example.hotelbooking.utils.Utils;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ShowProfileActivity extends AppCompatActivity {

    TextView tvUsername, tvFullName, tvBirthday, tvEmail, tvPhone;
    ImageView ivBack;
    FirebaseFirestore fStore;
    FirebaseUser mCurrentUser;
    FirebaseAuth mAuth;

    String username, email, fullname, birthday, phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_profile);

        tvUsername = findViewById(R.id.tvUsername);
        tvFullName = findViewById(R.id.tvFullName);
        tvEmail = findViewById(R.id.tvEmail);
        tvPhone = findViewById(R.id.tvPhone);
        tvBirthday = findViewById(R.id.tvBirthday);

        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        mCurrentUser = mAuth.getCurrentUser();


    }

    @Override
    protected void onResume() {
        super.onResume();
        String uid = mCurrentUser.getUid();

        DocumentReference df = fStore.collection("users").document(uid);

        df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                username = documentSnapshot.getString(Utils.USER_NAME);
                email = documentSnapshot.getString(Utils.EMAIL);
                phone = documentSnapshot.getString(Utils.PHONE_NUMBER);
                fullname = documentSnapshot.getString(Utils.FULL_Name);
                birthday = documentSnapshot.getString(Utils.BIRTHDAY);

                tvFullName.setText(fullname);
                tvEmail.setText(email);
                tvBirthday.setText(birthday);
                tvPhone.setText(phone);
                tvUsername.setText(username);
            }
        });
    }
}