package com.katalina.katalina.intents;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.katalina.katalina.app.SpeechApp;

import java.util.List;

import javax.inject.Inject;

public class CommonIntents {

    private Context context;
    private String message;
    private final String TAG = "VoiceRecognitionService";
    private final String action = "android.intent.category.LAUNCHER";
    @Inject
    List<PackageInfo> list;

    public CommonIntents(Context context, String message) {
        this.context = context;
        this.message = message;
        SpeechApp.getAppComponent().inject(this);
    }

    public void execute() {


        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).appname.toLowerCase().equals(message.toLowerCase())) {
                startIntentWitnPackage(list.get(i).pname);
            }
        }

//        switch (message) {
//            case "VK":
//                startIntentWithClassName("com.vkontakte.android", "com.vkontakte.android.MainActivity");
//                break;
//            case "Facebook":
//                startIntentWithClassName("com.facebook.katana", "com.facebook.katana.LoginActivity");
//                break;
//            case "контакты":
//                startIntentWithActionAndUrl(Intent.ACTION_PICK, android.provider.ContactsContract.Contacts.CONTENT_URI);
//                break;
//            case "Adobe Reader":
//                startIntentWithClassName("com.adobe.reader", "com.adobe.reader.AdobeReader");
//                break;
//            case "Google Translate":
//                startIntentWithClassName("com.google.android.apps.translate", "com.google.android.apps.translate.TranslateActivity");
//                break;
//            case "камера":
//                startIntentWithAction(MediaStore.ACTION_IMAGE_CAPTURE);
//                break;
//            case "калькулятор":
//                startIntentWithClassName("com.android.calculator2", "com.android.calculator2.Calculator");
//                break;
//            case "календарь":
//                startIntentWithClassName("com.google.android.calendar", "com.android.calendar.LaunchActivity");
//                break;
//        }

    }


    private void startIntentWitnPackage(String packageName) {
        Intent intent = context.getPackageManager().getLaunchIntentForPackage(packageName);
        context.startActivity(intent);
    }


}
