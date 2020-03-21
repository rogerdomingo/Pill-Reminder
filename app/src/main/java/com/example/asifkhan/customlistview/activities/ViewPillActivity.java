package com.example.asifkhan.customlistview.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.example.asifkhan.customlistview.R;
import com.example.asifkhan.customlistview.models.Pill;

public class ViewPillActivity extends AppCompatActivity {

    private Pill pill;

    EditText textName;
    EditText textDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpill);

        textName = (EditText)findViewById(R.id.pill_name);
        textDate = (EditText)findViewById(R.id.pill_date);

        String pillName = getIntent().getStringExtra("PILL_NAME");
        String pillDate = getIntent().getStringExtra("PILL_DATE");

        textName.setText(pillName);
        textDate.setText(pillDate);



    }
}
