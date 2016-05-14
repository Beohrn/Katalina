package com.katalina.katalina.di;


import com.katalina.katalina.MainActivity;
import com.katalina.katalina.fragments.FragmentMicStart;
import com.katalina.katalina.fragments.FragmentMicStop;
import com.katalina.katalina.intents.CommonIntents;


import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, ServiceModule.class,
        SpeechServiceModule.class, AppsListModule.class})
public interface AppComponent {

    void inject(MainActivity mainActivity);

    void inject(FragmentMicStart fragmentMicStart);

    void inject(FragmentMicStop fragmentMicStop);

    void inject(CommonIntents commonIntents);

}
