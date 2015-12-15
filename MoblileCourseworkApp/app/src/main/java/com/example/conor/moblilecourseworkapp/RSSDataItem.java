package com.example.conor.moblilecourseworkapp;

import java.io.Serializable;

/**
 * Created by conor on 14/12/2015.
 */
public class RSSDataItem implements Serializable{
    private String carPark;
    private String occupancy;
    private String capacity;

    @Override
    public String toString() {
        String CarParkData;
        CarParkData = "RSSDataItem CarPark=" + carPark;
        CarParkData = ", Spaces=" +  occupancy;
        CarParkData = ", link=" + capacity;
        return CarParkData;
    }

    public String getCarPark() {
        return carPark;
    }

    public void setCarPark(String name) {
        this.carPark = name;
    }

    public String getOccupancy() {
        return occupancy;
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
}


