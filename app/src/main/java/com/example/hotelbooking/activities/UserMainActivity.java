package com.example.hotelbooking.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.hotelbooking.R;
import com.example.hotelbooking.userfragment.UserHeartFragment;
import com.example.hotelbooking.userfragment.UserHomeFragment;
import com.example.hotelbooking.userfragment.UserLocationFragment;
import com.example.hotelbooking.userfragment.UserSettingFragment;

public class UserMainActivity extends AppCompatActivity {

    MeowBottomNavigation bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);

        bottomNavigation = findViewById(R.id.bottom_navigation);

        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.ic_home));
        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.ic_location));
        bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.ic_heart));
        bottomNavigation.add(new MeowBottomNavigation.Model(4, R.drawable.ic_user));

        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                Fragment fragment = null;

                switch (item.getId()){
                    case 1:
                        fragment = new UserHomeFragment();
                        break;
                    case 2:
                        fragment = new UserLocationFragment();
                        break;
                    case 3:
                        fragment = new UserHeartFragment();
                        break;
                    case 4:
                        fragment = new UserSettingFragment();
                        break;
                }
                loadFragment(fragment);
            }
        });

        bottomNavigation.show(1, true);
        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
            }
        });

        bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
            }
        });

    }

    private void loadFragment(Fragment fragment) {

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout, fragment)
                .commit();

    }
}