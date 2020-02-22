package com.example.interfragmentcomunication;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements TextFragment.Comunicatable {

    TextFragment textFragment;
    ButtonFragment buttonFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (savedInstanceState == null) {
            textFragment = new TextFragment();
            buttonFragment = new ButtonFragment();
            fragmentTransaction.add(R.id.first_frame, textFragment, "text");
            fragmentTransaction.add(R.id.second_frame, buttonFragment, "button");
            fragmentTransaction.commit();
        } else {
            textFragment = (TextFragment) fragmentManager.findFragmentByTag("text");
            buttonFragment = (ButtonFragment) fragmentManager.findFragmentByTag("button");
        }
    }

    @Override
    public void respond(int count) {
        textFragment.updateCounter();
    }
}
