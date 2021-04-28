package com.example.hotelbooking.activities.admin_activites;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hotelbooking.R;
import com.example.hotelbooking.model.Room;
import com.google.firebase.database.FirebaseDatabase;

public class AddRoomActivity extends AppCompatActivity {
    private String hotelID;
    EditText txtRoomName, txtDescription, txtPrice, txtSquare, txtUrl;
    RadioGroup rgType;
    Button btnAdd;
    ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_room);
        btnAdd = findViewById(R.id.btnApply);
        rgType = findViewById(R.id.rgRoomType);
        txtRoomName = findViewById(R.id.txtRoomName);
        txtDescription = findViewById(R.id.txtDescription);
        txtPrice = findViewById(R.id.txtPrice);
        txtSquare = findViewById(R.id.txtSquare);
        txtUrl = findViewById(R.id.txtPurl);
        hotelID = getIntent().getStringExtra("hotelID");
        ivBack = findViewById(R.id.ivBack);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnAdd.setOnClickListener(v -> {
            String name = txtRoomName.getText().toString().trim();
            String des = txtDescription.getText().toString().trim();
            String price = txtPrice.getText().toString().trim();
            String square = txtSquare.getText().toString().trim();
            String url = txtUrl.getText().toString().trim();
            String type;
            if (rgType.getCheckedRadioButtonId() == R.id.rdSingle) {
                type = "Single";
            } else if (rgType.getCheckedRadioButtonId() == R.id.rdDouble) {
                type = "Double";
            } else {
                type = "Triple";
            }
            Room room = new Room(hotelID, name, type, url, price, "0", square);
            FirebaseDatabase.getInstance().getReference().child("rooms").push().setValue(room)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(AddRoomActivity.this, R.string.inserted_data, Toast.LENGTH_SHORT).show();
                            onBackPressed();
                        }
                    });
        });
    }
}