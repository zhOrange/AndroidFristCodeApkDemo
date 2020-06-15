package com.zh.notificationdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    NotificationManager notificationManager;

    Button btnCreate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCreate = (Button)findViewById(R.id.btn_CreateNotice);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NotiificationActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);

                notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
                createNotificationChannel("1", "demo", NotificationManager.IMPORTANCE_MIN, "the notification channel demo.");
                Notification notification = new NotificationCompat.Builder(MainActivity.this, "1")
                        .setContentTitle("Notification Title")
                        //.setContentText("the content of notification.the content of notification.the content of notification.the content of notification.the content of notification.the content of notification.the content of notification.the content of notification.")
                        .setWhen(System.currentTimeMillis())
                        .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.duck)))
//                        .setStyle(new NotificationCompat.BigTextStyle().bigText("the content of notification.the content of notification.the content of notification.the content of notification.the content of notification.the content of notification.the content of notification."))
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true)
                        .setSound(Uri.fromFile(new File("/system/media/audio/ringtones/Luna.ogg")))
                        .build();
                notificationManager.notify(1, notification);
            }
        });
    }

    public void createNotificationChannel(String id, String name, int importance, String desc) {
        //Android 8.0以上，不再自动分配默认通道，需要创建通道。
        //同时，setSound()等notification方法，如果在channel中已经设置，
        //则notification中设置将可能会失效。
        if(Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O){
            if (notificationManager.getNotificationChannel(id) != null) {
                return;
            }
            NotificationChannel notificationChannel = new NotificationChannel(id, name, importance);

            notificationChannel.enableLights(true);
            notificationChannel.enableVibration(true);

            notificationChannel.setLightColor(Color.RED);
            notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
            notificationChannel.setShowBadge(true);
            notificationChannel.setBypassDnd(true);
            notificationChannel.setVibrationPattern(new long[]{100, 200, 300, 400});
            notificationChannel.setDescription(desc);

            notificationManager.createNotificationChannel(notificationChannel);
        }

    }
}
