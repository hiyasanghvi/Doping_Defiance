package com.example.doping_defiance;

import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.GestureDetectorCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private GestureDetectorCompat gestureObject;
    private ImageView swipeIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Find your swipe indicator view
        swipeIndicator = findViewById(R.id.swipe_indicator);

        // Apply window insets for proper padding
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize the gesture detector
        gestureObject = new GestureDetectorCompat(this, new LearnGesture());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.gestureObject.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    // Gesture listener class for detecting swipe
    class LearnGesture extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(@Nullable MotionEvent e1, @NonNull MotionEvent e2, float velocityX, float velocityY) {
            // Detect swipe right
            if (e2.getX() > e1.getX()) {
                showSwipeIndicator(); // Show the swipe action visual indicator
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                finish();
                startActivity(intent);
            }
            // Detect swipe left (optional, if you want to handle it)
            else if (e2.getX() < e1.getX()) {
                // Handle left swipe here (optional)
            }
            return true;
        }
    }

    // Function to show the swipe indicator
    private void showSwipeIndicator() {
        swipeIndicator.setVisibility(View.VISIBLE);  // Make the swipe indicator visible
        swipeIndicator.animate().alpha(0).setDuration(500).start();  // Fade it out after 500ms
    }
}
