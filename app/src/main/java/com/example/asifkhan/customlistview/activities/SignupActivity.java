package com.example.asifkhan.customlistview.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asifkhan.customlistview.R;
import com.example.asifkhan.customlistview.SQLiteHelpers.SQLiteDBHelper;
import com.example.asifkhan.customlistview.models.User;

public class SignupActivity extends AppCompatActivity {
    private static final String TAG = "SignupActivity";

    AppCompatButton signupButton;
    EditText textName;
    EditText textEmail;
    EditText textPassword;
    TextView linkToLogin;

    private SQLiteDBHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        db = new SQLiteDBHelper(this);

        signupButton = (AppCompatButton)findViewById(R.id.btn_signup);
        textName = (EditText)findViewById(R.id.input_name);
        textEmail = (EditText)findViewById(R.id.input_email);
        textPassword = (EditText)findViewById(R.id.input_password);
        linkToLogin = (TextView)findViewById(R.id.link_login);

        signupButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                signUp();
            }
        });

        linkToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void signUp() {
        if (!validate()) {
            onSignUpFailed();
            return;
        }
        else {
            if(existsUsernameWithThisEmail(textEmail.getText().toString())) {
                Toast.makeText(getBaseContext(), "The email is already registered", Toast.LENGTH_LONG).show();
            }
            else {
                Log.v(TAG, "Else");
                User user = new User(textName.getText().toString(), textEmail.getText().toString(), textPassword.getText().toString(), null);
                registerUser(user);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("USER_EMAIL", user.getEmail());
                startActivityForResult(intent, 0);
                Toast.makeText(getBaseContext(), "SignUp Completed", Toast.LENGTH_LONG).show();
            }
        }
        //Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        //startActivityForResult(intent, 0);

    }

    public boolean validate() {
        boolean valid = true;

        String name = textName.getText().toString();
        String email = textEmail.getText().toString();
        String password = textPassword.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            textName.setError("at least 3 characters");
            valid = false;
        } else {
            textName.setError(null);
        }

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            textEmail.setError("enter a valid email address");
            valid = false;
        } else {
            textEmail.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            textPassword.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            textPassword.setError(null);
        }

        return valid;
    }

    public void onSignUpFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
        textPassword.setText("");

        signupButton.setEnabled(true);
    }

    public boolean existsUsernameWithThisEmail(String email) {
        Log.v(TAG, "existsUsernameWithThisEmail: " + email);
        User user = db.getUser(email);
        Log.v(TAG, "existsUsernameWithThisEmail: " + user.getName());
        if(user.getId() != null) return true;
        else return false;
    }

    private void registerUser(User user) {
        db.addUser(user);
    }
}
