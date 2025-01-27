package com.example.smima.auth;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.smima.R;

public class SignupFragment extends Fragment {

    private EditText firstNameInput;
    private EditText lastNameInput;
    private EditText emailInput;
    private EditText confirmEmailInput;
    private EditText passwordInput;
    private EditText phoneInput;
    private EditText countryInput;
    private CheckBox termsCheckbox;
    private Button signupButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                           @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signup, container, false);

        initializeViews(view);
        setupClickListeners();

        return view;
    }

    private void initializeViews(View view) {
        firstNameInput = view.findViewById(R.id.firstNameInput);
        lastNameInput = view.findViewById(R.id.lastNameInput);
        emailInput = view.findViewById(R.id.emailInput);
        confirmEmailInput = view.findViewById(R.id.confirmEmailInput);
        passwordInput = view.findViewById(R.id.passwordInput);
        phoneInput = view.findViewById(R.id.phoneInput);
        countryInput = view.findViewById(R.id.countryInput);
        termsCheckbox = view.findViewById(R.id.termsCheckbox);
        signupButton = view.findViewById(R.id.signupButton);
    }

    private void setupClickListeners() {
        signupButton.setOnClickListener(v -> attemptSignup());
    }

    private void attemptSignup() {
        if (!validateInputs()) {
            return;
        }

        // TODO: Implement actual signup logic
        // For now, simulate successful signup
        Bundle result = new Bundle();
        result.putBoolean("success", true);
        getParentFragmentManager().setFragmentResult("auth_success", result);
    }

    private boolean validateInputs() {
        String firstName = firstNameInput.getText().toString().trim();
        String lastName = lastNameInput.getText().toString().trim();
        String email = emailInput.getText().toString().trim();
        String confirmEmail = confirmEmailInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();

        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(getContext(), "Please fill in all required fields", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!email.equals(confirmEmail)) {
            Toast.makeText(getContext(), "Emails do not match", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!termsCheckbox.isChecked()) {
            Toast.makeText(getContext(), "Please accept the Terms of Service", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
} 