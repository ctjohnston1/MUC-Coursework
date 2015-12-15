package com.example.conor.moblilecourseworkapp;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
TextView result;
    public ArrayList<String> arr = new ArrayList<String>();
Button moreInfo;
    MediaPlayer infosound;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.carpark_output);
        Intent outputScreen = getIntent();
        Context appContext = getApplicationContext();

        infosound = MediaPlayer.create(carParkOutput.this,R.raw.info);
        result = (TextView) findViewById(R.id.test);
        moreInfo = (Button)findViewById(R.id.moreInfo);
        moreInfo.setOnClickListener(this);

//Get RSS Feed
       RSSDataItem CarParks = new RSSDataItem();
        String RSSFeedURL = "https://api.open.glasgow.gov.uk/traffic/carparks";//may need another part to this for the parameters
        AsyncParser asyncRSSParser = new AsyncParser(this, RSSFeedURL);
        try {
            CarParks = asyncRSSParser.execute("").get();
          //  Log.e("SizeofArraylist",CarParks.getCarPark());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        arr.add(CarParks.getCarPark());


        //result.setText();
        //for (int l = 0; l < arr.size(); l++) {
         //   Log.e("checking loader", arr.get(l));
//

      //  }

    }
    public void onClick(View view){
        infosound.start();
        moreInformation info = new moreInformation();
        setContentView(R.layout.more_info);
    }
}
