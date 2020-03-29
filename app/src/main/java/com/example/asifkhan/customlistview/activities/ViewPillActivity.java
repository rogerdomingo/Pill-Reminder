package com.example.asifkhan.customlistview.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asifkhan.customlistview.R;
import com.example.asifkhan.customlistview.SQLiteHelpers.SQLiteDBHelper;
import com.example.asifkhan.customlistview.models.Pill;
import com.example.asifkhan.customlistview.models.User;

public class ViewPillActivity extends AppCompatActivity {

    private Pill pill;

    EditText textName;
    EditText textDate;
    AppCompatButton deleteButton;
    AppCompatButton cancelButton;

    private SQLiteDBHelper db;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpill);

        db = new SQLiteDBHelper(this);
        String userEmail = getIntent().getStringExtra("USER_EMAIL");
        user = db.getUser(userEmail);

        textName = (EditText)findViewById(R.id.pill_name);
        textDate = (EditText)findViewById(R.id.pill_date);
        deleteButton = (AppCompatButton)findViewById(R.id.btn_delete);
        cancelButton = (AppCompatButton)findViewById(R.id.btn_cancel);

        String pillName = getIntent().getStringExtra("PILL_NAME");
        String pillDate = getIntent().getStringExtra("PILL_DATE");

        textName.setText(pillName);
        textDate.setText(pillDate);

        deleteButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Boolean deleted = db.deletePill(textName.getText().toString());
                if (deleted) ViewPillActivity.super.onBackPressed();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }
}
