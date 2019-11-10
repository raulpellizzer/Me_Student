package com.example.mestudent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.mestudent.schema.dtbaseCONSTS.CLASSROOM_COLUMN_NAME;
import static com.example.mestudent.schema.dtbaseCONSTS.DATE_COLUMN_NAME;
import static com.example.mestudent.schema.dtbaseCONSTS.DISCIPLINE_COLUMN_NAME;
import static com.example.mestudent.schema.dtbaseCONSTS.GRADE_COLUMN_NAME;
import static com.example.mestudent.schema.dtbaseCONSTS.GRADE_DISCIPLINE_COLUMN_NAME;
import static com.example.mestudent.schema.dtbaseCONSTS.TEACHER_COLUMN_NAME;

public class SelectedDiscInfo extends AppCompatActivity {
    private TextView txtViewDiscName;
    private TextView txtViewDiscTeacher;
    private TextView txtViewDiscClass;
    private TextView txtViewDiscDate;
    private TextView txtViewDiscGrades;
    private String discName, discTeacher, discClass, discDate, discGrades;
    int idiscName, idiscTeacher, idiscClass, idiscDate, idiscGrades;
    private Cursor c;
    private PostDbHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_disc_info);

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        getSupportActionBar().hide();

        try{
            DB = new PostDbHelper(this);

            txtViewDiscName = findViewById(R.id.txtViewDiscName);
            txtViewDiscTeacher = findViewById(R.id.txtViewDiscTeacher);
            txtViewDiscClass = findViewById(R.id.txtViewDiscClass);
            txtViewDiscDate = findViewById(R.id.txtViewDiscDay);
            txtViewDiscGrades = findViewById(R.id.txtViewDiscGrades);

            Intent it = getIntent();
            if (it != null) {
                discName = it.getStringExtra("discipline");
            }

            c = DB.readDisciplineData();

            if (c != null) {
                idiscName = c.getColumnIndex(DISCIPLINE_COLUMN_NAME);
                idiscTeacher = c.getColumnIndex(TEACHER_COLUMN_NAME);
                idiscClass = c.getColumnIndex(CLASSROOM_COLUMN_NAME);
                idiscDate = c.getColumnIndex(DATE_COLUMN_NAME);
                for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
                    if ((c.getString(idiscName).equals(discName))) {
                        discTeacher = c.getString(idiscTeacher);
                        discClass = c.getString(idiscClass);
                        discDate = c.getString(idiscDate);
                    }
                }

                txtViewDiscName.setText(discName);
                txtViewDiscTeacher.setText(discTeacher);
                txtViewDiscClass.setText(discClass);
                txtViewDiscDate.setText(discDate);

                c = null;
                c = DB.readGradeData();

                if (c != null) {
                    idiscName = c.getColumnIndex(GRADE_DISCIPLINE_COLUMN_NAME);
                    idiscGrades = c.getColumnIndex(GRADE_COLUMN_NAME);

                    for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
                        if ((c.getString(idiscName).equals(discName))) {
                            discGrades = c.getString(idiscGrades);
                        }
                    }
                }

                txtViewDiscGrades.setText(discGrades);
            } else {
                Context context = getApplicationContext();
                CharSequence text = "Error. Please, try again.";
                int duration = Toast.LENGTH_LONG;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
