package com.example.smima.device;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.smima.R;

public class DeviceFragment extends Fragment {
    
    private TextView waterLevelStatus;
    private TextView wifiStatus;
    private TextView bottleStatus;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_device, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeViews(view);
        updateDeviceStatus();
    }

    private void initializeViews(View view) {
        waterLevelStatus = view.findViewById(R.id.waterLevelStatus);
        wifiStatus = view.findViewById(R.id.wifiStatus);
        bottleStatus = view.findViewById(R.id.bottleStatus);
    }

    private void updateDeviceStatus() {
        // TODO: Implement actual device status checking
        waterLevelStatus.setText(getString(R.string.water_level) + ": " + getString(R.string.status_ok));
        wifiStatus.setText(getString(R.string.wifi_signal) + ": " + getString(R.string.status_connected));
        bottleStatus.setText(getString(R.string.bottle_present) + ": " + getString(R.string.status_yes));
    }

    @Override
    public void onResume() {
        super.onResume();
        updateDeviceStatus();
    }
} 