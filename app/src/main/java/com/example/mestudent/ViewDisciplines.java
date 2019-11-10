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

import java.util.ArrayList;
import java.util.List;

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
        viewDisciplines.setAdapter(new DisciplineAdapterRecycler(this.listAll(), this));
        RecyclerView.LayoutManager layout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        viewDisciplines.setLayoutManager(layout);
    }

    public List<DisciplinesInfo> listAll(){
        int iDiscName;
        List<DisciplinesInfo> disciplines = new ArrayList<>();
        PostDbHelper DB = new PostDbHelper(this);
        Cursor c;

        c = DB.readDisciplineData();

        if (c != null) {
            iDiscName = c.getColumnIndex(DISCIPLINE_COLUMN_NAME);
            for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
                disciplines.add(new DisciplinesInfo(c.getString(iDiscName), c.getString(iDiscName), c.getString(iDiscName), c.getString(iDiscName)));
            }
        }

        return disciplines;
    }
}
