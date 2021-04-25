package com.example.hotelbooking.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.hotelbooking.R;
import com.example.hotelbooking.adminfragment.AdminHomeFragment;

public class AddHotelActivity extends AppCompatActivity {

    EditText txtHotelName, txtHotelAddress, txtDescription, txtPrice, txtRank, txtPurl;
    ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hotel);
        txtHotelName = findViewById(R.id.txtHotelName);
        txtHotelAddress = findViewById(R.id.txtHotelAddress);
        txtDescription = findViewById(R.id.txtDescription);
        txtPrice = findViewById(R.id.txtPrice);
        txtPurl = findViewById(R.id.txtPurl);
        txtRank = findViewById(R.id.txtRank);
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