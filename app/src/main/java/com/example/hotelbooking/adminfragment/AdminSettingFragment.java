package com.example.hotelbooking.adminfragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.hotelbooking.R;
import com.example.hotelbooking.activities.ShowProfileActivity;
import com.example.hotelbooking.activities.SignInActivity;
import com.example.hotelbooking.model.Admin;
import com.example.hotelbooking.model.User;
import com.example.hotelbooking.utils.Utils;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Objects;

public class AdminSettingFragment extends Fragment {

    Button btnLogout, btnProfile, btnBooked, btnAbout, btnCoupon;
    ImageView bigAvatar;
    TextView tvUsername;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    Context context;
    private Uri mAvtUri;
    private StorageTask mUploadTask;
    private FirebaseUser mCurrentUser;
    private StorageReference mStorageReference;




    private static final int OPEN_AVT_REQUEST_CODE = 1;

    public AdminSettingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_admin_setting, container, false);
        btnCoupon = (Button) view.findViewById(R.id.btnCoupon);
        btnLogout = (Button) view.findViewById(R.id.btnLogout);
        btnProfile = (Button) view.findViewById(R.id.btnProfile);
        btnAbout = (Button) view.findViewById(R.id.btnAbout);
        btnBooked = (Button) view.findViewById(R.id.btnBooked);
        bigAvatar = (ImageView) view.findViewById(R.id.bigAvatar);
        tvUsername = (TextView) view.findViewById(R.id.tvUsername);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        context = container.getContext();

        mCurrentUser = fAuth.getCurrentUser();
        mStorageReference = FirebaseStorage.getInstance().getReference(Utils.UPLOADS);


        getAdmin();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        String uid = mCurrentUser.getUid();

        DocumentReference df = fStore.collection("users").document(uid);

        df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                String username = documentSnapshot.getString(Utils.USER_NAME);
                tvUsername.setText(username);
            }
        });
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();//logout
                startActivity(new Intent(getActivity(), SignInActivity.class));
            }
        });

        bigAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isNetworkAvailable(getActivity())) {
                    setAvatar();
                } else {
                    Toast.makeText(getActivity(), getString(R.string.network_unavailable), Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ShowProfileActivity.class));
            }
        });
    }

    private void setAvatar() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, OPEN_AVT_REQUEST_CODE);
    }
    private void getAdmin() {
        final String id = mCurrentUser.getUid();
        FirebaseDatabase.getInstance().getReference(Utils.USERS).child(id)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Admin admin = snapshot.getValue(Admin.class);
                        if (admin != null) {
                            if (Objects.equals(admin.getAdminID(), id)) {
                                if (!admin.getAvatar().equals(Utils.DEFAULT)) {
                                    Glide.with(getActivity()).load(admin.getAvatar()).into(bigAvatar);
                                }
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    private String getFileExtension(Uri uri) {
        ContentResolver contentResolver = getActivity().getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    private void uploadAvatar() {
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(getString(R.string.uploading));
        progressDialog.show();

        if (mAvtUri != null) {
            final StorageReference storageReference = mStorageReference.child(System.currentTimeMillis()
                    + "." + getFileExtension(mAvtUri));
            mUploadTask = storageReference.putFile(mAvtUri);
            mUploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }
                    return storageReference.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()) {
                        String uri = task.getResult().toString();
                        String id = mCurrentUser.getUid();

                        HashMap<String, Object> map = new HashMap<>();
                        map.put(Utils.AVATAR, uri);
                        FirebaseDatabase.getInstance().getReference(Utils.USERS).child(id).updateChildren(map);
                    } else {
                        Toast.makeText(context, R.string.failed, Toast.LENGTH_SHORT).show();
                    }
                    progressDialog.dismiss();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            });
        } else {
            Toast.makeText(getActivity(), R.string.no_image_selected, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == OPEN_AVT_REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            mAvtUri = data.getData();
            bigAvatar.setImageURI(mAvtUri);

            if (mUploadTask != null && mUploadTask.isInProgress()) {
                Toast.makeText(getActivity(), R.string.upload_in_progress, Toast.LENGTH_SHORT).show();
            } else {
                uploadAvatar();
            }
        }
    }

}