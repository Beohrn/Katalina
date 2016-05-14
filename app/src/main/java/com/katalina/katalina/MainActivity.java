package com.katalina.katalina;

import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.katalina.katalina.app.SpeechApp;
import com.katalina.katalina.fragments.FragmentMicStart;
import com.katalina.katalina.intents.PackageInfo;
import com.katalina.katalina.utils.NetworkStateManager;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    private FragmentTransaction transaction;
    private TextView currentMessage;
    private NetworkStateManager manager;
    private boolean isStarting = false;
    private boolean isNotStartService;

    @Inject
    Intent intent;
    @Inject
    List<PackageInfo> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.layout_main);
        SpeechApp.getAppComponent().inject(this);
        currentMessage = (TextView) findViewById(R.id.txtSpeechInput);

        manager = new NetworkStateManager((ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE));

        if (!manager.isConnectedOrConnecting()) {
            currentMessage.setText(getString(R.string.connection_error));
        }

        isStarting = false;

        if (!isNotStartService) {
            transaction = getFragmentManager().beginTransaction();
            transaction.add(R.id.container, new FragmentMicStart());
            transaction.commit();
        }
        isNotStartService = true;
        Log.d("MainActivity", "onCreate");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("MainActivity", "onResume");
        registerReceiver(receiver, new IntentFilter("com.katalina.katalina"));
        if (isStarting)
            startService(intent);
        isStarting = true;

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("MainActivity", "onStop");
        unregisterReceiver(receiver);
        if (isNotStartService)
            isNotStartService = false;

        stopService(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("MainActivity", "onDestroy");
        if (isNotStartService)
            isNotStartService = false;

        stopService(intent);
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                currentMessage.setText(intent.getStringExtra("Message"));
            } else {
                currentMessage.setText(getString(R.string.speech_prompt));
            }
        }
    };


}
