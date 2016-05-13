package com.katalina.katalina.utils;

import android.content.Intent;

import com.katalina.katalina.MainActivity;
import com.katalina.katalina.services.VoiceRecognitionService;

public class ServiceRecognition {

    public Intent getRecognitionService(MainActivity activity) {
        return new Intent(activity, VoiceRecognitionService.class);
    }
}
