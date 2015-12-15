package com.example.conor.moblilecourseworkapp;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.net.MalformedURLException;

/**
 * Created by conor on 14/12/2015.
 */
public class AsyncParser extends AsyncTask<String, Integer, RSSDataItem> {

    private Context context;
    private String urlToParse;

    public AsyncParser(Context context, String url) {
        this.context = context;
        this.urlToParse = url;
    }

    @Override
    protected void onPreExecute() {
        Toast.makeText(context, "Parsing started!", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected RSSDataItem doInBackground(String... params) {
        RSSParser parser = new RSSParser();
        try {
            parser.parseRSSData(urlToParse);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return parser.getRssDataItem();
    }

    @Override
    protected void onPostExecute(RSSDataItem result) {
        Toast.makeText(context, "Parsing finished!", Toast.LENGTH_SHORT).show();
    }


}
