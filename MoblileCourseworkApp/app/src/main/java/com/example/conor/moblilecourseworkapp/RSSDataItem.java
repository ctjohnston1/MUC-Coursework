package com.example.conor.moblilecourseworkapp;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by conor on 14/12/2015.
 */
public class RSSDataItem implements Serializable{
    private String carPark;// car park id holder
    private String occupancy;//occupancy holder
    private String capacity; //capacity holder
    private String longitude; //for maps
    private String latitude; //for maps
//private ArrayList<String> arr = new ArrayList<String>();


    @Override
    public String toString() {
        String CarParkData;
        CarParkData = "RSSDataItem CarPark=" + carPark;
        CarParkData = ", Spaces=" +  occupancy;
        CarParkData = ", link=" + capacity;
        CarParkData = ",longitude"+ longitude;
        CarParkData = ",latitude"+ latitude;
   //    CarParkData = ", arr=" + arr.get(i);

        return CarParkData;

    }





    public String getCarPark() {
        Log.e("CAR PARK FOUND",carPark);


        return carPark;


    }

    public void setCarPark(String name) {
        this.carPark = name;



    }

    public String getOccupancy() {
        return occupancy;
    }

    public String getLongitude(){
        return longitude;
    }
    public String getLatitude(){
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setOccupancy(String value) {
        this.occupancy = value;
    }

    public String getLink() {
        return capacity;
    }

    public void setCapacity(String totvalue) {
        this.capacity = totvalue;
    }

    public void setLongitude(String location){
        this.longitude = location;
    }

}


