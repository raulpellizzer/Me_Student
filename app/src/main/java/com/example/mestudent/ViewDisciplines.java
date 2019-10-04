package com.example.mestudent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Spinner;

import static com.example.mestudent.schema.dtbaseCONSTS.CLASSROOM_COLUMN_NAME;
import static com.example.mestudent.schema.dtbaseCONSTS.DATE_COLUMN_NAME;
import static com.example.mestudent.schema.dtbaseCONSTS.DISCIPLINE_COLUMN_NAME;
import static com.example.mestudent.schema.dtbaseCONSTS.TEACHER_COLUMN_NAME;

public class ViewDisciplines extends AppCompatActivity {
    private RecyclerView viewDisciplines;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_disciplines);

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        getSupportActionBar().hide();

        viewDisciplines = findViewById(R.id.recViewAllDisc);

        viewDisciplines.setAdapter(new DisciplineAdapterRecycler(DisciplinesInfo.listAll(), this));
        //RecyclerView.LayoutManager layout = new GridLayoutManager(ViewDisciplines.this, 2);
        RecyclerView.LayoutManager layout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        viewDisciplines.setLayoutManager(layout);
    }
}
