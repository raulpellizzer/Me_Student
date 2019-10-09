package com.example.mestudent;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.mestudent.schema.dtbaseCONSTS.LOGIN_TABLE_NAME;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btnLogin;
    private Button btnSignUp;
    private EditText edtUserName;
    private EditText edtPassword;
    private String userName;
    private String password;
    private boolean result;
    PostDbHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DB = new PostDbHelper(this);


        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        getSupportActionBar().hide();

        btnLogin = findViewById(R.id.btnLogin);
        btnSignUp = findViewById(R.id.btnSignUp);

        btnLogin.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);

        edtUserName = findViewById(R.id.edtLogin);
        edtPassword = findViewById(R.id.edtPassword);
    }

    @Override
    public void onClick(View v) {
        try {
            switch (v.getId()) {
                case R.id.btnLogin:
                    userName = edtUserName.getText().toString();
                    password = edtPassword.getText().toString();

                    result = DB.readUserData(LOGIN_TABLE_NAME, userName, password);

                    if (result) {
                        Intent it = new Intent(
                                getApplicationContext(),
                                appMenu.class
                        );

                        it.putExtra("user", edtUserName.getText().toString());

                        startActivity(it);
                        break;
                    } else {
                        Toast.makeText(getApplicationContext(), "User not authenticated. Try again.", Toast.LENGTH_LONG).show();
                        break;
                    }

                case R.id.btnSignUp:
                    Intent itSignUp = new Intent(
                            getApplicationContext(),
                            signUp.class
                    );
                    startActivity(itSignUp);
                    break;

                default:
                    break;

            }
        } finally {
            return;
        }
    }
}
