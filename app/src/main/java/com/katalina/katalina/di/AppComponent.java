package com.katalina.katalina.di;


import com.katalina.katalina.MainActivity;
import com.katalina.katalina.fragments.FragmentMicStart;
import com.katalina.katalina.fragments.FragmentMicStop;


import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, ServiceModule.class, SpeechServiceModule.class})
public interface AppComponent {

    void inject(MainActivity mainActivity);
    void inject(FragmentMicStart fragmentMicStart);
    void inject(FragmentMicStop fragmentMicStop);
}
