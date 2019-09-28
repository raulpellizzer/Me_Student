package com.example.mestudent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class newDiscipline extends AppCompatActivity implements View.OnClickListener {
    PostDbHelper DB;
    private Button btnNewDiscipline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_discipline);

        DB = new PostDbHelper(this);

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        getSupportActionBar().hide();

        btnNewDiscipline = findViewById(R.id.btnRegisterNewDisc);
        btnNewDiscipline.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnRegisterNewDisc) {
            boolean result;
            EditText discName, teacherName, classroom, date;

            discName = findViewById(R.id.edtNewDiscName);
            teacherName = findViewById(R.id.edtNewDiscTeacherName);
            classroom = findViewById(R.id.edtClassroom);
            date = findViewById(R.id.edtClassDay);

            if ( (discName.getText().toString().length() > 4) && (teacherName.getText().toString().length() > 4) && (classroom.getText().toString().length() > 0) && (date.getText().toString().length() > 0) ) {
                result = DB.insertNewDiscipline(discName.getText().toString(), teacherName.getText().toString(), classroom.getText().toString(), date.getText().toString());

                if (result) {
                    Context context = getApplicationContext();
                    CharSequence text = "Successfully registered course!";
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
            } else {
                Toast.makeText(getApplicationContext(), "Error. Please, type again.", Toast.LENGTH_LONG).show();
                discName.setText("");
                teacherName.setText("");
                classroom.setText("");
                date.setText("");
                return;
            }
        }
    }
}
