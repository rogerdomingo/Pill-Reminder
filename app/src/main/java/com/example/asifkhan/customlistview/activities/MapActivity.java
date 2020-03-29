package com.example.asifkhan.customlistview.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.asifkhan.customlistview.R;
import com.example.asifkhan.customlistview.SQLiteHelpers.SQLiteDBHelper;
import com.example.asifkhan.customlistview.models.User;

public class MapActivity extends AppCompatActivity {


    private BottomNavigationView mBottomNavigationView;

    private User user;
    private SQLiteDBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        db = new SQLiteDBHelper(this);
        String userEmail = getIntent().getStringExtra("USER_EMAIL");
        user = db.getUser(userEmail);

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
                        Intent intent = new Intent(getApplicationContext(), PanicButtonActivity.class);
                        intent.putExtra("USER_EMAIL", user.getEmail());
                        startActivityForResult(intent, 0);
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.menu_home:
                        Intent intent2 = new Intent(getApplicationContext(), MainActivity.class);
                        intent2.putExtra("USER_EMAIL", user.getEmail());
                        startActivityForResult(intent2, 0);
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
