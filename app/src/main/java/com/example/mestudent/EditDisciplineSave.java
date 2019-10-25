package com.example.mestudent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.EditText;

public class EditDisciplineSave extends AppCompatActivity {
    String discName, teacherName, classroom, date;
    private EditText edtEditDiscName, edtEditTeacherName, edtEditClassroom, edtEditDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_discipline_save);

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        getSupportActionBar().hide();

        edtEditDiscName = findViewById(R.id.edtEditDiscName);
        edtEditTeacherName = findViewById(R.id.edtEditDiscTeacherName);
        edtEditClassroom = findViewById(R.id.edtEditClassroom);
        edtEditDate = findViewById(R.id.edtEditClassDay);

        Intent itContinue = getIntent();

        if(itContinue != null) {
            discName = itContinue.getStringExtra("discipline");
            teacherName = itContinue.getStringExtra("teacher");
            classroom = itContinue.getStringExtra("classroom");
            date = itContinue.getStringExtra("date");
        }

        edtEditDiscName.setText(discName);
        edtEditTeacherName.setText(teacherName);
        edtEditClassroom.setText(classroom);
        edtEditDate.setText(date);

    }
}
