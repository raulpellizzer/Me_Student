package com.example.mestudent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.example.mestudent.schema.dtbaseCONSTS.CLASSROOM_COLUMN_NAME;
import static com.example.mestudent.schema.dtbaseCONSTS.DATE_COLUMN_NAME;
import static com.example.mestudent.schema.dtbaseCONSTS.DISCIPLINE_COLUMN_NAME;
import static com.example.mestudent.schema.dtbaseCONSTS.TEACHER_COLUMN_NAME;

public class EditDiscipline extends AppCompatActivity implements View.OnClickListener {
    int iDiscName;
    PostDbHelper DB;
    private Spinner editDiscSpinner;
    private Button btnContinue;
    Cursor c;

    List<String> disciplines = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_discipline);

        try {
            DB = new PostDbHelper(this);

            getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN
            );
            getSupportActionBar().hide();

            btnContinue = findViewById(R.id.btnContinueEdtDisc);
            editDiscSpinner = findViewById(R.id.edtDiscSpinner);
            btnContinue.setOnClickListener(this);

            c = DB.readDisciplineData();

            if (c != null) {
                iDiscName = c.getColumnIndex(DISCIPLINE_COLUMN_NAME);
                for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
                    disciplines.add(c.getString(iDiscName));
                }

                ArrayAdapter<String> adapterSpinner = new ArrayAdapter<String>(EditDiscipline.this, android.R.layout.simple_list_item_1, disciplines);

                editDiscSpinner.setAdapter(adapterSpinner);

            } else {
                Context context = getApplicationContext();
                CharSequence text = "Error. Please, try again. Check if you have disciplines assigned to you.";
                int duration = Toast.LENGTH_LONG;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        } finally {
            return;
        }
    }

    @Override
    public void onClick(View view) {
        int itemPosition, iEditDiscName, iEditTeacherName, iEditClassroom, iEditDate;
        String selectedItem, discName, teacherName, classroom, date;
        c = null;

        discName = "";
        teacherName = "";
        classroom = "";
        date = "";

        if(view.getId() == R.id.btnContinueEdtDisc){
            Intent itContinue = new Intent(
                    getApplicationContext(),
                    EditDisciplineSave.class
            );

            itemPosition = editDiscSpinner.getSelectedItemPosition();
            selectedItem = disciplines.get(itemPosition);

            c = DB.readDisciplineData();
            iEditDiscName = c.getColumnIndex(DISCIPLINE_COLUMN_NAME);
            iEditTeacherName = c.getColumnIndex(TEACHER_COLUMN_NAME);
            iEditClassroom = c.getColumnIndex(CLASSROOM_COLUMN_NAME);
            iEditDate = c.getColumnIndex(DATE_COLUMN_NAME);

            for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
                if ((c.getString(iEditDiscName).equals(selectedItem))) {
                    discName = c.getString(iEditDiscName);
                    teacherName = c.getString(iEditTeacherName);
                    classroom = c.getString(iEditClassroom);
                    date = c.getString(iEditDate);
                    break;
                }
            }

            itContinue.putExtra("discipline", discName);
            itContinue.putExtra("teacher", teacherName);
            itContinue.putExtra("classroom", classroom);
            itContinue.putExtra("date", date);

            startActivity(itContinue);
        }

    }
}
