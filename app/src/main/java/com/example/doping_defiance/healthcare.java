package com.example.doping_defiance;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;

public class healthcare extends AppCompatActivity {

    private TextView distanceTextView;
    private TextView stepsTextView;
    private TextView caloriesTextView;
    private TextView waterTextView;
    private TextView sleepTextView;
    private TextView oxygenTextView;
    private TextView heartRateTextView;
    private CalendarView calendarView;
    private Button updateButton;

    private int waterIntake = 0; // Variable to track water intake

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_healthcare);

        // Initialize views
        distanceTextView = findViewById(R.id.distanceTextView);
        stepsTextView = findViewById(R.id.steps);
        caloriesTextView = findViewById(R.id.caloriesTextView);
        waterTextView = findViewById(R.id.waterTextView);
        sleepTextView = findViewById(R.id.sleepTextView);
        heartRateTextView = findViewById(R.id.HeartRate);
        calendarView = findViewById(R.id.calendarView);
        updateButton = findViewById(R.id.updateButton);
        TextView nutritionTextView = findViewById(R.id.nutrition);
        TextView injuryTextView = findViewById(R.id.injury);

        // Set initial values
        setInitialValues();

        // Make waterTextView clickable
        waterTextView.setClickable(true);

        // Make stepsTextView clickable
        stepsTextView.setClickable(true);

        // Make heartRateTextView clickable
        heartRateTextView.setClickable(true);
        TextView mentalHealthTextView = findViewById(R.id.mental);
        mentalHealthTextView.setOnClickListener(v -> {
            String url = "https://share.chatling.ai/s/EFJcRA1PdomPZBz"; // Replace with your URL
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        });
        // CalendarView date change listener
        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            String date = dayOfMonth + "/" + (month + 1) + "/" + year;
            // Handle date selection if needed
        });

        // Update button click listener
        updateButton.setOnClickListener(v -> updateData());

        // Add click listener to waterTextView
        waterTextView.setOnClickListener(v -> {
            waterIntake += 25; // Increment water intake by 25ml
            waterTextView.setText("Water Intake: " + waterIntake + " ml");
            Toast.makeText(healthcare.this, "Water intake increased by 25ml!", Toast.LENGTH_SHORT).show();

            // Navigate to a new activity
            Intent intent = new Intent(healthcare.this, water.class);
            intent.putExtra("waterIntake", waterIntake);
            startActivity(intent);
        });

        // Add click listener to stepsTextView
        stepsTextView.setOnClickListener(v -> {
            // Get the steps count
            String stepsCount = stepsTextView.getText().toString();

            // Navigate to a new activity
            Intent intent = new Intent(healthcare.this, step_tracker.class);
            intent.putExtra("stepsCount", stepsCount);
            startActivity(intent);
        });

        // Add click listener to heartRateTextView
        heartRateTextView.setOnClickListener(v -> {
            // Get the heart rate
            String heartRate = heartRateTextView.getText().toString();

            // Navigate to a new activity
            Intent intent = new Intent(healthcare.this, heartt.class);
            intent.putExtra("heartRate", heartRate);
            startActivity(intent);
        });

        // Add click listener to nutritionTextView
        nutritionTextView.setOnClickListener(v -> openNutritionPdf());

        // Add click listener to injuryTextView
        injuryTextView.setOnClickListener(v -> openInjuryPpt());
    }

    private void setInitialValues() {
        // Set default values for the dashboard
        distanceTextView.setText("Distance Walked: 0 km");
        stepsTextView.setText("Steps Count: 0");
        caloriesTextView.setText("Calories Burned: 0 kcal");
        waterTextView.setText("Water Intake: 0 ml");
        sleepTextView.setText("Sleep Cycle: 0 hours");
        heartRateTextView.setText("Heart Rate: 00 Hz");
    }

    private void updateData() {
        // Use random values to simulate the data update
        Random random = new Random();
        int randomValue = random.nextInt(100) + 1;

        // Update the TextViews with random data
        distanceTextView.setText("Distance Walked: " + (randomValue * 0.1) + " km");
        stepsTextView.setText("Steps Count: " + (randomValue * 10));
        caloriesTextView.setText("Calories Burned: " + (randomValue * 5) + " kcal");
        waterTextView.setText("Water Intake: " + waterIntake + " ml"); // Retain the manually updated water intake
        sleepTextView.setText("Sleep Cycle: " + (randomValue % 10) + " hours");
        heartRateTextView.setText("Heart Rate: " + (60 + randomValue % 40) + " Hz");
    }

    private void openNutritionPdf() {
        try {
            // Copy the PDF file to the app's internal storage
            File pdfDir = new File(getFilesDir(), "pdfs");
            if (!pdfDir.exists()) {
                pdfDir.mkdirs();
            }
            File pdfFile = new File(pdfDir, "nutrition_guide.pdf");
            if (!pdfFile.exists()) {
                InputStream inputStream = getResources().openRawResource(R.raw.pdfnutrition);
                OutputStream outputStream = new FileOutputStream(pdfFile);
                byte[] buffer = new byte[1024];
                int length;
                while ((length = inputStream.read(buffer)) > 0) {
                    outputStream.write(buffer, 0, length);
                }
                outputStream.close();
                inputStream.close();
            }

            // Create a URI with FileProvider
            Uri pdfUri = FileProvider.getUriForFile(this, getPackageName() + ".fileprovider", pdfFile);

            // Open the PDF file with an intent
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(pdfUri, "application/pdf");
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            } else {
                Toast.makeText(this, "No PDF viewer found to open the file.", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Error opening PDF: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void openInjuryPpt() {
        try {
            // Copy the PPT file to the app's internal storage
            File pptDir = new File(getFilesDir(), "ppts");
            if (!pptDir.exists()) {
                pptDir.mkdirs();
            }
            File pptFile = new File(pptDir, "injury_guide.pptx");
            if (!pptFile.exists()) {
                InputStream inputStream = getResources().openRawResource(R.raw.injury);
                OutputStream outputStream = new FileOutputStream(pptFile);
                byte[] buffer = new byte[1024];
                int length;
                while ((length = inputStream.read(buffer)) > 0) {
                    outputStream.write(buffer, 0, length);
                }
                outputStream.close();
                inputStream.close();
            }

            // Create a URI with FileProvider
            Uri pptUri = FileProvider.getUriForFile(this, getPackageName() + ".fileprovider", pptFile);

            // Open the PPT file with an intent
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(pptUri, "application/vnd.openxmlformats-officedocument.presentationml.presentation");
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            } else {
                Toast.makeText(this, "No PowerPoint viewer found to open the file.", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Error opening PPT: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}