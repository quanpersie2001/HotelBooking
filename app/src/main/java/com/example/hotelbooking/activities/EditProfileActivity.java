package com.example.hotelbooking.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.hotelbooking.R;
import com.example.hotelbooking.model.User;
import com.example.hotelbooking.utils.Utils;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class EditProfileActivity extends AppCompatActivity {

    EditText txtUsername, txtEmail, txtBirthday, txtPhone, txtFullName;
    ImageView ivBack, ivAvatar;
    Button btnSave;

    FirebaseFirestore fStore;
    FirebaseAuth fAuth;
    FirebaseUser mCurrentUser;

    DocumentReference df;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        txtUsername = findViewById(R.id.txtUsername);
        txtEmail = findViewById(R.id.txtEmail);
        txtBirthday = findViewById(R.id.txtBirthday);
        txtPhone = findViewById(R.id.txtPhone);
        txtFullName = findViewById(R.id.txtFullName);
        ivBack = findViewById(R.id.ivBack);
        ivAvatar = findViewById(R.id.ivAvatar);
        btnSave = findViewById(R.id.btnSave);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        mCurrentUser = fAuth.getCurrentUser();


        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        df = fStore.collection("users").document(mCurrentUser.getUid());

        df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                String username = documentSnapshot.getString(Utils.USER_NAME);
                String phone = documentSnapshot.getString(Utils.PHONE_NUMBER);
                String birthday = documentSnapshot.getString(Utils.BIRTHDAY);
                String fullname = documentSnapshot.getString(Utils.FULL_Name);
                String email = documentSnapshot.getString(Utils.EMAIL);

                txtUsername.setText(username);
                txtFullName.setText(fullname);
                txtBirthday.setText(birthday);
                txtEmail.setText(email);
                txtPhone.setText(phone);

            }
        });

    }

    private void update() {
        String email = txtEmail.getText().toString().trim();

        mCurrentUser.updateEmail(email).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                df = fStore.collection("users").document(mCurrentUser.getUid());
                Map<String, Object> update = new HashMap<>();
                update.put(Utils.EMAIL, email);
                update.put(Utils.USER_NAME, txtUsername.getText().toString().trim());
                update.put(Utils.PHONE_NUMBER, txtPhone.getText().toString().trim());
                update.put(Utils.BIRTHDAY, txtBirthday.getText().toString().trim());
                update.put(Utils.FULL_Name, txtFullName.getText().toString().trim());

                df.update(update).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(EditProfileActivity.this, getString(R.string.profile_is_changed), Toast.LENGTH_SHORT).show();
                        onBackPressed();
                    }
                });
                Toast.makeText(EditProfileActivity.this, getString(R.string.email_is_changed), Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(EditProfileActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

                

    }
}