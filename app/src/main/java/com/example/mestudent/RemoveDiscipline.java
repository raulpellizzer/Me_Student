package com.example.mestudent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.example.mestudent.schema.dtbaseCONSTS.DISCIPLINE_COLUMN_NAME;

public class RemoveDiscipline extends AppCompatActivity {
    int iDiscName;
    PostDbHelper DB;
    private Spinner removeDiscSpinner;
    Cursor c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_discipline);

        DB = new PostDbHelper(this);
        List<String> disciplines = new ArrayList<>();

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        getSupportActionBar().hide();

        removeDiscSpinner = findViewById(R.id.rmvDiscSpiner);

        c = DB.readDisciplineData();
        iDiscName = c.getColumnIndex(DISCIPLINE_COLUMN_NAME);

        if(c != null) {
            for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
                disciplines.add(c.getString(iDiscName));
            }

            ArrayAdapter<String> adapterSpinner = new ArrayAdapter<String>(RemoveDiscipline.this, android.R.layout.simple_list_item_1, disciplines);

            removeDiscSpinner.setAdapter(adapterSpinner);

        } else {
            Context context = getApplicationContext();
            CharSequence text = "Error. Please, try again.";
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }
}
