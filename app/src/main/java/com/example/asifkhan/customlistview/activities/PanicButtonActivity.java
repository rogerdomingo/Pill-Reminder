package com.example.asifkhan.customlistview.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.asifkhan.customlistview.R;
import com.example.asifkhan.customlistview.SQLiteHelpers.SQLiteDBHelper;
import com.example.asifkhan.customlistview.models.User;


public class PanicButtonActivity extends AppCompatActivity {

    private static final int REQUEST_CALL = 1;
    private AppCompatButton panicButton;
    private BottomNavigationView mBottomNavigationView;

    private User user;
    private SQLiteDBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panic_button);

        db = new SQLiteDBHelper(this);
        String userEmail = getIntent().getStringExtra("USER_EMAIL");
        user = db.getUser(userEmail);

        // Initialize and assign variable
        mBottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigation);
        // Set Emergency Selected
        mBottomNavigationView.setSelectedItemId(R.id.menu_panic_btn);
        // Perform item selected listener
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.menu_panic_btn:
                        return true;
                    case R.id.menu_home:
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.putExtra("USER_EMAIL", user.getEmail());
                        startActivityForResult(intent, 0);
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.menu_tracker:
                        Intent intent2 = new Intent(getApplicationContext(), MapActivity.class);
                        intent2.putExtra("USER_EMAIL", user.getEmail());
                        startActivityForResult(intent2, 0);
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

        panicButton = (AppCompatButton) findViewById(R.id.panic_btn);
        panicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makePhoneCall();
            }
        });
    }

    private void makePhoneCall() {
        String phone_number = "+34 628153187";

        if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(PanicButtonActivity.this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
        } else {
            String dial = "tel:" + phone_number;
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_CALL) {
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makePhoneCall();
            }
        } else {
            Toast.makeText(this, "Permission DENIED", Toast.LENGTH_LONG).show();
        }
    }
}
