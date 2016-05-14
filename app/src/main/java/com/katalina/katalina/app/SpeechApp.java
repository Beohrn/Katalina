package com.katalina.katalina.app;

import android.app.Application;

import com.katalina.katalina.di.AppComponent;
import com.katalina.katalina.di.AppModule;
import com.katalina.katalina.di.AppsListModule;
import com.katalina.katalina.di.DaggerAppComponent;
import com.katalina.katalina.di.ServiceModule;
import com.katalina.katalina.di.SpeechServiceModule;

public class SpeechApp extends Application {

    private static AppComponent appComponent;

    public static AppComponent getAppComponent() {
        return appComponent;
    }


    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = buildComponent();

    }

    private AppComponent buildComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .speechServiceModule(new SpeechServiceModule())
                .serviceModule(new ServiceModule())
                .appsListModule(new AppsListModule())
                .build();
    }


}
