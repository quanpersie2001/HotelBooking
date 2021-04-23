package com.example.hotelbooking.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hotelbooking.R;
import com.example.hotelbooking.model.Admin;
import com.example.hotelbooking.utils.Utils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class SignInActivity extends AppCompatActivity {
    private EditText txtEmail;
    private EditText txtPassword;
    private Button btnSignIn;
    private TextView tvForgot;
    private TextView tvSignUp;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    private Context context;
    FirebaseFirestore fStore;

    @Override
    protected void onStart() {
        super.onStart();
        mAuth = FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser() != null){
            Intent intent = new Intent(context, UserMainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPassword);
        tvForgot = findViewById(R.id.tvForgot);
        tvSignUp = findViewById(R.id.tvSignUp);
        btnSignIn = findViewById(R.id.btnSignIn);
        progressBar = findViewById(R.id.progressBar);

        context = getApplicationContext();
        fStore = FirebaseFirestore.getInstance();
    }

    @Override
    protected void onResume() {
        super.onResume();

        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignUp();
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Utils.isNetworkAvailable(context)){
                    signIn();
                }else{
                    Toast.makeText(context, R.string.network_unavailable, Toast.LENGTH_SHORT).show();
                }
            }
        });

        tvForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ResetPasswordActivity.class);
                startActivity(intent);
            }
        });

    }

    private void openSignUp(){
        Intent intent = new Intent(context, SignUpActivity.class);
        startActivity(intent);
    }
    private void signIn(){
        String email = txtEmail.getText().toString().trim();
        String password = txtPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
            if(TextUtils.isEmpty(email)){
                txtEmail.setError(getString(R.string.email_required));
            }

            if(TextUtils.isEmpty(password)){
                txtPassword.setError(getString(R.string.pass_required));
            }
        }else if(password.length() < 6){
            txtPassword.setError(getString(R.string.pass_required));
        }else{
            progressBar.setVisibility(View.VISIBLE);
            mAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    Toast.makeText(context, "Sign In Successfully", Toast.LENGTH_SHORT).show();
                    checkAdmin(authResult.getUser().getUid());
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(context, "Failed To Sign In", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
            });
        }
    }

    private void checkAdmin(String uid) {

        DocumentReference documentReference = fStore.collection("users").document(uid);

        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Log.d(Utils.TAG, "onSuccess" + documentSnapshot.getData());

                if(documentSnapshot.getString("isAdmin") != null) {
                    startActivity(new Intent(context, AdminMainActivity.class));
                    finish();
                }
                if(documentSnapshot.getString("isUser") != null){
                    startActivity(new Intent(context, AdminMainActivity.class));
                    finish();
                }
            }
        });

    }
}