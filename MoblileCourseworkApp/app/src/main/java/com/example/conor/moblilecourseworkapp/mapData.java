package com.example.conor.moblilecourseworkapp;

import java.io.Serializable;

/**
 * Created by conor on 16/12/2015.
 */
public class mapData implements Serializable {
// *********************************************
// Declare variables etc.
// *********************************************

    private int entryID;
    private String Surname;
    private String Firstname;
    private String StarSign;
    private String Occupation;
    private float Latitude;
    private float Longitude;

    private static final long serialVersionUID = 0L;

// *********************************************
// Declare getters and setters etc.
// *********************************************


    public int getEntryID() {
        return entryID;
    }//gets and returns the entryID

    public void setEntryID(int entryID) {
        this.entryID = entryID;
    }//sets the entryId to the passed value

    public String getSurname() {
        return Surname;
    }//gets and returns the surname

    public void setSurname(String surname) {
        this.Surname = surname;
    }//sets the surname to the passed value

    public String getFirstname() {
        return Firstname;
    }//gets and returns the firstname

    public void setFirstname(String firstname) {
        this.Firstname = firstname;
    }//sets the firstname to the passed value

    public String getStarSign() {
        return StarSign;
    }//gets and returns starsign

    public void setStarSign(String starSign) {
        this.StarSign = starSign;
    }//sets starsign to the passed value

    public String getOccupation() {
        return Occupation;
    }//gets and returns occupation

    public void setOccupation(String occupation) {
        this.Occupation = occupation;
    }//sets occupation to the passed value

    public float getLatitude()
    {
        return Latitude;
    }//gets and returns latitude

    public void setLatitude(float Lat)
    {
        this.Latitude = Lat;
    }//sets latitude to the passed value

    public float getLongitude()
    {
        return Longitude;
    }//gets and returns the longitude

    public void setLongitude(float fLongitude)
    {
        this.Longitude = fLongitude;
    }//sets the longitude to the passed value

    @Override
    public String toString() {//converts the data into a string output
        String mapData;
        mapData = "mcStarSignsInfo [entryID=" + entryID;
        mapData = ", Surname=" + Surname;
        mapData = ", Firstname=" + Firstname;
        mapData = ", StarSign=" + StarSign;
        mapData = ", Occupation=" + Occupation;
        mapData = ", Latitude=" + Latitude;
        mapData = ", Longitude=" + Longitude +"]";
        return mapData;//returns the mapData string
    }

}