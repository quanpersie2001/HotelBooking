package com.example.hotelbooking.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.example.hotelbooking.R;

public class UserBookedActivity extends AppCompatActivity {
    private Button btnBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_booked);
        btnBook = findViewById(R.id.btnBook);
        btnBook.setOnClickListener(v->{

        });
    }
}