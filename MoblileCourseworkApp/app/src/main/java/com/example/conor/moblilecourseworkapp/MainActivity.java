package com.example.conor.moblilecourseworkapp;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button beginApp;
    ImageView mungosTree;
    TextView welcome;
    FragmentManager fmAboutDialogue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mungosTree = (ImageView)findViewById(R.id.mungosTree);
        welcome = (TextView)findViewById(R.id.welcome);
        beginApp = (Button)findViewById(R.id.beginApp);
        beginApp.setOnClickListener(this);
        fmAboutDialogue = this.getFragmentManager();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (item.getItemId()){
            case R.id.Quit:
                finish();
                return true;
            case R.id.About:
                //about dialogue
                DialogFragment mcAboutDig = new AboutDialogue();
                mcAboutDig.show(fmAboutDialogue,"mc_About_Dig");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
        //noinspection SimplifiableIfStatement



    }
    @Override
    public void onClick(View view){
Intent outputScreen = new Intent(getApplicationContext(), carParkOutput.class);
        Intent mInfo = new Intent(getApplicationContext(), moreInformation.class);

startActivity(outputScreen);
    }
}
