package com.example.interfragmentcomunication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements TextFragment.Comunicatable {

    TextFragment textFragment;
    Fragment buttonFragment;
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textFragment = new TextFragment();
        buttonFragment = new ButtonFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.first_frame, textFragment, "text");
        fragmentTransaction.add(R.id.second_frame, buttonFragment, "button");
        fragmentTransaction.commit();


    }


    @Override
    public void respond(int count) {
        textFragment.updateCounter(++counter);
    }
}
