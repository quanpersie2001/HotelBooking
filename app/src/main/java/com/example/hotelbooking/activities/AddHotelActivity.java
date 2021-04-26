package com.example.hotelbooking.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.hotelbooking.R;

public class AddHotelActivity extends AppCompatActivity {

    EditText txtHotelName, txtHotelAddress, txtDescription, txtPrice, txtRank, txtPurl;
    ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hotel);
        txtHotelName = findViewById(R.id.txtUsername);
        txtHotelAddress = findViewById(R.id.txtFullName);
        txtDescription = findViewById(R.id.txtBirthday);
        txtPrice = findViewById(R.id.txtEmail);
        txtPurl = findViewById(R.id.txtPurl);
        txtRank = findViewById(R.id.txtPhone);
        ivBack = findViewById(R.id.ivBack);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AdminMainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}