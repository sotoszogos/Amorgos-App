package com.example.cardviewdemo;

import androidx.annotation.NonNull;

/* A topic of a tour, which has several POIs (points of interest)
* */
public class Topic {
    // data for condensed view
    private String name;
    private int numOfPOIs;
    private int thumbnailResourceID; //arithmos apo ta drawble
    private String imageContentDescription; //accessibility. TO VLEPOUME MONO STO TEXT TO SPEECH
    private String description;

    // constructor with all parameters //DIMIOURGOUN ANTIKIMENA THS KLASIS KAI KALITE STO TOPIC DATA
    public Topic(@NonNull String name, int numOfPOIs, int thumbnailResourceID,
                 String imageContentDescription, String description){
        this.name = name;
        this.numOfPOIs = numOfPOIs;
        this.thumbnailResourceID = thumbnailResourceID;
        this.imageContentDescription = imageContentDescription;
        this.description = description;
    }

    public @NonNull
    String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public int getThumbnailResourceID() {
        return thumbnailResourceID;
    }

    public void setThumbnailResourceID(int thumbnailResourceID) {
        this.thumbnailResourceID = thumbnailResourceID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public int getNumOfPOIs() {
        return numOfPOIs;
    }

    public void setNumOfPOIs(int numOfPOIs) {
        this.numOfPOIs = numOfPOIs;
    }

    public String getImageContentDescription() {
        return imageContentDescription;
    }

    public void setImageContentDescription(String imageContentDescription) {
        this.imageContentDescription = imageContentDescription;
    }
}
