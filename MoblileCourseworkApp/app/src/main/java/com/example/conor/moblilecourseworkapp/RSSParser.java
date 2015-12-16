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



   // private int i = 0;
 //   public  ArrayList<String> arr = new ArrayList<String>();
  //  private String found;
//set values of the RSS Data item that has been instantiated passing in a string.
    public void setRssDataItem(String sItemData)
    {
        rssDataItem.setCarPark(sItemData);//set the carPark
        rssDataItem.setOccupancy(sItemData);//set the description
        rssDataItem.setCapacity(sItemData);//set the link
        rssDataItem.setLongitude(sItemData);//set the longitude
       // rssDataItem.setArr(sItemData);//arraylistvalue

    }

    public RSSDataItem getRssDataItem()//returns the RSS Data Item Object
    {
        return this.rssDataItem;
    }

    public RSSParser()
    {
        this.rssDataItem =  new RSSDataItem();//sets this rss = to a new RSSDataItem
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
                    if (theParser.getName().equalsIgnoreCase("carParkIdentity"))//picks up car park identity
                    {
                        // Now just get the associated text
                        String temp = theParser.nextText();
                        // store data in class
                        rssDataItem.setCarPark(temp);
                        Log.e("Car Park: ",temp);//send a message to logcat (using error colour to help seperate parsed values on logcat
                    }
                    else
                        // Check which Tag we have
                        if (theParser.getName().equalsIgnoreCase("carParkOccupancy"))//picks up car park occupancy percentage
                        {
                            // Now just get the associated text
                            String temp = theParser.nextText();
                            // store data in class
                            rssDataItem.setOccupancy(temp);
                            Log.e("OccupancyPercentage", temp);//send message to logcat
                        }
                        else
                            // Check which Tag we have
                            if (theParser.getName().equalsIgnoreCase("totalCapacity"))//picks up the total capacity of car park
                            {
                                // Now just get the associated text
                                String temp = theParser.nextText();
                                // store data in class
                                rssDataItem.setCapacity(temp);
                                Log.v("Total Capacity", temp);//sends message to logcat

                            }

                            else
                                // Check which Tag we have
                                if (theParser.getName().equalsIgnoreCase("longitude"))//picks up the longitude of the car park
                                {
                                    // Now just get the associated text
                                    String temp = theParser.nextText();
                                    // store data in class
                                    rssDataItem.setLongitude(temp);
                                    Log.v("Logitude= ", temp);//sends message to logcat

                                }
                                else
                                    // Check which Tag we have
                                    if (theParser.getName().equalsIgnoreCase("latitude"))//picks up the latitude of the car park
                                    {
                                        // Now just get the associated text
                                        String temp = theParser.nextText();
                                        // store data in class
                                        rssDataItem.setLatitude(temp);
                                        Log.e("Latitude= ", temp);//show value of latitude in logcat

                                    }
                                    else
                                        // Check which Tag we have
                                        if (theParser.getName().equalsIgnoreCase("occupiedSpaces"))//shows the number of spaces taken in car park
                                        {
                                            // Now just get the associated text
                                            String temp = theParser.nextText();
                                            // store data in class
                                            rssDataItem.setTakenSpaces(temp);
                                            Log.e("Taken Spaces= ", temp);//shows number of spaces taken in car park on logcat

                                        }
                }

                // Get the next event
                theEventType = theParser.next();

            } // End of while

        }
        catch (XmlPullParserException parserExp1)//in the event that the parsing of the data item fails throw this message up on logcat
        {
            Log.e("MyTag", "Parsing error" + parserExp1.toString());
        }

        catch (IOException parserExp1) //catch try and throw error message to logcat if it fails.
        {
            Log.e("MyTag", "IO error during parsing");
        }

    }

    public void parseRSSData(String RSSItemsToParse) throws MalformedURLException {
        URL rssURL = new URL(RSSItemsToParse); //setting up the xml feed instance
        InputStream rssInputStream;
        try
        {
            XmlPullParserFactory parseRSSfactory = XmlPullParserFactory.newInstance();//creating an instance of an xml pull parser factory
            parseRSSfactory.setNamespaceAware(true);//makes the parser aware of namespaces
            XmlPullParser RSSxmlPP = parseRSSfactory.newPullParser();//creates the new xml pull parser
            String xmlRSS = getStringFromInputStream(getInputStream(rssURL), "UTF-8");//turning the feed into a string after retrieving it
            RSSxmlPP.setInput(new StringReader(xmlRSS));//
            int eventType = RSSxmlPP.getEventType();//get the event type

            parseRSSDataItem(RSSxmlPP,eventType);//call  parseDataItem function and pass in the parameters ie the feed

        }
        catch (XmlPullParserException ae1)//catch pullparser exceptions
        {
            Log.e("MyTag","Parsing error" + ae1.toString());
        }
        catch (IOException ae1)//catch Input Output exceptions
        {
            Log.e("MyTag","IO error during parsing");
        }

        Log.e("MyTag","End document");
    }

    public InputStream getInputStream(URL url) throws IOException
    {
        return url.openConnection().getInputStream();//opens up connection with the RSSfeed
    }

    public static String getStringFromInputStream(InputStream stream, String charsetName) throws IOException
    {
        //turns the RSS feed into a string
        int n = 0;
        char[] buffer = new char[1024 * 4];//buffer variable to hold the character
        InputStreamReader reader = new InputStreamReader(stream, charsetName);//new input stream reader
        StringWriter writer = new StringWriter();//new string writer
        while (-1 != (n = reader.read(buffer))) writer.write(buffer, 0, n);//character held in buffer read then wrote to the writer
        return writer.toString(); //converts whats in the writer and returns the string to what called the function
    }

}
