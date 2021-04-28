package com.example.hotelbooking.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.hotelbooking.R;
import com.example.hotelbooking.model.HotelFilter;
import com.example.hotelbooking.model.RoomPrice;
import com.example.hotelbooking.model.RoomType;

public class FilterActivity extends AppCompatActivity {

    private ImageView ivBack;
    private EditText edtAmount;
    private Button btnApply;
    private RadioGroup rgType, rgPrice;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        ivBack = findViewById(R.id.ivBack);
        edtAmount = findViewById(R.id.edtAmount);
        btnApply = findViewById(R.id.btnApply);
        rgType = findViewById(R.id.rgRoomType);
        rgPrice = findViewById(R.id.rgPrice);
        HotelFilter filter = (HotelFilter) getIntent().getSerializableExtra("filter");
        switch (filter.getRoomType()) {
            case SINGLE:
                rgType.check(R.id.rdSingle);
                break;
            case DOUBLE:
                rgType.check(R.id.rdDouble);
                break;
            case TRIPLE:
                rgType.check(R.id.rdTriple);
                break;
        }

        switch (filter.getRoomPrice()) {
            case F10_T30:
                rgPrice.check(R.id.rdPrice1);
                break;
            case F30_T50:
                rgPrice.check(R.id.rdPrice2);
                break;
            case F50_T70:
                rgPrice.check(R.id.rdPrice3);
                break;

            case UP70:
                rgPrice.check(R.id.rdPrice4);
                break;
        }

        edtAmount.setText(filter.getAmount() + "");

        btnApply.setOnClickListener(v -> {
            if (rgType.getCheckedRadioButtonId() == R.id.rdSingle) {
                filter.setRoomType(RoomType.SINGLE);
            } else if (rgType.getCheckedRadioButtonId() == R.id.rdDouble) {
                filter.setRoomType(RoomType.DOUBLE);
            } else {
                filter.setRoomType(RoomType.TRIPLE);
            }
            if (rgPrice.getCheckedRadioButtonId() == R.id.rdPrice1) {
                filter.setRoomPrice(RoomPrice.F10_T30);
            } else if (rgPrice.getCheckedRadioButtonId() == R.id.rdPrice2) {
                filter.setRoomPrice(RoomPrice.F30_T50);
            } else if (rgPrice.getCheckedRadioButtonId() == R.id.rdPrice3) {
                filter.setRoomPrice(RoomPrice.F50_T70);
            } else {
                filter.setRoomPrice(RoomPrice.UP70);
            }
            filter.setAmount(Integer.parseInt(edtAmount.getText().toString().trim()));
            Intent intent = new Intent();
            intent.putExtra("filter", filter);
            setResult(Activity.RESULT_OK, intent);
            finish();
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}