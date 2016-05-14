package com.katalina.katalina.services;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;

import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.katalina.katalina.intents.PackageInfo;
import com.katalina.katalina.intents.CommonIntents;

import java.util.ArrayList;
import java.util.List;


public class VoiceRecognitionService extends Service {

    private final String TAG = "VoiceRecognitionService";
    private SpeechRecognizer recognizer;
    private Intent intent;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
        start();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (intent != null)
            if (intent.getAction() != null)
                if (intent.getAction().equals("STOP"))
                    stopSelf();


        Log.d(TAG, "onStartCommand");
        Toast.makeText(getApplicationContext(), "Start Service", Toast.LENGTH_SHORT).show();

        bindService(new Intent(this, VoiceRecognitionService.class), mServiceConnection, 0);


        return super.onStartCommand(intent, flags, startId);
    }

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "Service Connected");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "Service Disconnected");
        }

    };

    @Override
    public void onDestroy() {

        if (recognizer != null) {
            cancel();
        }

        if (mServiceConnection != null) {
            unbindService(mServiceConnection);
            mServiceConnection = null;
        }
        Log.d(TAG, "onDestroy");
        super.onDestroy();
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind");
        return null;
    }

    public void start() {
        recognizer = SpeechRecognizer.createSpeechRecognizer(getApplicationContext());
        intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getPackageName());
        recognizer.setRecognitionListener(new SpeechListener());
        recognizer.startListening(intent);

    }

    public void cancel() {
        recognizer.cancel();
        recognizer.destroy();
        recognizer = null;
        intent = null;
    }


    public class SpeechListener implements RecognitionListener {

        private final String TAG = "VoiceRecognitionService";
        private boolean isCountDown;

        CountDownTimer timer = new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {

            }
        };

        @Override
        public void onReadyForSpeech(Bundle params) {
            Log.d(TAG, "onReadyForSpeech");
            publishResults("Say something...");
            timer.start();

        }

        @Override
        public void onBeginningOfSpeech() {
            Log.d(TAG, "onBeginningOfSpeech");
            timer.cancel();
        }

        @Override
        public void onRmsChanged(float rmsdB) {

        }

        @Override
        public void onBufferReceived(byte[] buffer) {

        }

        @Override
        public void onEndOfSpeech() {
            Log.d(TAG, "onEndOfSpeech");
        }

        @Override
        public void onError(int error) {
            timer.cancel();
            String message = "";

            switch (error) {
                case SpeechRecognizer.ERROR_NETWORK_TIMEOUT:
                    message = "ERROR_NETWORK_TIMEOUT";
                    cancel();
                    start();
                    break;
                case SpeechRecognizer.ERROR_NETWORK:
                    message = "ERROR_NETWORK";
                    break;
                case SpeechRecognizer.ERROR_AUDIO:
                    message = "ERROR_AUDIO";
                    break;
                case SpeechRecognizer.ERROR_CLIENT:
                    message = "ERROR_CLIENT";
                    break;
                case SpeechRecognizer.ERROR_SPEECH_TIMEOUT:
                    message = "ERROR_SPEECH_TIMEOUT";
                    cancel();
                    start();
                    break;
                case SpeechRecognizer.ERROR_RECOGNIZER_BUSY:
                    message = "ERROR_RECOGNIZER_BUSY";
                    cancel();
                    start();
                    break;
                case SpeechRecognizer.ERROR_NO_MATCH:
                    message = "ERROR_NO_MATCH";
                    cancel();
                    start();
                    break;
                case SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS:
                    message = "ERROR_INSUFFICIENT_PERMISSIONS";
                    break;
                case SpeechRecognizer.ERROR_SERVER:
                    message = "ERROR_SERVER";

                    break;
            }

            Log.d(TAG, "Error " + error + " : " + message);
        }

        @Override
        public void onResults(Bundle results) {

            ArrayList<String> result = results
                    .getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
            Log.d(TAG, "onResults: " + result.get(0).toString());

            publishResults(result.get(0).toString());

            if (recognizer != null) {
                cancel();
                start();
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                new CommonIntents(getApplicationContext(), result.get(0).toString()).execute();
            }

        }

        @Override
        public void onPartialResults(Bundle partialResults) {

        }

        @Override
        public void onEvent(int eventType, Bundle params) {

        }

        private void publishResults(String message) {
            Intent intent = new Intent("com.katalina.katalina");
            intent.putExtra("Message", message);
            sendBroadcast(intent);
        }

    }
}
