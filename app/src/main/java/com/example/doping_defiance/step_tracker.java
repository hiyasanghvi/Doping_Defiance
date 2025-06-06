package com.example.doping_defiance;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class step_tracker extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_tracker);

        // Initialize UI components
        ProgressBar progressCircle = findViewById(R.id.progress_circle);
        TextView stepCount = findViewById(R.id.step_count);
        TextView goalText = findViewById(R.id.goal_text);
        TextView calories = findViewById(R.id.calories);
        TextView distance = findViewById(R.id.distance);
        TextView time = findViewById(R.id.time);

        // Set data (dummy values)
        progressCircle.setProgress(62); // Progress percentage
        stepCount.setText("6,162");
        goalText.setText("Goal 10,000");
        calories.setText("204 kcal");
        distance.setText("4.5 km");
        time.setText("1:25 h");
    }
}