package com.example.cardviewdemo;

import androidx.annotation.NonNull;
import org.osmdroid.util.GeoPoint;

public class POI {

    //points of interests
    private String POIGroup; //group or category of the POI
    private String title;
    private GeoPoint geopoint;
    private int imageResourceID;
    private int pinResourceID;
    private final static int DEFAULT_PIN_RESOURCE_ID = R.drawable.ic_pin_drop_black_24dp;

    // constructor for all data
    public POI(String POIGroup, @NonNull String title, GeoPoint geopoint,
               int imageResourceID){
        this.POIGroup = POIGroup;
        this.title = title;
        this.geopoint = geopoint;
        this.imageResourceID = imageResourceID;
        this.pinResourceID = DEFAULT_PIN_RESOURCE_ID;
    }

    public String getPOIGroup() {
        return POIGroup;
    }

    public void setPOIGroup(String POIGroup) {
        this.POIGroup = POIGroup;
    }

    public @NonNull
    String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    public GeoPoint getGeopoint() {
        return geopoint;
    }

    public void setGeopoint(GeoPoint geopoint) {
        this.geopoint = geopoint;
    }

    public int getImageResourceID() {
        return imageResourceID;
    }

    public void setImageResourceID(int imageResourceID) {
        this.imageResourceID = imageResourceID;
    }

    public int getPinResourceID() {
        return pinResourceID;
    }

    public void setPinResourceID(int pinResourceID) {
        this.pinResourceID = pinResourceID;
    }

    public static int getDefaultPinResourceId() {
        return DEFAULT_PIN_RESOURCE_ID;
    }

}
