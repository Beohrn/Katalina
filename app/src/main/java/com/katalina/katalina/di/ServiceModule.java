package com.katalina.katalina.di;

import com.katalina.katalina.services.VoiceRecognitionService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ServiceModule {

    @Singleton
    @Provides
    public VoiceRecognitionService provideSerivice() {
        return new VoiceRecognitionService();
    }
}
