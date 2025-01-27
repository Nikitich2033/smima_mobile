package com.example.smima.auth;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.smima.MainActivity;
import com.example.smima.R;

public class LoginFragment extends Fragment {
    private static final String TAG = "LoginFragment";
    private static final String TEST_USERNAME = "name";
    private static final String TEST_PASSWORD = "pass";

    private EditText emailInput;
    private EditText passwordInput;
    private Button loginButton;
    private TextView forgotPasswordText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                           @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        Log.d(TAG, "LoginFragment created");

        initializeViews(view);
        setupClickListeners();

        return view;
    }

    private void initializeViews(View view) {
        emailInput = view.findViewById(R.id.emailInput);
        passwordInput = view.findViewById(R.id.passwordInput);
        loginButton = view.findViewById(R.id.loginButton);
        forgotPasswordText = view.findViewById(R.id.forgotPasswordText);

        // Set hints to show test credentials
        emailInput.setHint(R.string.email + " (use: name)");
        passwordInput.setHint(R.string.password + " (use: pass)");
    }

    private void setupClickListeners() {
        loginButton.setOnClickListener(v -> {
            Log.d(TAG, "Login button clicked");
            attemptLogin();
        });
        
        forgotPasswordText.setOnClickListener(v -> handleForgotPassword());
    }

    private void attemptLogin() {
        String username = emailInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();

        Log.d(TAG, "Attempting login with username: " + username);

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(getContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Check against test credentials
        if (username.equals(TEST_USERNAME) && password.equals(TEST_PASSWORD)) {
            Log.d(TAG, "Login successful, navigating to MainActivity");
            
            // Successful login
            try {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                if (getActivity() != null) {
                    getActivity().finish();
                }
            } catch (Exception e) {
                Log.e(TAG, "Error navigating to MainActivity", e);
                Toast.makeText(getContext(), "Error navigating to main screen", Toast.LENGTH_SHORT).show();
            }
        } else {
            Log.d(TAG, "Login failed - invalid credentials");
            // Failed login
            Toast.makeText(getContext(), "Invalid credentials. Use 'name' and 'pass'", Toast.LENGTH_LONG).show();
        }
    }

    private void handleForgotPassword() {
        Log.d(TAG, "Forgot password clicked");
        Toast.makeText(getContext(), "Test credentials: name / pass", Toast.LENGTH_LONG).show();
    }
} 