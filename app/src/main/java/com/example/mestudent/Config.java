package com.example.mestudent;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class Config extends AppCompatActivity implements View.OnClickListener {
    private Button btnConfig;
    private AlertDialog alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Me, Student");
            builder.setMessage("Configurações salvas com sucesso");
            alert = builder.create();

            getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN
            );
            getSupportActionBar().hide();

            btnConfig = findViewById(R.id.btnSaveConfig);
            btnConfig.setOnClickListener(this);
        } finally {
            return;
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View view) {

        try {
            if (view.getId() == R.id.btnSaveConfig) {
                // Configurar a parte do negativo (falha na operação)
                CriarNotificacao("Me,Student", "Settings updated successfully");
                alert.show();
            }
        } finally {
            return;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void CriarNotificacao(String titulo, String texto) {
        final int NOTIFICATION_ID = 123;
        final String CHANNEL_ID = "Notificação";

        try {
            NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                int importance = NotificationManager.IMPORTANCE_HIGH;
                NotificationChannel canal = new NotificationChannel(CHANNEL_ID, "canal", importance);
                canal.setDescription("Canal de notificação");
                canal.enableLights(true);
                canal.setLightColor(Color.RED);
                canal.enableVibration(true);
                canal.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
                canal.setShowBadge(true);
                notificationManager.createNotificationChannel(canal);
            }

            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setSmallIcon(R.drawable.student)
                    .setContentTitle(titulo)
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(texto))
                    .setContentText(texto);

            TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
            stackBuilder.addParentStack(Config.class);
            stackBuilder.addNextIntent(new Intent(this, Config.class));
            PendingIntent it = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setContentIntent(it);

            notificationManager.notify(NOTIFICATION_ID, builder.build());
        } finally {
            return;
        }
    }
}