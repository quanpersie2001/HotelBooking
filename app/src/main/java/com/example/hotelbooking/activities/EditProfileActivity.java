package com.example.hotelbooking.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hotelbooking.R;

public class EditProfileActivity extends AppCompatActivity {

    TextView tvEditProfile;
    ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        tvEditProfile = findViewById(R.id.tvEditProfile);
        ivBack = findViewById(R.id.ivBack);

    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}