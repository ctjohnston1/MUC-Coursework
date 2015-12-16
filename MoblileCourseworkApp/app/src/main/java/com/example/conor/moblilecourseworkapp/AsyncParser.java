package com.example.conor.moblilecourseworkapp;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.net.MalformedURLException;

/**
 * Created by conor on 14/12/2015.
 */
public class AsyncParser extends AsyncTask<String, Integer, RSSDataItem> {
//declare class variables
    private Context context;
    private String urlToParse;
//pass parameters through constructor
    public AsyncParser(Context context, String url) {
        this.context = context; //set the context
        this.urlToParse = url;//set the url
    }

    @Override
    protected void onPreExecute() {
        Toast.makeText(context, "Parsing started!", Toast.LENGTH_SHORT).show();//message to inform the user that the parsing is commencing
    }

    @Override
    protected RSSDataItem doInBackground(String... params) {
        RSSParser parser = new RSSParser();//creates an RSSParser instance called parser
        try {//attempting to send the data through to the RSSParser object.
            parser.parseRSSData(urlToParse);
        } catch (MalformedURLException e) { //the failsafe incase the attempt is unsuccessful
            e.printStackTrace();
        }
        return parser.getRssDataItem();//return the RSSDataItem values

    }

    @Override
    protected void onPostExecute(RSSDataItem result) {
        Toast.makeText(context, "Parsing finished!", Toast.LENGTH_SHORT).show();//lets user know through a toast that the parsing has finished
    }


}
