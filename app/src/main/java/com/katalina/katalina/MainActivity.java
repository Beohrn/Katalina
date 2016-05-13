package com.katalina.katalina;

import android.app.Application;
import android.app.FragmentTransaction;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
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
import android.view.animation.LayoutAnimationController;
import android.widget.RemoteViews;

import com.katalina.katalina.fragments.FragmentMicStart;
import com.katalina.katalina.services.VoiceRecognitionService;
import com.squareup.leakcanary.LeakCanary;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    private FragmentTransaction transaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.layout_main);

        final Intent intent = new Intent(this, VoiceRecognitionService.class);

        transaction = getFragmentManager().beginTransaction();
        transaction.add(R.id.container, new FragmentMicStart());
        transaction.commit();

//        findViewById(R.id.start).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startService(intent);
//            }
//        });
//
//        findViewById(R.id.stop).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                stopService(intent);
//            }
//        });

    }

}
