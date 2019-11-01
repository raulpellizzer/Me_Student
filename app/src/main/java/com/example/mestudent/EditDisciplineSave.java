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

public class EditDisciplineSave extends AppCompatActivity implements View.OnClickListener {
    PostDbHelper DB;
    String discName, teacherName, classroom, date;
    String newDiscName, newTeacherName, newClassroom, newDate;
    private EditText edtEditDiscName, edtEditTeacherName, edtEditClassroom, edtEditDate;
    private Button btnSaveChanges;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_discipline_save);

        DB = new PostDbHelper(this);

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        getSupportActionBar().hide();

        edtEditDiscName = findViewById(R.id.edtEditDiscName);
        edtEditTeacherName = findViewById(R.id.edtEditDiscTeacherName);
        edtEditClassroom = findViewById(R.id.edtEditClassroom);
        edtEditDate = findViewById(R.id.edtEditClassDay);
        btnSaveChanges = findViewById(R.id.btnSaveChanges);
        btnSaveChanges.setOnClickListener(this);

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

    @Override
    public void onClick(View view) {
        boolean result = false;

        try {
            newDiscName = edtEditDiscName.getText().toString();
            newTeacherName = edtEditTeacherName.getText().toString();
            newClassroom = edtEditClassroom.getText().toString();
            newDate = edtEditDate.getText().toString();

            result = DB.updateRegister(discName, newDiscName, newTeacherName, newClassroom, newDate);

            if (result) {
                Context context = getApplicationContext();
                CharSequence text = "Changes saved succesfully.";
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
        }finally {
            return;
        }
    }
}
