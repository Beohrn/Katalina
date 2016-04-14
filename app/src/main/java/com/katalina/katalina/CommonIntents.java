package com.katalina.katalina;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;


public class CommonIntents {

    private Context context;
    private String message;
    private final String TAG = "VoiceRecognitionService";
    private final String action = "android.intent.category.LAUNCHER";

    public CommonIntents(Context context, String message) {
        this.context = context;
        this.message = message;
    }

    public void execute() {
//        String[] array = context.getResources().getStringArray(R.array.intents);

        switch (message) {
            case "VK":
                startIntentWithClassName("com.vkontakte.android", "com.vkontakte.android.MainActivity");
                break;
            case "Facebook":
                startIntentWithClassName("com.facebook.katana", "com.facebook.katana.LoginActivity");
                break;

            case "контакты":
                startIntentWithActionAndUrl(Intent.ACTION_PICK, android.provider.ContactsContract.Contacts.CONTENT_URI);
                break;
            case "LinkedIn":
                startIntentWithClassName("com.linkedin.android.authenticator", "com.linkedin.android.authenticator.LaunchActivity");
                break;
            case "Adobe Reader":
                startIntentWithClassName("com.adobe.reader", "com.adobe.reader.AdobeReader");
                break;
            case "Google Translate":
                startIntentWithClassName("com.google.android.apps.translate", "com.google.android.apps.translate.TranslateActivity");
                break;
            case "камера":
                startIntentWithAction(MediaStore.ACTION_IMAGE_CAPTURE);

                break;
        }

        if (message.contains("Позвонить")) {
            phoneCall(message);
        }
    }

    private void startIntentWithClassName(String packageName, String className) {
        Intent intent = new Intent(action);
        intent.setClassName(packageName, className);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    private void startIntentWithActionAndUrl(String action, Uri uri) {
        Intent intent = new Intent(action, uri);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    private void startIntentWithAction(String action) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    private void phoneCall(String message) {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse(message));

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(callIntent);
    }

}
