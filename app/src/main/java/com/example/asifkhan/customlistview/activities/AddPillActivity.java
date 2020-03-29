package com.example.asifkhan.customlistview.activities;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.MotionEvent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asifkhan.customlistview.R;
import com.example.asifkhan.customlistview.SQLiteHelpers.SQLiteDBHelper;
import com.example.asifkhan.customlistview.models.Pill;
import com.example.asifkhan.customlistview.models.User;

import java.util.Calendar;

public class AddPillActivity extends AppCompatActivity {

    EditText textName;
    EditText textDate;
    AppCompatButton addButton;
    AppCompatButton cancelButton;
    DatePickerDialog.OnDateSetListener setListener;

    private User user;
    private SQLiteDBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addpill);

        db = new SQLiteDBHelper(this);
        String userEmail = getIntent().getStringExtra("USER_EMAIL");
        user = db.getUser(userEmail);

        textName = (EditText)findViewById(R.id.pill_name);
        textDate = (EditText)findViewById(R.id.pill_date);
        addButton = (AppCompatButton)findViewById(R.id.btn_add);
        cancelButton = (AppCompatButton)findViewById(R.id.btn_cancel);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        textDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        AddPillActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month+1;
                        String date = day+"/"+month+"/"+year;
                        textDate.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });


        addButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!textName.getText().toString().equals("") && !textDate.getText().toString().equals("")){
                    Pill pill = new Pill(textName.getText().toString(), textDate.getText().toString());
                    db.addPill(user, pill);
                    AddPillActivity.super.onBackPressed();
                }
                else Toast.makeText(getBaseContext(), "There are empty fileds", Toast.LENGTH_LONG).show();
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
