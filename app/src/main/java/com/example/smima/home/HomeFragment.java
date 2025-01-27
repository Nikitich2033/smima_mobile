package com.example.smima.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.smima.R;
import com.example.smima.databinding.FragmentHomeBinding;
import com.google.android.material.button.MaterialButton;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    
    private FragmentHomeBinding binding;
    private RecentActivityAdapter activityAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeViews();
        setupClickListeners();
        setupRecyclerView();
        updateDeviceStatus();
        loadRecentActivity();
    }

    private void initializeViews() {
        // Initialize any additional views if needed
    }

    private void setupClickListeners() {
        binding.quickStartButton.setOnClickListener(v -> handleQuickStart());
        binding.lastUsedButton.setOnClickListener(v -> handleLastUsed());
        binding.viewDetailsButton.setOnClickListener(v -> handleViewDetails());
        binding.viewAllActivityButton.setOnClickListener(v -> handleViewAllActivity());
    }

    private void setupRecyclerView() {
        binding.recentActivityRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        activityAdapter = new RecentActivityAdapter(new ArrayList<>());
        binding.recentActivityRecyclerView.setAdapter(activityAdapter);
    }

    private void loadRecentActivity() {
        List<RecentActivityAdapter.ActivityItem> mockData = new ArrayList<>();
        mockData.add(new RecentActivityAdapter.ActivityItem(
            "Formula Prepared",
            "180ml at 37°C",
            "2 hours ago"
        ));
        mockData.add(new RecentActivityAdapter.ActivityItem(
            "Device Maintenance",
            "Water filter replaced",
            "Yesterday"
        ));
        mockData.add(new RecentActivityAdapter.ActivityItem(
            "Formula Prepared",
            "120ml at 37°C",
            "Yesterday"
        ));
        activityAdapter.updateItems(mockData);
    }

    private void updateDeviceStatus() {
        binding.deviceStatusSummary.setText("All systems operational");
    }

    private void handleQuickStart() {
        Toast.makeText(getContext(), "Quick Start coming soon", Toast.LENGTH_SHORT).show();
    }

    private void handleLastUsed() {
        Toast.makeText(getContext(), "Last Used Settings coming soon", Toast.LENGTH_SHORT).show();
    }

    private void handleViewDetails() {
        Toast.makeText(getContext(), "Device Details coming soon", Toast.LENGTH_SHORT).show();
    }

    private void handleViewAllActivity() {
        Toast.makeText(getContext(), "Activity History coming soon", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
} 