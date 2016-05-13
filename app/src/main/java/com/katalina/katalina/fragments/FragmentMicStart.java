package com.katalina.katalina.fragments;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.katalina.katalina.R;
import com.katalina.katalina.app.SpeechApp;
import com.katalina.katalina.di.AppModule;
import com.katalina.katalina.services.VoiceRecognitionService;

import javax.inject.Inject;

public class FragmentMicStart extends Fragment {

    private ImageButton imageButton;
    private FragmentTransaction transaction;

    @Inject
    Intent intent;

    public FragmentMicStart() {
    }


    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_start, container, false);
        SpeechApp.getAppComponent().inject(this);
        imageButton = (ImageButton) view.findViewById(R.id.btnStart);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startService(intent);
                transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.container, new FragmentMicStop());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        return view;
    }
}
