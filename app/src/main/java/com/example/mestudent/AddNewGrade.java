package com.example.mestudent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.mestudent.schema.dtbaseCONSTS.GRADE_COLUMN_NAME;
import static com.example.mestudent.schema.dtbaseCONSTS.GRADE_DISCIPLINE_COLUMN_NAME;

public class AddNewGrade extends AppCompatActivity implements View.OnClickListener {
    private EditText edtGradeDesc;
    private EditText edtGradeNumber;
    private Button btnSaveNewGrade;
    private TextView txtViewDisc;
    PostDbHelper DB;
    String discipline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_grade);

        try {
            getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN
            );
            getSupportActionBar().hide();

            edtGradeDesc = findViewById(R.id.edtGradeDesc);
            edtGradeNumber = findViewById(R.id.edtGradeNumber);
            btnSaveNewGrade = findViewById(R.id.btnSaveNewGrade);
            txtViewDisc = findViewById(R.id.txtViewDisc);
            btnSaveNewGrade.setOnClickListener(this);

            Intent it = getIntent();
            if (it != null) {
                discipline = it.getStringExtra("discipline");
                txtViewDisc.setText(discipline);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        Cursor c;
        int iDiscName, iDiscGrade;

        try{
            if (view.getId() == R.id.btnSaveNewGrade) {
                String gradeDesc, gradeNumber, newGrade;

                newGrade = "";
                gradeDesc = edtGradeDesc.getText().toString();
                gradeNumber = edtGradeNumber.getText().toString();

                newGrade = gradeDesc + ": " + gradeNumber;

                c = DB.readGradeData();
                iDiscName = c.getColumnIndex(GRADE_DISCIPLINE_COLUMN_NAME);
                iDiscGrade = c.getColumnIndex(GRADE_COLUMN_NAME);

                for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
//                    if ((c.getString(iDiscName).equals(selectedItem))) {
//
//                    }
                }














//                Context context = getApplicationContext();
//                CharSequence text = newGrade;
//                int duration = Toast.LENGTH_LONG;
//
//                Toast toast = Toast.makeText(context, text, duration);
//                toast.show();
            }



        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
