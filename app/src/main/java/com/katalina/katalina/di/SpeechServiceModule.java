package com.katalina.katalina.di;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import com.katalina.katalina.services.VoiceRecognitionService;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

@Module
public class SpeechServiceModule {

    @Provides
    @NonNull
    @Singleton
    public Intent provideIntent(Context context, VoiceRecognitionService service) {
        return new Intent(context, service.getClass());
    }
}
