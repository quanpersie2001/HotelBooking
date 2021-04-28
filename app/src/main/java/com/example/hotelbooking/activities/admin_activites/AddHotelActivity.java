package com.example.hotelbooking.activities.admin_activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.hotelbooking.R;
import com.example.hotelbooking.adminfragment.AdminAddFragment;
import com.example.hotelbooking.model.Hotel;
import com.example.hotelbooking.utils.Utils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddHotelActivity extends AppCompatActivity {

    EditText txtHotelName, txtHotelAddress, txtDescription, txtPrice, txtRank, txtPurl;
    ImageView ivBack;
    Button btnApply;

    DatabaseReference hotelRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hotel);
        txtHotelName = findViewById(R.id.txtHotelName);
        txtHotelAddress = findViewById(R.id.txtAddress);
        txtDescription = findViewById(R.id.txtDescription);
        txtPrice = findViewById(R.id.txtPrice);
        txtPurl = findViewById(R.id.txtPurl);
        txtRank = findViewById(R.id.txtRank);
        ivBack = findViewById(R.id.ivBack);
        btnApply = findViewById(R.id.btnApply);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               onBackPressed();

            }
        });

        hotelRef = FirebaseDatabase.getInstance().getReference().child(Utils.HOTEL);
        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertHotel();
            }
        });

    }
    private void insertHotel() {
        String name, description, address, price, purl;
        double rank;

        name = txtHotelName.getText().toString();
        description = txtDescription.getText().toString().trim();
        address = txtHotelAddress.getText().toString().trim();
        price = txtPrice.getText().toString().trim();
        purl = txtPurl.getText().toString().trim();
        rank = Double.parseDouble(txtRank.getText().toString().trim());

        Hotel hotel = new Hotel(purl, name, price, address, description, rank);

        hotelRef.push().setValue(hotel).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(AddHotelActivity.this, R.string.inserted_data, Toast.LENGTH_SHORT).show();
                    txtHotelName.setText("");
                    txtHotelAddress.setText("");
                    txtPrice.setText("");
                    txtPurl.setText("");
                    txtRank.setText("");
                    txtDescription.setText("");
                } else {

                }
            }
        });

    }

}