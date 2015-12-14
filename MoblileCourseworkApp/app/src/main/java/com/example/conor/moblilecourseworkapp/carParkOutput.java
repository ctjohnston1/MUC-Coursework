package com.example.conor.moblilecourseworkapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by conor on 14/12/2015.
 */
public class carParkOutput extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.carpark_output);
        Intent outputScreen = getIntent();
        Context appContext = getApplicationContext();
    }
}
