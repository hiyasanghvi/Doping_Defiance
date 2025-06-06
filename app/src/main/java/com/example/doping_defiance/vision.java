package com.example.doping_defiance;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class vision extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vision);

        // Initialize ViewPager2 and set up the adapter
        ViewPager2 viewPager = findViewById(R.id.view_pager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(adapter);
    }

    private static class ViewPagerAdapter extends FragmentStateAdapter {

        public ViewPagerAdapter(AppCompatActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @Override
        public int getItemCount() {
            return 3; // Number of pages
        }

        @Override
        public Fragment createFragment(int position) {
            // Return a new fragment instance based on position
            if (position == 0) {
                return new vision1(); // Vision Page
            } else if (position == 1) {
                return new mission1(); // Mission Page
            } else if (position == 2) {
                return new whatwedo(); // What We Do Page
            } else {
                throw new IllegalArgumentException("Invalid position: " + position);
            }
        }
    }
}