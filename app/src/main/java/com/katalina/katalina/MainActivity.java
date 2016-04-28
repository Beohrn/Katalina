package com.katalina.katalina;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.RemoteViews;

import com.katalina.katalina.services.VoiceRecognitionService;
import com.squareup.leakcanary.LeakCanary;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "VoiceRecognitionService";
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private boolean isSerivceUp = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        LeakCanary.install(getApplication());

//        final NotificationManager nm = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
//        Notification.Builder builder = new Notification.Builder(getApplicationContext())
//                .setSmallIcon(R.drawable.ic_launcher)
//                .setTicker("RecApp")
//                .setAutoCancel(true)
//                .setContentIntent(null);
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//            // build a complex notification, with buttons and such
//
//            RemoteViews notificationView = new RemoteViews(
//                    getApplicationContext().getPackageName(),
//                    R.layout.notification_layout
//            );
//
//            notificationView.setImageViewResource(
//                    R.id.imagenotileft,
//                    R.drawable.ic_launcher);
//            final Intent intent = new Intent(getApplicationContext(), VoiceRecognitionService.class);
//
//            if (!isSerivceUp) {
//                intent.setAction("STOP");
//                notificationView.setOnClickPendingIntent(R.id.stop, unInitialize(getApplicationContext(), intent));
////                notificationView.setTextViewText(R.id.start, "START");
//            } else {
//                notificationView.setOnClickPendingIntent(R.id.start, initialize(getApplicationContext(), intent));
////                notificationView.setTextViewText(R.id.start, "Start");
//            }
//
////            findViewById(R.id.start).setOnClickListener(new View.OnClickListener() {
////                @Override
////                public void onClick(View v) {
////                    startService(intent);
////                }
////            });
////
////            findViewById(R.id.stop).setOnClickListener(new View.OnClickListener() {
////                @Override
////                public void onClick(View v) {
////                    stopService(intent);
////                }
////            });
//            notificationView.setTextViewText(R.id.title, getTitle());
//            notificationView.setTextViewText(R.id.text, "Tap on Start");
//
//
//            builder = builder.setContent(notificationView);
//
//        } else {
//            // Build a simpler notification, without buttons
//            builder = builder.setContentTitle(getTitle())
//                    .setContentText("LOLOLOLO")
//                    .setSmallIcon(android.R.drawable.ic_menu_gallery);
//        }
//
//        final Notification notification = builder.build();
//
//        nm.notify(1010, notification);
//
//        Intent service = new Intent(this, VoiceRecognitionService.class);
//        startService(service);
//        finish();
        final Intent intent = new Intent(this, VoiceRecognitionService.class);
        findViewById(R.id.start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(intent);
            }
        });

        findViewById(R.id.stop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(intent);
            }
        });

    }

    private PendingIntent initialize(Context context, Intent intent) {

        PendingIntent pendingIntent = PendingIntent.getService(context, 0, intent, 0);
        isSerivceUp = true;
        return pendingIntent;
    }

    private PendingIntent unInitialize(Context context, Intent intent) {

        PendingIntent pendingIntent = PendingIntent.getService(context, 0, intent, 0);
        isSerivceUp = false;
        return pendingIntent;
    }

}
