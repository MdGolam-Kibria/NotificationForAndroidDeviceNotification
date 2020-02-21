package com.example.notificationforandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.RemoteInput;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    static String cID = "kk";
    Button button;
    private static final String key = "here your input";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.click);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {//this is a notification channel for device notification
            NotificationChannel notificationChannel = new NotificationChannel(cID, "kibria", NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription("this is my channel Description");
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NotificationCompat.Builder builder =
                        new NotificationCompat.Builder(MainActivity.this, cID)
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setContentTitle("wow it was works")
                        .setContentText("this is my fast  notification")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//                        .setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 })
                        .setSound(Settings.System.DEFAULT_NOTIFICATION_URI);
//                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                PendingIntent pendingIntent =
                        PendingIntent.getActivity(MainActivity.this, 0, new Intent(MainActivity.this, MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);
                builder.setContentIntent(pendingIntent);
                NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(MainActivity.this);
                notificationManagerCompat.notify(1, builder.build());
            }
        });
    }
}
