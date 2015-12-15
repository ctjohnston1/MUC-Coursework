package com.example.conor.moblilecourseworkapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import java.util.concurrent.ExecutionException;

/**
 * Created by conor on 14/12/2015.
 */
public class carParkOutput extends MainActivity {
TextView result;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.carpark_output);
        Intent outputScreen = getIntent();
        Context appContext = getApplicationContext();
        result = (TextView)findViewById(R.id.test);
//Get RSS Feed
        RSSDataItem CarParks = new RSSDataItem();
        String RSSFeedURL = "https://api.open.glasgow.gov.uk/traffic/carparks";//may need another part to this for the parameters
        AsyncParser asyncRSSParser = new AsyncParser(this, RSSFeedURL);
        try {
            CarParks = asyncRSSParser.execute("").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        result.setText(CarParks.getCarPark());



    }


}
