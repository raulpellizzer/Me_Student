package com.example.mestudent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
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

import static com.example.mestudent.schema.dtbaseCONSTS.DISCIPLINE_COLUMN_NAME;

public class DisciplineInfo extends AppCompatActivity implements View.OnClickListener {
    int iDiscName;
    PostDbHelper DB;
    Cursor c;
    private Spinner DiscInfoSpinner;  // removeDiscSpinner
    private Button btnDiscInfoContinue;  // btnRmvDiscContinue
    private List<String> disciplines = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discipline_info);

        btnDiscInfoContinue = findViewById(R.id.btnContinueDiscInfo);
        btnDiscInfoContinue.setOnClickListener(this);

        try {
            DB = new PostDbHelper(this);

            getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN
            );
            getSupportActionBar().hide();

            DiscInfoSpinner = findViewById(R.id.discInfoSpiner);

            c = DB.readDisciplineData();

            if (c != null) {
                iDiscName = c.getColumnIndex(DISCIPLINE_COLUMN_NAME);
                for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
                    disciplines.add(c.getString(iDiscName));
                }

                ArrayAdapter<String> adapterSpinner = new ArrayAdapter<String>(DisciplineInfo.this, android.R.layout.simple_list_item_1, disciplines);
                DiscInfoSpinner.setAdapter(adapterSpinner);

            } else {
                Context context = getApplicationContext();
                CharSequence text = "Error. Please, try again.";
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

    }
}
