package com.example.smima.auth;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class AuthPagerAdapter extends FragmentStateAdapter {

    public AuthPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // Return Login fragment for position 0, Signup fragment for position 1
        return position == 0 ? new LoginFragment() : new SignupFragment();
    }

    @Override
    public int getItemCount() {
        return 2; // Two pages: Login and Signup
    }
} 