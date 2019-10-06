package com.example.mestudent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class appMenu extends AppCompatActivity implements View.OnClickListener{
    private Button btnNewDisc;
    private Button btnAbout;
    private Button btnAddNote;
    private Button btnViewAllDisc;
    private Button btnRmvDisc;
    private Button btnEditDisc;
    private Button btnResetSemester;
    private Button btnConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_menu);

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        getSupportActionBar().hide();

        btnNewDisc = findViewById(R.id.btnAddDisc);
        btnAbout = findViewById(R.id.btnAbout);
        btnAddNote = findViewById(R.id.btnAddNote);
        btnViewAllDisc = findViewById(R.id.btnViewDisc);
        btnRmvDisc = findViewById(R.id.btnRemoveDisc);
        btnEditDisc = findViewById(R.id.btnEditDisc);
        btnResetSemester = findViewById(R.id.btnReset);
        btnConfig = findViewById(R.id.btnConfig);

        btnNewDisc.setOnClickListener(this);
        btnAbout.setOnClickListener(this);
        btnAddNote.setOnClickListener(this);
        btnViewAllDisc.setOnClickListener(this);
        btnRmvDisc.setOnClickListener(this);
        btnEditDisc.setOnClickListener(this);
        btnResetSemester.setOnClickListener(this);
        btnConfig.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnAddDisc:
                Intent itAdd = new Intent(
                        getApplicationContext(),
                        newDiscipline.class
                );
                startActivity(itAdd);
                break;

            case R.id.btnAbout:
                Intent itAbout = new Intent(
                        getApplicationContext(),
                        About.class
                );
                startActivity(itAbout);
                break;

            case R.id.btnAddNote:
                Intent itAddNote = new Intent(
                        getApplicationContext(),
                        AddGrade.class
                );
                startActivity(itAddNote);
                break;

            case R.id.btnViewDisc:
                Intent itViewAll = new Intent(
                        getApplicationContext(),
                        ViewDisciplines.class
                );
                startActivity(itViewAll);
                break;

            case R.id.btnRemoveDisc:
                Intent itRmvDisc = new Intent(
                        getApplicationContext(),
                        RemoveDiscipline.class
                );
                startActivity(itRmvDisc);
                break;

            case R.id.btnEditDisc:
                Intent itEdtDisc = new Intent(
                        getApplicationContext(),
                        EditDiscipline.class
                );
                startActivity(itEdtDisc);
                break;

            case R.id.btnReset:
                Intent itReset = new Intent(
                        getApplicationContext(),
                        ResetAll.class
                );
                startActivity(itReset);
                break;

            case R.id.btnConfig:
                Intent itConfig = new Intent(
                        getApplicationContext(),
                        Config.class
                );
                startActivity(itConfig);
                break;

            default:
                break;

        }
    }
}
