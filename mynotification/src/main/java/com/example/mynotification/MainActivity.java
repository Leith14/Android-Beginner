package com.example.mynotification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private NotificationManager manager;
    private Notification notification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("lei", "测试通知", NotificationManager.IMPORTANCE_HIGH);
            manager.createNotificationChannel(channel);
        }

        Intent intent = new Intent(this, NotificationActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);


        notification = new NotificationCompat.Builder(this, "lei")
                .setContentTitle("官方通知")
                .setContentText("bilbil干杯！")
                .setSmallIcon(R.drawable.ic_baseline_outlet_24)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.image))
                .setColor(Color.parseColor("#ff0000"))
                // TODO NotificationActivity的日志没触发，等学多点再回来看看什么问题
                .setContentIntent(pendingIntent)
                .setAutoCancel(false)
                .build();
    }

    public void sendNotification(View view) {
        manager.notify(1,notification);
        Log.e("lei","sent noti");

    }

    public void cancelNotification(View view) {
        manager.cancel(1);
    }
}