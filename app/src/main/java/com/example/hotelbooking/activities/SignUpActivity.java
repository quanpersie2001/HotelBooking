package com.example.hotelbooking.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hotelbooking.R;
import com.example.hotelbooking.activities.user_activities.UserMainActivity;
import com.example.hotelbooking.utils.Utils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    private EditText txtEmail, txtPassword,txtCfPassword, txtUsername;
    private Button btnSignUp;
    private FirebaseFirestore fStore;
    private ImageView ivBack;
    private TextView tvSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        progressBar = findViewById(R.id.progressBar);
        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);
        txtCfPassword = findViewById(R.id.txtCfPassword);
        txtEmail = findViewById(R.id.txtEmail);
        btnSignUp = findViewById(R.id.btnSignUp);
        ivBack = findViewById(R.id.ivBack);
        tvSignIn = findViewById(R.id.tvSignIn);
        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SignInActivity.class));
                finish();
            }
        });

        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SignInActivity.class));
                finish();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        if(Utils.isNetworkAvailable(getApplicationContext())){
            signUp();
        } else {
            Toast.makeText(this, getString(R.string.network_unavailable), Toast.LENGTH_SHORT).show();
        }
    }

    private void signUp(){
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = txtUsername.getText().toString().trim();
                final String email = txtEmail.getText().toString().trim();
                final String password = txtPassword.getText().toString().trim();

                String cfPassword = txtCfPassword.getText().toString().trim();

                if(TextUtils.isEmpty(username) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
                    if(TextUtils.isEmpty(username)){
                        txtUsername.setError(getString(R.string.name_required));
                    }

                    if(TextUtils.isEmpty(email)){
                        txtEmail.setError(getString(R.string.email_required));
                    }

                    if(password.length() < 6){
                        txtPassword.setError(getString(R.string.pass_than_6));
                    }

                    if (TextUtils.isEmpty(cfPassword) || !password.equals(cfPassword)) {
                        txtCfPassword.setError(getString(R.string.pass_not_match));
                    }
                }else if (TextUtils.isEmpty(cfPassword) || !cfPassword.equals(password)){
                    txtCfPassword.setError(getString(R.string.pass_required));
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser currentUser = mAuth.getCurrentUser();
                                assert currentUser != null;
                                String userId = currentUser.getUid();

                                //Đẩy dữ liệu user vào database
                                DocumentReference documentReference = fStore.collection("users").document(userId);
                                Map<String, Object> hashMap = new HashMap<>();
                                hashMap.put(Utils.ID, userId);
                                hashMap.put(Utils.USER_NAME, username);
                                hashMap.put(Utils.EMAIL, email);
                                hashMap.put(Utils.AVATAR, Utils.DEFAULT);
                                hashMap.put(Utils.IS_ADMIN,"FALSE");
                                hashMap.put(Utils.PHONE_NUMBER, Utils.EMPTY);
                                hashMap.put(Utils.BIRTHDAY, Utils.EMPTY);

                                documentReference.set(hashMap);

                                startActivity(new Intent(getApplicationContext(), UserMainActivity.class));
                            } else {
                                Toast.makeText(SignUpActivity.this, R.string.user_exist, Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(SignUpActivity.this, "Failed To Create Account", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        });
    }
}