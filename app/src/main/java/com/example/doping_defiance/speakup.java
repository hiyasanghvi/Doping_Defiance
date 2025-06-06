package com.example.doping_defiance;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class speakup extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speakup);

        // Initialize Firebase Auth and Database
        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("reports");  // Store in the "reports" node

        // Sign in anonymously
        signInAnonymously();

        // Set up country code spinner and submit button
        setupCountryCodeSpinner();
    }

    /**
     * Sign in anonymously to Firebase.
     */
    private void signInAnonymously() {
        mAuth.signInAnonymously().addOnCompleteListener(this, task -> {
            if (task.isSuccessful()) {
                // Authentication successful
                FirebaseUser user = mAuth.getCurrentUser();
                Log.d("Firebase", "signInAnonymously:success");
                setupSubmitButton(); // Initialize the submit button after successful authentication
            } else {
                // Authentication failed
                Log.w("Firebase", "signInAnonymously:failure", task.getException());
                Toast.makeText(speakup.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Populates the Spinner with country codes and sets up the adapter.
     */
    private void setupCountryCodeSpinner() {
        String[] countryCodes = {"+1", "+91", "+44", "+61", "+81"};
        Spinner countryCodeSpinner = findViewById(R.id.countryCodeSpinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, countryCodes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countryCodeSpinner.setAdapter(adapter);
    }

    /**
     * Sets up the Submit button to send data to Firebase.
     */
    private void setupSubmitButton() {
        Button submitButton = findViewById(R.id.submitButton);
        EditText nameEditText = findViewById(R.id.nameInput);
        EditText phoneEditText = findViewById(R.id.mobileInput);
        Spinner countryCodeSpinner = findViewById(R.id.countryCodeSpinner);
        EditText commentEditText = findViewById(R.id.commentInput);

        submitButton.setOnClickListener(v -> {
            // Get user inputs
            String name = nameEditText.getText().toString().trim();
            String phone = phoneEditText.getText().toString().trim();
            String countryCode = countryCodeSpinner.getSelectedItem().toString();
            String comment = commentEditText.getText().toString().trim();

            // Validate inputs
            if (name.isEmpty() || phone.isEmpty() || comment.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            // Combine country code with phone number
            String fullPhoneNumber = countryCode + " " + phone;

            // Create a message object
            Message message = new Message(name, fullPhoneNumber, comment);

            // Push the message to Firebase
            databaseReference.push().setValue(message).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(this, "Message submitted successfully!", Toast.LENGTH_SHORT).show();

                    // Clear input fields
                    nameEditText.setText("");
                    phoneEditText.setText("");
                    commentEditText.setText("");
                } else {
                    // Log the error and display a failure message
                    Log.e("FirebaseError", "Failed to submit message", task.getException());
                    Toast.makeText(this, "Failed to submit message: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

    /**
     * Data class for storing messages.
     */
    public static class Message {
        private String name;
        private String phone;
        private String comment;

        public Message() {
            // Default constructor required for Firebase
        }

        public Message(String name, String phone, String comment) {
            this.name = name;
            this.phone = phone;
            this.comment = comment;
        }

        // Getters and setters
        public String getName() {
            return name;
        }

        public String getPhone() {
            return phone;
        }

        public String getComment() {
            return comment;
        }
    }
}
