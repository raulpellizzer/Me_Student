package com.example.mestudent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class signUp extends AppCompatActivity implements View.OnClickListener{
    PostDbHelper DB;
    private Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        DB = new PostDbHelper(this);

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        getSupportActionBar().hide();

        btnSignUp = findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        try {
            if (view.getId() == R.id.btnSignUp) {
                boolean result;
                EditText login, password, confPassword;

                login = findViewById(R.id.edtTextSignUpLogin);
                password = findViewById(R.id.edtTextSignUpPassword);
                confPassword = findViewById(R.id.edtTextConfPass);

                if ((login.getText().toString().length() == 0) || (password.getText().toString().length() == 0) || (confPassword.getText().toString().length() == 0) || (!password.getText().toString().equals(confPassword.getText().toString()))) {
                    Toast.makeText(getApplicationContext(), "Type a valid login/password name.", Toast.LENGTH_LONG).show();
                    login.setText("");
                    password.setText("");
                    confPassword.setText("");
                    return;
                } else {
                    result = DB.insertUser(login.getText().toString(), password.getText().toString());

                    if (result) {
                        Context context = getApplicationContext();
                        CharSequence text = "Welcome, " + login.getText().toString() + " !";
                        int duration = Toast.LENGTH_LONG;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    } else {
                        Context context = getApplicationContext();
                        CharSequence text = "Error. Please, try again.";
                        int duration = Toast.LENGTH_LONG;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                }
            }
        } finally {
            return;
        }
    }
}
