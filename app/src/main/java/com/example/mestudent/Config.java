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
import android.database.Cursor;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Switch;
import android.widget.Toast;

import static com.example.mestudent.schema.dtbaseCONSTS.AUTH_COLUMN_NAME;

public class Config extends AppCompatActivity implements View.OnClickListener {
    private Button btnConfig;
    private CheckBox ckBoxAuth;
    private Switch swTheme;
    private AlertDialog alert;
    private PostDbHelper DB;
    private Cursor c;
    private int iAuth, iTheme;
    private String currentAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        DB = new PostDbHelper(this);

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

            swTheme = findViewById(R.id.swt);
            ckBoxAuth = findViewById(R.id.ckbAuth);
            btnConfig = findViewById(R.id.btnSaveConfig);
            btnConfig.setOnClickListener(this);

            c = DB.readConfigData();
            c.moveToFirst();

            iAuth = c.getColumnIndex(AUTH_COLUMN_NAME);
            currentAuth = c.getString(iAuth);

            if (currentAuth.equals("AUTH:YES"))
                ckBoxAuth.toggle();

        } finally {
            return;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View view) {

        try {
            if (view.getId() == R.id.btnSaveConfig) {
                if (ckBoxAuth.isChecked()) {
                    DB.toggleAuthOn();

                    Context context = getApplicationContext();
                    CharSequence text = "Authentification is now necessary.";
                    int duration = Toast.LENGTH_LONG;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                } else {
                    DB.toggleAuthOff();
                    Context context = getApplicationContext();
                    CharSequence text = "Authentification is no longer necessary.";
                    int duration = Toast.LENGTH_LONG;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }




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