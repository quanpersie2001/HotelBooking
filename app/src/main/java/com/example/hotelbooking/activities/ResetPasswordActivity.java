package com.example.hotelbooking.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hotelbooking.R;

public class ResetPasswordActivity extends AppCompatActivity {

    private ImageView ivBack;
    private Button btnSend;
    private TextView txtResetPassword;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        ivBack = findViewById(R.id.ivBack);
        btnSend = findViewById(R.id.btnSend);
        txtResetPassword = findViewById(R.id.txtResetPassword);

        context = getApplicationContext();

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SignInActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}