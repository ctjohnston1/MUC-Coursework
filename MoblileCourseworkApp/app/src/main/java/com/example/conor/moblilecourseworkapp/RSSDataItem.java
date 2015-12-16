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
    private String takenSpaces; //taken spaces holder
//private ArrayList<String> arr = new ArrayList<String>();


    @Override
    public String toString() {
        //turns CarParkData into one large string value
        String CarParkData;
        CarParkData = "RSSDataItem CarPark=" + carPark;
        CarParkData = ", Spaces=" +  occupancy;
        CarParkData = ", link=" + capacity;
        CarParkData = ",longitude"+ longitude;
        CarParkData = ",latitude"+ latitude;
        CarParkData = ",takenSpaces"+takenSpaces;
   //    CarParkData = ", arr=" + arr.get(i);

        return CarParkData;//returns CarParkData

    }





    public String getCarPark() {//gets car park name
        Log.e("CAR PARK FOUND",carPark);


        return carPark; //returns car park name


    }

    public void setCarPark(String name) {//sets car park name
        this.carPark = name; //sets this instance of car park name equal to the value of the information passed into it.

    }

    public String getOccupancy() {//gets occupancy
        return occupancy;//returns the value for occupancy
    }

    public String getLongitude(){//gets longitude
        return longitude;//returns the value for longitude
    }
    public String getLatitude(){//gets latitude
        return latitude;//returns the value for latitude
    }
public String getTakenSpaces(){//gets taken places
    return takenSpaces;//returns the value for taken places
}
    public void setTakenSpaces(String takenSpaces){//sets taken places
        this.takenSpaces=takenSpaces;//this instance of taken places is equal to the value of the information passed in
    }
    public void setLatitude(String latitude) {//set latitude
        this.latitude = latitude;//this instance of latitude is equal to the value of the information passed in
    }

    public void setOccupancy(String value) {//set occupancy value
        this.occupancy = value;//this instance of occpancy is equal to the value of the information passed in
    }

    public String getCapacity() {
        return capacity;
    }//gets and returns the value for capacity

    public void setCapacity(String totvalue) {
        this.capacity = totvalue;
    }//sets the value for this instance of capacity equal to the value of the data being passed in

    public void setLongitude(String location){
        this.longitude = location;
    }//sets this instance of longitude equal the value of the data being passed in

}


