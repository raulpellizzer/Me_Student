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

public class RemoveDiscipline extends AppCompatActivity implements View.OnClickListener {
    int iDiscName;
    PostDbHelper DB;
    Cursor c;
    private Spinner removeDiscSpinner;
    private Button btnRmvDiscContinue;

    List<String> disciplines = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_discipline);

        btnRmvDiscContinue = findViewById(R.id.btnContinueRmvDisc);
        btnRmvDiscContinue.setOnClickListener(this);

        try {
            DB = new PostDbHelper(this);

            getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN
            );
            getSupportActionBar().hide();

            removeDiscSpinner = findViewById(R.id.rmvDiscSpiner);

            c = DB.readDisciplineData();

            if (c != null) {
                iDiscName = c.getColumnIndex(DISCIPLINE_COLUMN_NAME);
                for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
                    disciplines.add(c.getString(iDiscName));
                }

                ArrayAdapter<String> adapterSpinner = new ArrayAdapter<String>(RemoveDiscipline.this, android.R.layout.simple_list_item_1, disciplines);
                removeDiscSpinner.setAdapter(adapterSpinner);

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
        boolean result;

        try {
            if (view.getId() == R.id.btnContinueRmvDisc){

                itemPosition = removeDiscSpinner.getSelectedItemPosition();
                selectedItem = disciplines.get(itemPosition);
                result = DB.deleteDiscipline(selectedItem);

                if(result) {
                    Context context = getApplicationContext();
                    CharSequence text = "Succesfully removed.";
                    int duration = Toast.LENGTH_LONG;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                } else {
                    Context context = getApplicationContext();
                    CharSequence text = "Error. Please, try again.";
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
