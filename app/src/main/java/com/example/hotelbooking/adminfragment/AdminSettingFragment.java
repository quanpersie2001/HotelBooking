package com.example.hotelbooking.adminfragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hotelbooking.R;
import com.example.hotelbooking.activities.SignInActivity;
import com.example.hotelbooking.utils.Utils;
import com.google.firebase.auth.FirebaseAuth;

public class AdminSettingFragment extends Fragment {

    Button btnLogout;
    ImageView bigAvatar;
    TextView tvEditProfile, tvChangePass, tvListBooking, tvAbout;
    private static final int OPEN_AVT_REQUEST_CODE = 1;

    public AdminSettingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_setting, container, false);
        btnLogout = (Button) view.findViewById(R.id.btnLogout);
        tvEditProfile = (TextView) view.findViewById(R.id.tvEditProfile);
        tvChangePass = (TextView) view.findViewById(R.id.tvChagePass);
        tvAbout = (TextView) view.findViewById(R.id.tvAbout);
        tvListBooking = (TextView) view.findViewById(R.id.tvListBooking);
        bigAvatar = (ImageView) view.findViewById(R.id.bigAvatar);


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

        return view;
    }

    private void setAvatar() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, OPEN_AVT_REQUEST_CODE);
    }

}