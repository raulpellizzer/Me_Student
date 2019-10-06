package com.example.mestudent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RadioButton;

public class ResetAll extends AppCompatActivity {
    private RadioButton radio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_all);

        radio = findViewById(R.id.radNo);
        radio.toggle();
    }
}
