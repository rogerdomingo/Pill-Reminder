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

public class ViewPillActivity extends AppCompatActivity {

    private Pill pill;

    EditText textName;
    EditText textDate;
    AppCompatButton deleteButton;

    private SQLiteDBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpill);

        db = new SQLiteDBHelper(this);

        textName = (EditText)findViewById(R.id.pill_name);
        textDate = (EditText)findViewById(R.id.pill_date);
        deleteButton = (AppCompatButton)findViewById(R.id.btn_delete);

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



    }
}
