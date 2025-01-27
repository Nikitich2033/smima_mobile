package com.example.smima.auth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import com.example.smima.MainActivity;
import com.example.smima.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class AuthActivity extends AppCompatActivity {
    private ViewPager2 viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);

        AuthPagerAdapter pagerAdapter = new AuthPagerAdapter(this);
        viewPager.setAdapter(pagerAdapter);

        new TabLayoutMediator(tabLayout, viewPager,
            (tab, position) -> tab.setText(position == 0 ? R.string.login : R.string.signup)
        ).attach();

        // Handle successful authentication
        getSupportFragmentManager().setFragmentResultListener("auth_success", this,
            (requestKey, result) -> {
                Intent intent = new Intent(AuthActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            });
    }
} 