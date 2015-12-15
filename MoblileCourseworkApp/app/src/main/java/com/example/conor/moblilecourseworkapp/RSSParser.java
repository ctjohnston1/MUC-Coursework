package com.example.conor.moblilecourseworkapp;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * Created by conor on 14/12/2015.
 */
public class RSSParser  {
    //creates an instance of RSSDataIetem
    private RSSDataItem rssDataItem;

    private String carpark;
    private String spaces;
   // private int i = 0;
 //   public  ArrayList<String> arr = new ArrayList<String>();
  //  private String found;
//set values of the RSS Data item that has been instantiated passing in a string.
    public void setRssDataItem(String sItemData)
    {
        rssDataItem.setCarPark(sItemData);//set the carPark
        rssDataItem.setOccupancy(sItemData);//set the description
        rssDataItem.setCapacity(sItemData);//set the link
       // rssDataItem.setArr(sItemData);//arraylistvalue

    }

    public RSSDataItem getRssDataItem()//returns the RSS Data Item Object
    {
        return this.rssDataItem;
    }

    public RSSParser()
    {
        this.rssDataItem =  new RSSDataItem();
        setRssDataItem(null);
    }

    public void parseRSSDataItem(XmlPullParser theParser, int theEventType)
    {
        try
        {
            int i = 0;
            while (theEventType != XmlPullParser.END_DOCUMENT)
            {

                // Found a start tag
                if(theEventType == XmlPullParser.START_TAG)
                {
                    // Check which Tag has been found
                    if (theParser.getName().equalsIgnoreCase("carParkIdentity"))
                    {
                        // Now just get the associated text
                        String temp = theParser.nextText();
                        // store data in class
                        rssDataItem.setCarPark(temp);
                     //   arr.add(temp);

                        //send data to logcat to show that it has been parsed

                       //     rssDataItem.setArr(temp);
                       // Log.e("Arraylist Works", arr.get(i));
                      //      i++;
                    }
                    else
                        // Check which Tag we have
                        if (theParser.getName().equalsIgnoreCase("carParkOccupancy"))
                        {
                            // Now just get the associated text
                            String temp = theParser.nextText();
                            // store data in class
                            rssDataItem.setOccupancy(temp);
                            Log.e("OccupancyPercentage", temp);
                        }
                        else
                            // Check which Tag we have
                            if (theParser.getName().equalsIgnoreCase("totalCapacity"))
                            {
                                // Now just get the associated text
                                String temp = theParser.nextText();
                                // store data in class
                                rssDataItem.setCapacity(temp);
                                Log.v("Total Capacity", temp);

                            }
                }

                // Get the next event
                theEventType = theParser.next();

            } // End of while

        }
        catch (XmlPullParserException parserExp1)
        {
            Log.e("MyTag", "Parsing error" + parserExp1.toString());
        }

        catch (IOException parserExp1)
        {
            Log.e("MyTag", "IO error during parsing");
        }

    }

    public void parseRSSData(String RSSItemsToParse) throws MalformedURLException {
        URL rssURL = new URL(RSSItemsToParse);
        InputStream rssInputStream;
        try
        {
            XmlPullParserFactory parseRSSfactory = XmlPullParserFactory.newInstance();
            parseRSSfactory.setNamespaceAware(true);
            XmlPullParser RSSxmlPP = parseRSSfactory.newPullParser();
            String xmlRSS = getStringFromInputStream(getInputStream(rssURL), "UTF-8");
            RSSxmlPP.setInput(new StringReader(xmlRSS));
            int eventType = RSSxmlPP.getEventType();

            parseRSSDataItem(RSSxmlPP,eventType);

        }
        catch (XmlPullParserException ae1)
        {
            Log.e("MyTag","Parsing error" + ae1.toString());
        }
        catch (IOException ae1)
        {
            Log.e("MyTag","IO error during parsing");
        }

        Log.e("MyTag","End document");
    }

    public InputStream getInputStream(URL url) throws IOException
    {
        return url.openConnection().getInputStream();
    }

    public static String getStringFromInputStream(InputStream stream, String charsetName) throws IOException
    {
        int n = 0;
        char[] buffer = new char[1024 * 4];
        InputStreamReader reader = new InputStreamReader(stream, charsetName);
        StringWriter writer = new StringWriter();
        while (-1 != (n = reader.read(buffer))) writer.write(buffer, 0, n);
        return writer.toString();
    }

}
