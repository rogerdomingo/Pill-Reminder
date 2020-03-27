package com.example.asifkhan.customlistview.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;

import com.example.asifkhan.customlistview.R;


public class NewPillActivity extends AppCompatActivity {

    EditText textPillName;
    EditText textPillDate;
    AppCompatButton doneButton;
    AppCompatButton cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_pill);

        textPillName = (EditText) findViewById(R.id.pill_name);
        textPillDate = (EditText) findViewById(R.id.pill_date);
        doneButton = (AppCompatButton) findViewById(R.id.btn_done);
        cancelButton = (AppCompatButton) findViewById(R.id.btn_cancel);

        doneButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }
}


