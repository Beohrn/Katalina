package com.katalina.katalina.services;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.RemoteViews;

import com.katalina.katalina.R;


public class NotificationService extends Service {

    private NotificationManager nm;

    private final String TAG = "Katalina";
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private Notification notification;

    @Override
    public void onCreate() {
        super.onCreate();
        nm = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        Log.d(TAG, "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");

//        Notification.Builder builder = new Notification.Builder(getApplicationContext());
//        Intent notificationIntent = new Intent(getApplicationContext(), VoiceRecognitionService.class);
//        PendingIntent pendingIntent = PendingIntent.getService(getApplicationContext(), 0, notificationIntent, 0);
//
//        builder.setContentIntent(pendingIntent)
//                .setSmallIcon(R.drawable.stopped)
//                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.stopped))
//                .setTicker("Katalina service")
//                .setContentTitle("Katalina service")
//                .setContentText("Press to start recognition service");
//        Notification notification = builder.build();
//
//        notification.flags = Notification.FLAG_NO_CLEAR;
//        nm.notify(101, notification);


        startRecognizing();
        /*
            Intent notificationIntent = new Intent(mContext, HandleNotificationClickService.class);
            PendingIntent pendingIntent = PendingIntent.getService(mContext, 0, notificationIntent, 0);

            Notification notification = new Notification(icon, tickerText,System.currentTimeMillis());
            notification.setLatestEventInfo(mContext,contentTitle , contentText, pendingIntent);
            notification.flags = Notification.FLAG_AUTO_CANCEL | Notification.FLAG_ONGOING_EVENT;

            NotificationManager notificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);

            notificationManager.notify(CALLER_ID_NOTIFICATION_ID, notification);
        *
        *
        * */



        return super.onStartCommand(intent, flags, startId);
    }

    private void stopRecognizing() {

    }

    private void startRecognizing() {
        RemoteViews notificationView = new RemoteViews(getPackageName(), R.layout.notification_layout);
        Notification.Builder builder = new Notification.Builder(getApplicationContext());
        Intent notificationIntent = new Intent(getApplicationContext(), VoiceRecognitionService.class);
        PendingIntent pendingIntent = PendingIntent.getService(getApplicationContext(), 0, notificationIntent, 0);

//        builder
//                .setSmallIcon(R.drawable.stopped)
//                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.stopped))
//                .setTicker("Katalina service")
//                .setContentTitle("Katalina service")
//                .setContentText("Press to start recognition service");
//        notification = builder.build();
//        notification = new Notification(R.drawable.stopped, null, System.currentTimeMillis());
        notification.contentView = notificationView;
//        notificationView.setOnClickPendingIntent(R.id.switch1, pendingIntent);
        notification.flags = Notification.FLAG_NO_CLEAR;
        nm.notify(101, notification);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
