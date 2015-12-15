package com.example.conor.moblilecourseworkapp;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;

/**
 * Created by conor on 15/12/2015.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class AboutDialogue extends DialogFragment{
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder aboutDialog = new AlertDialog.Builder((getActivity()));
        aboutDialog.setMessage("This app will show you information on Car Parks")
                .setTitle("About")

                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        return aboutDialog.create();
    }
}
