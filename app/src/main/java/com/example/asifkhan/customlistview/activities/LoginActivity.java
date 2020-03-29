package com.example.asifkhan.customlistview.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asifkhan.customlistview.R;
import com.example.asifkhan.customlistview.SQLiteHelpers.SQLiteDBHelper;
import com.example.asifkhan.customlistview.models.User;

public class LoginActivity extends AppCompatActivity {
    //private static final String TAG = "LoginActivity";
    //private static final int REQUEST_SIGNUP = 0;

    AppCompatButton loginButton;
    EditText textEmail;
    EditText textPassword;
    TextView linkToSignUp;

    private SQLiteDBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new SQLiteDBHelper(this);

        loginButton = (AppCompatButton)findViewById(R.id.btn_login);
        textEmail = (EditText)findViewById(R.id.input_email);
        textPassword = (EditText)findViewById(R.id.input_password);
        linkToSignUp = (TextView)findViewById(R.id.link_signup);

        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });

        linkToSignUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the SignUp activity
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivityForResult(intent, 0);

            }
        });
    }

    private void login() {

        if (!validate()) {
            onLoginFailed();
            return;
        }
        else {
            User user = db.getUser(textEmail.getText().toString());
            if(user.getId() == null) Toast.makeText(getBaseContext(), "User does not exist", Toast.LENGTH_LONG).show();
            else if(!user.getPassword().equals(textPassword.getText().toString())) Toast.makeText(getBaseContext(), "Wrong password: ", Toast.LENGTH_LONG).show();
            else {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("USER_EMAIL", user.getEmail());
                startActivityForResult(intent, 0);
            }
        }
        //Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        //startActivityForResult(intent, 0);

    }

    public boolean validate() {
        boolean valid = true;

        String email = textEmail.getText().toString();
        String password = textPassword.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            textEmail.setError("Enter a valid email address");
            valid = false;
        } else {
            textEmail.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 15) {
            textPassword.setError("Between 4 and 15 alphanumeric characters");
            valid = false;
        } else {
            textPassword.setError(null);
        }

        return valid;
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
        textPassword.setText("");

        loginButton.setEnabled(true);
    }
}
