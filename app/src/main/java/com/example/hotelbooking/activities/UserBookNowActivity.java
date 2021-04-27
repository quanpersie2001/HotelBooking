package com.example.hotelbooking.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hotelbooking.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class UserBookNowActivity extends AppCompatActivity {
    ImageView imageRoom, ivBack;
    TextView tvName, tvName2, tvPrice, tvSquare, tvType, tvStatus, txtCheckIn, txtCheckOut;
    Button btnBook;
    DatabaseReference roomRef;
    private int mDayCheckIn;
    private int mMonthCheckIn;
    private int mYearCheckIn;

    private int mDayCheckOut;
    private int mMonthCheckOut;
    private int mYearCheckOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_book_now);

        imageRoom = findViewById(R.id.imageRoom);
        tvName = findViewById(R.id.tvName);
        tvName2 = findViewById(R.id.tvName2);
        tvPrice = findViewById(R.id.tvPrice);
        tvSquare = findViewById(R.id.tvSquare);
        tvType = findViewById(R.id.tvType);
        tvStatus = findViewById(R.id.tvStatus);
        ivBack = findViewById(R.id.ivBack);
        txtCheckIn = findViewById(R.id.txtCheckIn);
        txtCheckOut = findViewById(R.id.txtCheckOut);
        btnBook = findViewById(R.id.btnBook);

        roomRef = FirebaseDatabase.getInstance().getReference().child("rooms");

        String RoomKey = getIntent().getStringExtra("RoomKey");

        roomRef.child(RoomKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String name = snapshot.child("name").getValue().toString();
                    String purl = snapshot.child("purl").getValue().toString();
                    String price = snapshot.child("price").getValue().toString();
                    String square = snapshot.child("square").getValue().toString();
                    String status =  snapshot.child("status").getValue().toString();
                    String type = snapshot.child("type").getValue().toString();

                    tvName.setText(name);
                    tvName2.setText(name);
                    tvPrice.setText(price);
                    tvSquare.setText(square);
                    tvType.setText(type);
                    Glide.with(imageRoom.getContext()).load(purl).into(imageRoom);

                    if(status.equals("0")){
                        tvStatus.setText("Empty");
                        tvStatus.setTextColor(Integer.parseInt("#66cc33"));
                    }
                    if(status.equals("1")){
                        tvStatus.setText("Booked");
                        tvStatus.setTextColor(Integer.parseInt("#FF0000"));
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        ivBack.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), UserDetailHotelActivity.class));
            finish();
        });

        txtCheckIn.setOnClickListener(v->{
            showDateTimePicker(true);
        });

        txtCheckOut.setOnClickListener(v->{
            showDateTimePicker(false);
        });
    }

    private void showDateTimePicker(boolean isCheckIn){
        Calendar calendar = Calendar.getInstance();
        int currentDay = 0;
        int currentMonth = 0;
        int currentYear = 0;
        if (isCheckIn){
            currentDay = mDayCheckIn;
            currentMonth = mMonthCheckIn;
            currentYear = mYearCheckIn;
        }else {
            currentDay = mDayCheckOut;
            currentMonth = mMonthCheckOut;
            currentYear = mYearCheckOut;
        }
        if (currentDay == 0){
            currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        }
        if (currentMonth == 0){
            currentMonth = calendar.get(Calendar.MONTH);
        }

        if (currentYear == 0){
            currentYear = calendar.get(Calendar.YEAR);
        }
        @SuppressLint("SetTextI18n")
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, year, monthOfYear, dayOfMonth) -> {
                    if (isCheckIn){
                        mDayCheckIn = dayOfMonth;
                        mMonthCheckIn = monthOfYear +1;
                        mYearCheckIn = year;
                        txtCheckIn.setText("Check in : "+dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                    }else {
                        mDayCheckOut = dayOfMonth;
                        mMonthCheckOut = monthOfYear +1;
                        mYearCheckOut = year;
                        txtCheckOut.setText("Check out : "+dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                    }
                }, currentYear, currentMonth, currentDay);
        datePickerDialog.show();
    }
}