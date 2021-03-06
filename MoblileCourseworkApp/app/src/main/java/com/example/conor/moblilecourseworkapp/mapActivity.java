package com.example.conor.moblilecourseworkapp;

/**
 * Created by conor on 15/12/2015.
 */
import android.annotation.TargetApi;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
public class mapActivity extends FragmentActivity{
  List<mapData> mapDataList;
    private Marker[] mapDataMarkerList = new Marker[5];
    private GoogleMap parkLocations;
    private float markerColours[] = {210.0f, 120.0f, 330.0f , 270.0f};
    private LatLng location = new LatLng(55.85977240,-4.239331652);

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.map_view);
        mapDataList = new ArrayList<mapData>();
        mapDataDBmgr mapDB = new mapDataDBmgr(this, "mapEKFamous5.s3db", null, 1);
        try {
            mapDB.dbCreate();
        } catch (IOException e) {
            e.printStackTrace();
        }


        mapDataList = mapDB.allMapData();
        SetUpMap();
        AddMarkers();
    }

    public void SetUpMap(){
       parkLocations = ((MapFragment)getFragmentManager().findFragmentById(R.id.map)).getMap();
        if(parkLocations != null) {
            parkLocations.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 12));//moves the camera to the designated latitude and longitude at the zoom factor specified
            parkLocations.setMyLocationEnabled(true);//turns on the GPS
            parkLocations.getUiSettings().setCompassEnabled(true);//turns on the compass
            parkLocations.getUiSettings().setMyLocationButtonEnabled(true);//turns on the location buttons
            parkLocations.getUiSettings().setRotateGesturesEnabled(true);

        }
    }
    public void AddMarkers(){
        MarkerOptions marker;
        mapData mData;
        String markTitle;
        String markText;

        //for all the marker options in dbList
        for(int i = 0; i < mapDataList.size(); i++){
            mData = mapDataList.get(i);
            markTitle = mData.getFirstname()+ " "+ mData.getSurname() + "  "+ mData.getOccupation();
            markText = "Information to be entered";
            marker = SetMarker(markTitle,markText, new LatLng(mData.getLatitude(), mData.getLongitude()), markerColours[i],true);
            mapDataMarkerList[i] = parkLocations.addMarker(marker);
        }
    }
    public MarkerOptions SetMarker(String title, String snippet, LatLng location, float markerColour, boolean centreAnchor){
        float anchorX; //create anchorX
        float anchorY; //create anchorY
        if(centreAnchor){
            anchorX = 0.5f; ///center X
            anchorY = 0.5f; //center Y

        }
        else{
            anchorX = 0.5f; //center x
            anchorY = 1.0f; //bottom y
        }
        //create marker options from the input variables
        MarkerOptions marker = new MarkerOptions().title(title).snippet(snippet).icon(BitmapDescriptorFactory.defaultMarker(markerColour)).anchor(anchorX,anchorY).position(location).draggable(centreAnchor);
        return marker;

    }
}
