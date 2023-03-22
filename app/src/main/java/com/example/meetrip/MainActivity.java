package com.example.meetrip;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity{
    RecommendFragment recFragment;
    CommunityFragment comFragment;
    MypageFragment mypFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recFragment = new RecommendFragment();
        comFragment = new CommunityFragment();
        mypFragment = new MypageFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.container, recFragment).commit();
        BottomNavigationView bottom_menu = findViewById(R.id.bottom_menu);
        bottom_menu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()) {
                    case R.id.recommend:
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, recFragment).commit();
                    return true;
                    case R.id.community:
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, comFragment).commit();
                    return true;
                    case R.id.mypage:
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, mypFragment).commit();
                    return true;
                }
                return false;
            }
        });
    }
}