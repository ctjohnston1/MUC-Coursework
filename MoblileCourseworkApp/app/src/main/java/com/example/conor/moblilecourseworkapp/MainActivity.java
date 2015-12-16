package com.example.conor.moblilecourseworkapp;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
//declaring items for the main activity
    Button beginApp;
    ImageView mungosTree;
    TextView welcome;
    FragmentManager fmAboutDialogue;//for the about dialog

    MediaPlayer soundeffect;//for the sound effect on button click

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//set the view to the activity_main.xml
        mungosTree = (ImageView)findViewById(R.id.mungosTree);//set the imageview to mungosTree src of image already defined in activity_main.xml
        welcome = (TextView)findViewById(R.id.welcome);//set welcome = to text view

        soundeffect = MediaPlayer.create(MainActivity.this, R.raw.carstart); //set sound effect = to mediaplayer and create a sound from the R.raw file
                                                                            //with the sound effect being called carstart

        beginApp = (Button)findViewById(R.id.beginApp);//begin app button set to equal a button on screen 1 (activity_main.xml)
        beginApp.setOnClickListener(this);//attach onclicklistener to the button
        fmAboutDialogue = this.getFragmentManager();//get fragmentmanager
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);//creates a menu accessible from any layout
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (item.getItemId()){
            case R.id.Quit://quit button, has been added to the menu_main.xml
                finish();//finishes the current screen
                return true;//returns a true boolean
            case R.id.About://About button has been added to the menu_main.xml
                //about dialogue
                DialogFragment mcAboutDig = new AboutDialogue();//new about dialogue made
                mcAboutDig.show(fmAboutDialogue,"mc_About_Dig");//passes fragment manager & string in with the .show function being called
                return true;
            default:
                return super.onOptionsItemSelected(item);//returns the selected item
        }
        //noinspection SimplifiableIfStatement



    }
    @Override
    public void onClick(View view){//when  button clicked
Intent outputScreen = new Intent(getApplicationContext(), carParkOutput.class);//new intent for the carParkOutput class

        soundeffect.start();//play sound effect

startActivity(outputScreen);//start the outputScreen activity
    }
}
