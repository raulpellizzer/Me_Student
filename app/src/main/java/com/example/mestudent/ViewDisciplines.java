package com.example.mestudent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Spinner;

import static com.example.mestudent.schema.dtbaseCONSTS.CLASSROOM_COLUMN_NAME;
import static com.example.mestudent.schema.dtbaseCONSTS.DATE_COLUMN_NAME;
import static com.example.mestudent.schema.dtbaseCONSTS.DISCIPLINE_COLUMN_NAME;
import static com.example.mestudent.schema.dtbaseCONSTS.TEACHER_COLUMN_NAME;

public class ViewDisciplines extends AppCompatActivity {
    int iDiscName, iTeacherName, iClassroom, iDate;
    PostDbHelper DB;
    private RecyclerView viewDisciplines;
    Cursor c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_disciplines);

        DB = new PostDbHelper(this);

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        getSupportActionBar().hide();

        viewDisciplines = findViewById(R.id.recViewAllDisc);

        viewDisciplines.setAdapter(new DisciplineAdapterRecycler(DisciplinesInfo.listar(), this));
        RecyclerView.LayoutManager layout = new GridLayoutManager(ViewDisciplines.this, 2);

        viewDisciplines.setLayoutManager(layout);







        c = DB.readDisciplineData();
        iDiscName = c.getColumnIndex(DISCIPLINE_COLUMN_NAME);
        iTeacherName = c.getColumnIndex(TEACHER_COLUMN_NAME);
        iClassroom = c.getColumnIndex(CLASSROOM_COLUMN_NAME);
        iDate = c.getColumnIndex(DATE_COLUMN_NAME);
    }
}
