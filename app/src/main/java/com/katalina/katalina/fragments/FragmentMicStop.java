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

import javax.inject.Inject;

/**
 * Created by Alexander on 09.05.2016.
 */
public class FragmentMicStop extends Fragment {

    private ImageButton imageButton;
    private FragmentTransaction transaction;

    @Inject
    Intent intent;

    public FragmentMicStop() {
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stop, container, false);
        SpeechApp.getAppComponent().inject(this);
        imageButton = (ImageButton) view.findViewById(R.id.btnStop);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().stopService(intent);
                transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.container, new FragmentMicStart());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        return view;
    }
}
