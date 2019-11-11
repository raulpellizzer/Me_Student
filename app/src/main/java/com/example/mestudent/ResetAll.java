package com.example.mestudent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

public class ResetAll extends AppCompatActivity implements View.OnClickListener {
    private RadioButton radioNo, radioYes;
    private Button btnResetAll;
    PostDbHelper DB;
    boolean resultReset, resultCreateDisc, resultCreateGrade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_all);

        DB = new PostDbHelper(this);

        radioNo = findViewById(R.id.radNo);
        radioYes = findViewById(R.id.radYes);

        radioNo.toggle();

        btnResetAll = findViewById(R.id.btnResetSemester);
        btnResetAll.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        try {
            if (view.getId() == R.id.btnResetSemester && radioYes.isChecked()) {

                resultReset = DB.resetSemester();
                resultCreateDisc = DB.createDisciplinesTable();
                resultCreateGrade = DB.createGradesTable();

                if (resultReset = true && resultCreateDisc == true && resultCreateGrade == true) {
                    Context context = getApplicationContext();
                    CharSequence text = "Your semester has been reseted.";
                    int duration = Toast.LENGTH_LONG;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }
        } finally {
            return;
        }
    }
}
