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

public class AddGrade extends AppCompatActivity implements View.OnClickListener {
    int iDiscName;
    PostDbHelper DB;
    private Spinner addGradeSpinner;
    private Button btnAddGrade;
    Cursor c;

    List<String> disciplines = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_grade);

        try {
            DB = new PostDbHelper(this);

            getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN
            );
            getSupportActionBar().hide();

            addGradeSpinner = findViewById(R.id.addGradeSpiner);
            btnAddGrade = findViewById(R.id.btnContinueAddGrade);
            btnAddGrade.setOnClickListener(this);

            c = DB.readDisciplineData();

            if (c != null) {
                iDiscName = c.getColumnIndex(DISCIPLINE_COLUMN_NAME);
                for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
                    disciplines.add(c.getString(iDiscName));
                }

                ArrayAdapter<String> adapterSpinner = new ArrayAdapter<String>(AddGrade.this, android.R.layout.simple_list_item_1, disciplines);

                addGradeSpinner.setAdapter(adapterSpinner);

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
        int itemPosition;
        String selectedItem;

        try {
            if (view.getId() == R.id.btnContinueAddGrade) {
                Intent it = new Intent(
                        getApplicationContext(),
                        AddNewGrade.class
                );

                itemPosition = addGradeSpinner.getSelectedItemPosition();
                selectedItem = disciplines.get(itemPosition);
                it.putExtra("discipline", selectedItem);
                startActivity(it);
            }
        } finally {
            return;
        }
    }
}
