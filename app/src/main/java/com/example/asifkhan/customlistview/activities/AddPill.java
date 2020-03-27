package com.example.asifkhan.customlistview.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.asifkhan.customlistview.R;
import com.example.asifkhan.customlistview.SQLiteHelpers.SQLiteDBHelper;
import com.example.asifkhan.customlistview.adapters.CustomListAdapter;
import com.example.asifkhan.customlistview.models.Pill;
import com.example.asifkhan.customlistview.models.User;

public class AddPill extends AppCompatActivity {

    EditText textName;
    EditText textDate;
    AppCompatButton addButton;
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

        addButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!textName.getText().toString().equals("") && !textDate.getText().toString().equals("")){
                    Pill pill = new Pill(textName.getText().toString(), textDate.getText().toString());
                    db.addPill(user, pill);
                    AddPill.super.onBackPressed();

                }
                else Toast.makeText(getBaseContext(), "There are empty fileds", Toast.LENGTH_LONG).show();
            }
        });

    }
}
