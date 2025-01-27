package com.example.smima;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    // UI Components
    private TextInputEditText temperatureInput;
    private TextInputEditText volumeInput;
    private TextInputEditText timeInput;
    private TextView waterLevelStatus;
    private TextView wifiStatus;
    private TextView bottleStatus;
    private MaterialButton scanBarcodeButton;
    private MaterialButton manualInputButton;
    private MaterialButton startPreparationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            Log.d(TAG, "Setting content view");
            setContentView(R.layout.activity_main);

            setupToolbar();
            initializeViews();
            setupClickListeners();
            updateDeviceStatus();

        } catch (Exception e) {
            Log.e(TAG, "Error in onCreate", e);
            Toast.makeText(this, "Error initializing main screen", Toast.LENGTH_LONG).show();
        }
    }

    private void setupToolbar() {
        try {
            Toolbar toolbar = findViewById(R.id.toolbar);
            if (toolbar != null) {
                setSupportActionBar(toolbar);
                if (getSupportActionBar() != null) {
                    getSupportActionBar().setTitle("Smima Home");
                }
            } else {
                Log.e(TAG, "Toolbar not found in layout");
            }
        } catch (Exception e) {
            Log.e(TAG, "Error setting up toolbar", e);
        }
    }

    private void initializeViews() {
        temperatureInput = findViewById(R.id.temperatureInput);
        volumeInput = findViewById(R.id.volumeInput);
        timeInput = findViewById(R.id.timeInput);
        waterLevelStatus = findViewById(R.id.waterLevelStatus);
        wifiStatus = findViewById(R.id.wifiStatus);
        bottleStatus = findViewById(R.id.bottleStatus);
        scanBarcodeButton = findViewById(R.id.scanBarcodeButton);
        manualInputButton = findViewById(R.id.manualInputButton);
        startPreparationButton = findViewById(R.id.startPreparationButton);
    }

    private void setupClickListeners() {
        scanBarcodeButton.setOnClickListener(v -> handleScanBarcode());
        manualInputButton.setOnClickListener(v -> handleManualInput());
        startPreparationButton.setOnClickListener(v -> handleStartPreparation());
    }

    private void handleScanBarcode() {
        // TODO: Implement barcode scanning
        Toast.makeText(this, "Barcode scanning coming soon", Toast.LENGTH_SHORT).show();
    }

    private void handleManualInput() {
        // TODO: Show manual input dialog
        Toast.makeText(this, "Manual input coming soon", Toast.LENGTH_SHORT).show();
    }

    private void handleStartPreparation() {
        String temperature = temperatureInput.getText().toString();
        String volume = volumeInput.getText().toString();
        String time = timeInput.getText().toString();

        if (temperature.isEmpty() || volume.isEmpty() || time.isEmpty()) {
            Toast.makeText(this, "Please fill in all preparation settings", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO: Implement actual preparation start
        String message = String.format("Starting preparation: %s°C, %sml, %s", temperature, volume, time);
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    private void updateDeviceStatus() {
        // TODO: Implement actual device status checking
        waterLevelStatus.setText(getString(R.string.water_level) + ": " + getString(R.string.status_ok));
        wifiStatus.setText(getString(R.string.wifi_signal) + ": " + getString(R.string.status_connected));
        bottleStatus.setText(getString(R.string.bottle_present) + ": " + getString(R.string.status_yes));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            // TODO: Navigate to settings activity
            Toast.makeText(this, "Settings coming soon", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateDeviceStatus();
        Log.d(TAG, "MainActivity resumed");
    }
}