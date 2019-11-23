package com.example.mestudent;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.mestudent.schema.dtbaseCONSTS.AUTH_COLUMN_NAME;
import static com.example.mestudent.schema.dtbaseCONSTS.LOGIN_TABLE_NAME;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btnLogin;
    private Button btnSignUp;
    private EditText edtUserName;
    private EditText edtPassword;
    private String userName;
    private String password;
    private String currentAuth;
    private int iAuth;
    private boolean result;
    private PostDbHelper DB;
    private Cursor c = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        try {
            DB = new PostDbHelper(this);
            c = DB.readConfigData();

            if (c != null) {
                if (c.getCount() <= 0) {
                    DB.initializeConfig();
                }
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }

    @Override
    public void onClick(View v) {
        try {
            switch (v.getId()) {
                case R.id.btnLogin:

                    c = DB.readConfigData();
                    if (c != null) {
                        c.moveToFirst();
                        iAuth = c.getColumnIndex(AUTH_COLUMN_NAME);
                        currentAuth = c.getString(iAuth);

                        if (currentAuth.equals("AUTH:YES")) {
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
                        } else {
                            Intent it = new Intent(
                                    getApplicationContext(),
                                    appMenu.class
                            );

                            it.putExtra("user", edtUserName.getText().toString());

                            startActivity(it);
                            break;
                        }
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
