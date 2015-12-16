package com.example.conor.moblilecourseworkapp;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by conor on 14/12/2015.
 */
public class carParkOutput extends MainActivity implements View.OnClickListener{
    //Declaring the widgets on screen 2
    TextView result;
    public ArrayList<String> arr = new ArrayList<String>();//this was the arraylist i was planning on putting through an adapter and into a string
                                                            //before the pull parser data item sent me for a loop of the infinite variety.
Button moreInfo;
    MediaPlayer infosound;//media player item is needed to be able to play sound.
    ImageView image;
    TextView areSpaces;
    TextView amountOfSpaces;
    TextView capacity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.carpark_output);//set the content view to carpark_output.xml (Screen 2)
        Intent outputScreen = getIntent();//get the intent for the outputScreen
        Context appContext = getApplicationContext();//get the application context
        image = (ImageView)findViewById(R.id.imageView2);//set the imageview on screen 2 to a variable

        //written code for drawing to screen instead of sourcing directly from the xml file
        int imageResID = appContext.getResources().getIdentifier("drawable/cartoon","drawable", "com.example.conor.moblilecourseworkapp");//a filepath to the image
        image.setImageResource(imageResID);//sets the image

        infosound = MediaPlayer.create(carParkOutput.this,R.raw.info);//tying the sound effect to the mediaplayer after telling which folder it is to be found in
        result = (TextView) findViewById(R.id.test);//result is set to equate to the test TextView on screen2
       areSpaces =(TextView) findViewById(R.id.areSpaces);//areSpaces is set to be represented by a textview
        amountOfSpaces= (TextView) findViewById(R.id.amountOfSpaces);//amountofspaces is set to equal a textview on screen
        capacity=(TextView) findViewById(R.id.capacity);//capacity is set to equal a textView
        moreInfo = (Button)findViewById(R.id.moreInfo);//moreinfo is set to equal a button on screen 2
        moreInfo.setOnClickListener(this);//more info button has an onclicklistener placed on it.

//Get RSS Feed

       RSSDataItem CarParks = new RSSDataItem(); //new RSSDataItem made
        String RSSFeedURL = "https://api.open.glasgow.gov.uk/traffic/carparks";//the RSSFeed url
        AsyncParser asyncRSSParser = new AsyncParser(this, RSSFeedURL);//new AsyncParser passing through the url
        try {
            CarParks = asyncRSSParser.execute("").get();//executed the asyncrssparser and gets the information.

          //  Log.e("SizeofArraylist",CarParks.getCarPark());
        } catch (InterruptedException e) {//catches exception
            e.printStackTrace();
        } catch (ExecutionException e) {//caches exception
            e.printStackTrace();
        }
        arr.add(CarParks.getCarPark());//the arraylist for the parser before the problem was spotted
        result.setText(CarParks.getCarPark());//setting the visual value of the textview
            areSpaces.setText(CarParks.getOccupancy() + "%");//setting the visual value of the textview
            capacity.setText(CarParks.getCapacity());//setting the visual value of the textview
        amountOfSpaces.setText(CarParks.getTakenSpaces());
      //  int free1 = Integer.parseInt(CarParks.getCapacity());

      //  int free2 = Integer.parseInt(CarParks.getOccupancy());


        //result.setText();
        //for (int l = 0; l < arr.size(); l++) {
         //   Log.e("checking loader", arr.get(l));
//

      //  }

    }
    public void onClick(View view){//for the onclicklistener
        infosound.start();//player the sound infosouond
        moreInformation info = new moreInformation();//instance of the moreInformation class, rendered moot by the maps class
        mapActivity theMap = new mapActivity();//instance of the maps activity class
        setContentView(R.layout.map_view);//set the view to the map_view.xml
    }
}
