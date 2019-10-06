package com.example.mestudent;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class Config extends AppCompatActivity implements View.OnClickListener {
    private Button btnConfig;
    private AlertDialog alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Me, Student");
        builder.setMessage("Configurações salvas com sucesso");
        alert = builder.create();

        builder.setPositiveButton("Positivo", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                Toast.makeText(Config.this, "positivo=" + arg1, Toast.LENGTH_SHORT).show();
            }
        });

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        getSupportActionBar().hide();

        btnConfig = findViewById(R.id.btnSaveConfig);
        btnConfig.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.btnSaveConfig) {
            // Configurar a parte do negativo (falha na operação)
            alert.show();
        }

    }
}
