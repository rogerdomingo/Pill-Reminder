package com.example.asifkhan.customlistview.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.asifkhan.customlistview.R;

public class MapActivity extends AppCompatActivity {


    private BottomNavigationView mBottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        // Initialize and assign variable
        mBottomNavigationView=(BottomNavigationView) findViewById(R.id.bottomNavigation);
        // Set Map Selected
        mBottomNavigationView.setSelectedItemId(R.id.menu_tracker);
        // Perform item selected listener
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.menu_panic_btn:
                        startActivity(new Intent(getApplicationContext(), PanicButtonActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.menu_home:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.menu_tracker:
                        return true;
                }
                return false;
            }
        });
    }
}
