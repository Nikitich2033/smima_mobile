package com.example.smima.preparation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.example.smima.R;

public class PreparationFragment extends Fragment {
    
    private TextInputEditText temperatureInput;
    private TextInputEditText volumeInput;
    private TextInputEditText timeInput;
    private MaterialButton scanBarcodeButton;
    private MaterialButton manualInputButton;
    private MaterialButton startPreparationButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_preparation, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeViews(view);
        setupClickListeners();
    }

    private void initializeViews(View view) {
        temperatureInput = view.findViewById(R.id.temperatureInput);
        volumeInput = view.findViewById(R.id.volumeInput);
        timeInput = view.findViewById(R.id.timeInput);
        scanBarcodeButton = view.findViewById(R.id.scanBarcodeButton);
        manualInputButton = view.findViewById(R.id.manualInputButton);
        startPreparationButton = view.findViewById(R.id.startPreparationButton);
    }

    private void setupClickListeners() {
        scanBarcodeButton.setOnClickListener(v -> handleScanBarcode());
        manualInputButton.setOnClickListener(v -> handleManualInput());
        startPreparationButton.setOnClickListener(v -> handleStartPreparation());
    }

    private void handleScanBarcode() {
        // TODO: Implement barcode scanning
        Toast.makeText(getContext(), "Barcode scanning coming soon", Toast.LENGTH_SHORT).show();
    }

    private void handleManualInput() {
        // TODO: Show manual input dialog
        Toast.makeText(getContext(), "Manual input coming soon", Toast.LENGTH_SHORT).show();
    }

    private void handleStartPreparation() {
        String temperature = temperatureInput.getText().toString();
        String volume = volumeInput.getText().toString();
        String time = timeInput.getText().toString();

        if (temperature.isEmpty() || volume.isEmpty() || time.isEmpty()) {
            Toast.makeText(getContext(), "Please fill in all preparation settings", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO: Implement actual preparation start
        String message = String.format("Starting preparation: %s°C, %sml, %s", temperature, volume, time);
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }
} 