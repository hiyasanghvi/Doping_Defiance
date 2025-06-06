package com.example.doping_defiance;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class water extends AppCompatActivity {

    private TextView remainingText;
    private TextView intakeText;
    private View waterOverlay;

    private float totalIntake = 41.3f; // Current intake in ounces
    private final float goal = 64f;   // Daily hydration goal (ounces)
    private final int overlayMaxHeight = 400; // ImageView height in dp

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water);

        remainingText = findViewById(R.id.remainingText);
        intakeText = findViewById(R.id.intakeText);
        waterOverlay = findViewById(R.id.waterOverlay);

        Button button8oz = findViewById(R.id.button8oz);
        Button button6oz = findViewById(R.id.button6oz);
        Button button14oz = findViewById(R.id.button14oz);
        Button button16oz = findViewById(R.id.button16oz);

        // Initialize UI
        updateUI();

        // Set button listeners
        button8oz.setOnClickListener(v -> addIntake(8f));
        button6oz.setOnClickListener(v -> addIntake(6f));
        button14oz.setOnClickListener(v -> addIntake(14f));
        button16oz.setOnClickListener(v -> addIntake(16.9f));
    }

    private void addIntake(float amount) {
        totalIntake = Math.min(totalIntake + amount, goal); // Prevent exceeding the goal
        updateUI();
    }

    private void updateUI() {
        // Calculate progress
        float progress = totalIntake / goal;
        float remaining = goal - totalIntake;

        // Update texts
        intakeText.setText(String.format("Intake: %.1foz", totalIntake));
        remainingText.setText(String.format("Remaining: %.1foz", remaining));

        // Adjust the height of the water overlay
        int newHeight = (int) (overlayMaxHeight * progress);
        waterOverlay.getLayoutParams().height = newHeight;
        waterOverlay.requestLayout();

        // Gradually darken color as progress increases
        int color = interpolateColor(0xFFADD8E6, 0xFF00008B, progress); // Light blue to dark blue
        waterOverlay.setBackgroundColor(color);
    }

    private int interpolateColor(int startColor, int endColor, float fraction) {
        int startA = (startColor >> 24) & 0xff;
        int startR = (startColor >> 16) & 0xff;
        int startG = (startColor >> 8) & 0xff;
        int startB = startColor & 0xff;

        int endA = (endColor >> 24) & 0xff;
        int endR = (endColor >> 16) & 0xff;
        int endG = (endColor >> 8) & 0xff;
        int endB = endColor & 0xff;

        int a = (int) (startA + fraction * (endA - startA));
        int r = (int) (startR + fraction * (endR - startR));
        int g = (int) (startG + fraction * (endG - startG));
        int b = (int) (startB + fraction * (endB - startB));

        return (a << 24) | (r << 16) | (g << 8) | b;
    }
}