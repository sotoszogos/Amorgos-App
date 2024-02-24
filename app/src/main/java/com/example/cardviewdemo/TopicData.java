package com.example.cardviewdemo;

import android.content.Context;
import android.util.Log;

import org.osmdroid.util.GeoPoint;

import java.util.ArrayList;
import java.util.List;

public class TopicData {

    //upokathista mia vasi dedomenon
    //dimiourgei ta dat stin mnimi

    private static List<Topic> topics = new ArrayList<Topic>();
    private static List<POI> pois = new ArrayList<POI>();

    public TopicData(Context context){  //KATASKEVASTIS TOPICDATA
        createTopicsAndPOIs(context);

        for (Topic topic : topics)
            Log.d("XXX", topic.getName());
    }

    public List<Topic> getTopics(){
        return topics;
    }

    public Topic getTopic(String topicName){

        for (Topic topic : topics) {
            if (topic.getName().equals(topicName))
                return topic;
        }

        return null;
    }

    public List<POI> getPOIsForTopic(String topicName){
        ArrayList<POI> poisForTopic = new ArrayList<POI>();

        for (POI poi : pois) {
            if (poi.getPOIGroup().equals(topicName))
                poisForTopic.add(poi);
        }

        return poisForTopic;
    }

    private void createTopicsAndPOIs(Context context){

        //create fields for topics
        //create topic names from resources
        int[] topicNamesIDs = new int[]{
                R.string.topic_name_0,
                R.string.topic_name_1,
                R.string.topic_name_2,
                R.string.topic_name_3,
                R.string.topic_name_4,
                R.string.topic_name_5,
                R.string.topic_name_6,
                R.string.topic_name_7
        };

        //create topic images from resources
        int[] topicImageIDs = new int[]{
                R.drawable.topic_image_id_0,
                R.drawable.topic_image_id_1,
                R.drawable.topic_image_id_2,
                R.drawable.topic_image_id_3,
                R.drawable.topic_image_id_4,
                R.drawable.topic_image_id_5,
                R.drawable.topic_image_id_6,
                R.drawable.topic_image_id_7
        };

        //create topic descriptions from resources
        int[] topicDescriptionsIDs = new int[]{
                R.string.topic_description_0,
                R.string.topic_description_1,
                R.string.topic_description_2,
                R.string.topic_description_3,
                R.string.topic_description_4,
                R.string.topic_description_5,
                R.string.topic_description_6,
                R.string.topic_description_7
        };

//Topic 0, POIs 00, 01, 02.
        //Create a topic //TA APOKATW EINAI GIA NA PARW TA DATA
        Topic t0 = new Topic(context.getResources().getString(topicNamesIDs[0]), 3, topicImageIDs[0],
                context.getResources().getString(R.string.topic_image_0_content_description),
                context.getResources().getString(topicDescriptionsIDs[0]));
        //Add topic to list
        topics.add(t0);

        //Create POIs for Topic.
        //Each topic has highlights with title, description and geo point.
        // Data is in strings.xml file and R.drawable folder.

        // GIA TO POS FTIAXNETE ENA HIGHLIGHT

        //create array list of highlights for topic 0. (each topic has multiple highlights)
        String[] highlights = context.getResources().getStringArray(R.array.topic_0_highlights);

        //create resource id for geo point of highlight
        int resID = context.getResources().getIdentifier("topic_0_highlight_0_geopoint", "array", context.getPackageName());
        //get geo point data for highlight
        String[] POIGeoPointStringArray = context.getResources().getStringArray(resID);
        // create Geo point and add to array (need to convert strings --> doubles
        GeoPoint geoPoint = new GeoPoint(
                Double.valueOf(POIGeoPointStringArray[0]),
                Double.valueOf(POIGeoPointStringArray[1]));
        //create POI
        POI poi00 = new POI(t0.getName(),
                highlights[0],
                geoPoint,
                R.drawable.topic_0_highlight_0_image_id);
        //add to POIs array list
        pois.add(poi00);

        //create resource id for geo point of highlight
        resID = context.getResources().getIdentifier("topic_0_highlight_1_geopoint", "array", context.getPackageName());
        //get geo point data for highlight
        POIGeoPointStringArray = context.getResources().getStringArray(resID);
        // create Geo point and add to array (need to convert strings --> doubles
        geoPoint = new GeoPoint(
                Double.valueOf(POIGeoPointStringArray[0]),
                Double.valueOf(POIGeoPointStringArray[1]));
        //create POI
        POI poi01 = new POI(t0.getName(),
                highlights[1],
                geoPoint,
                R.drawable.topic_0_highlight_1_image_id);
        //add to POIs array list
        pois.add(poi01);

        //create resource id for geo point of highlight
        resID = context.getResources().getIdentifier("topic_0_highlight_2_geopoint", "array", context.getPackageName());
        //get geo point data for highlight
        POIGeoPointStringArray = context.getResources().getStringArray(resID);
        // create Geo point and add to array (need to convert strings --> doubles
        geoPoint = new GeoPoint(
                Double.valueOf(POIGeoPointStringArray[0]),
                Double.valueOf(POIGeoPointStringArray[1]));
        //create POI
        POI poi02 = new POI(t0.getName(),
                highlights[2],
                geoPoint,
                R.drawable.topic_0_highlight_2_image_id);
        //add to POIs array list
        pois.add(poi02);

//Topic 1, POIs 10, 11.
        //Create a topic
        Topic t1 = new Topic(context.getResources().getString(topicNamesIDs[1]), 2, topicImageIDs[1],
                context.getResources().getString(R.string.topic_image_1_content_description),
                context.getResources().getString(topicDescriptionsIDs[1]));
        //Add topic to list
        topics.add(t1);

        //Create POIs for Topic.
        //Each topic has highlights with title, description and geo point.
        // Data is in strings.xml file and R.drawable folder.

        //create array list of highlights for topic 0. (each topic has multiple highlights)
        highlights = context.getResources().getStringArray(R.array.topic_1_highlights);

        //create resource id for geo point of highlight
        resID = context.getResources().getIdentifier("topic_1_highlight_0_geopoint", "array", context.getPackageName());
        //get geo point data for highlight
        POIGeoPointStringArray = context.getResources().getStringArray(resID);
        // create Geo point and add to array (need to convert strings --> doubles
        geoPoint = new GeoPoint(
                Double.valueOf(POIGeoPointStringArray[0]),
                Double.valueOf(POIGeoPointStringArray[1]));
        //create POI
        POI poi10 = new POI(t1.getName(),
                highlights[0],
                geoPoint,
                R.drawable.topic_1_highlight_0_image_id);
        //add to POIs array list
        pois.add(poi10);

        //create resource id for geo point of highlight
        resID = context.getResources().getIdentifier("topic_1_highlight_1_geopoint", "array", context.getPackageName());
        //get geo point data for highlight
        POIGeoPointStringArray = context.getResources().getStringArray(resID);
        // create Geo point and add to array (need to convert strings --> doubles
        geoPoint = new GeoPoint(
                Double.valueOf(POIGeoPointStringArray[0]),
                Double.valueOf(POIGeoPointStringArray[1]));
        //create POI
        POI poi11 = new POI(t1.getName(),
                highlights[1],
                geoPoint,
                R.drawable.topic_1_highlight_1_image_id);
        //add to POIs array list
        pois.add(poi11);

//Topic 2, POIs 20, 21, 22.
        //Create a topic
        Topic t2 = new Topic(context.getResources().getString(topicNamesIDs[2]), 3, topicImageIDs[2],
                context.getResources().getString(R.string.topic_image_2_content_description),
                context.getResources().getString(topicDescriptionsIDs[2]));
        //Add topic to list
        topics.add(t2);

        //Create POIs for Topic.
        //Each topic has highlights with title, description and geo point.
        // Data is in strings.xml file and R.drawable folder.

        //create array list of highlights for topic 0. (each topic has multiple highlights)
        highlights = context.getResources().getStringArray(R.array.topic_2_highlights);

        //create resource id for geo point of highlight
        resID = context.getResources().getIdentifier("topic_2_highlight_0_geopoint", "array", context.getPackageName());
        //get geo point data for highlight
        POIGeoPointStringArray = context.getResources().getStringArray(resID);
        // create Geo point and add to array (need to convert strings --> doubles
        geoPoint = new GeoPoint(
                Double.valueOf(POIGeoPointStringArray[0]),
                Double.valueOf(POIGeoPointStringArray[1]));
        //create POI
        POI poi20 = new POI(t2.getName(),
                highlights[0],
                geoPoint,
                R.drawable.topic_2_highlight_0_image_id);
        //add to POIs array list
        pois.add(poi20);

        //create resource id for geo point of highlight
        resID = context.getResources().getIdentifier("topic_2_highlight_1_geopoint", "array", context.getPackageName());
        //get geo point data for highlight
        POIGeoPointStringArray = context.getResources().getStringArray(resID);
        // create Geo point and add to array (need to convert strings --> doubles
        geoPoint = new GeoPoint(
                Double.valueOf(POIGeoPointStringArray[0]),
                Double.valueOf(POIGeoPointStringArray[1]));
        //create POI
        POI poi21 = new POI(t2.getName(),
                highlights[1],
                geoPoint,
                R.drawable.topic_2_highlight_1_image_id);
        //add to POIs array list
        pois.add(poi21);

        //create resource id for geo point of highlight
        resID = context.getResources().getIdentifier("topic_2_highlight_2_geopoint", "array", context.getPackageName());
        //get geo point data for highlight
        POIGeoPointStringArray = context.getResources().getStringArray(resID);
        // create Geo point and add to array (need to convert strings --> doubles
        geoPoint = new GeoPoint(
                Double.valueOf(POIGeoPointStringArray[0]),
                Double.valueOf(POIGeoPointStringArray[1]));
        //create POI
        POI poi22 = new POI(t2.getName(),
                highlights[2],
                geoPoint,
                R.drawable.topic_2_highlight_2_image_id);
        //add to POIs array list
        pois.add(poi22);

//Topic 3, POIs 30, 31, 32, 33, 34, 35.
        //Create a topic
        Topic t3 = new Topic(context.getResources().getString(topicNamesIDs[3]), 6, topicImageIDs[3],
                context.getResources().getString(R.string.topic_image_3_content_description),
                context.getResources().getString(topicDescriptionsIDs[3]));
        //Add topic to list
        topics.add(t3);

        //Create POIs for Topic.
        //Each topic has highlights with title, description and geo point.
        // Data is in strings.xml file and R.drawable folder.

        //create array list of highlights for topic 0. (each topic has multiple highlights)
        highlights = context.getResources().getStringArray(R.array.topic_3_highlights);

        //create resource id for geo point of highlight
        resID = context.getResources().getIdentifier("topic_3_highlight_0_geopoint", "array", context.getPackageName());
        //get geo point data for highlight
        POIGeoPointStringArray = context.getResources().getStringArray(resID);
        // create Geo point and add to array (need to convert strings --> doubles
        geoPoint = new GeoPoint(
                Double.valueOf(POIGeoPointStringArray[0]),
                Double.valueOf(POIGeoPointStringArray[1]));
        //create POI
        POI poi30 = new POI(t3.getName(),
                highlights[0],
                geoPoint,
                R.drawable.topic_3_highlight_0_image_id);
        //add to POIs array list
        pois.add(poi30);

        //create resource id for geo point of highlight
        resID = context.getResources().getIdentifier("topic_3_highlight_1_geopoint", "array", context.getPackageName());
        //get geo point data for highlight
        POIGeoPointStringArray = context.getResources().getStringArray(resID);
        // create Geo point and add to array (need to convert strings --> doubles
        geoPoint = new GeoPoint(
                Double.valueOf(POIGeoPointStringArray[0]),
                Double.valueOf(POIGeoPointStringArray[1]));
        //create POI
        POI poi31 = new POI(t3.getName(),
                highlights[1],
                geoPoint,
                R.drawable.topic_3_highlight_1_image_id);
        //add to POIs array list
        pois.add(poi31);

        //create resource id for geo point of highlight
        resID = context.getResources().getIdentifier("topic_3_highlight_2_geopoint", "array", context.getPackageName());
        //get geo point data for highlight
        POIGeoPointStringArray = context.getResources().getStringArray(resID);
        // create Geo point and add to array (need to convert strings --> doubles
        geoPoint = new GeoPoint(
                Double.valueOf(POIGeoPointStringArray[0]),
                Double.valueOf(POIGeoPointStringArray[1]));
        //create POI
        POI poi32 = new POI(t3.getName(),
                highlights[2],
                geoPoint,
                R.drawable.topic_3_highlight_2_image_id);
        //add to POIs array list
        pois.add(poi32);

        //create resource id for geo point of highlight
        resID = context.getResources().getIdentifier("topic_3_highlight_3_geopoint", "array", context.getPackageName());
        //get geo point data for highlight
        POIGeoPointStringArray = context.getResources().getStringArray(resID);
        // create Geo point and add to array (need to convert strings --> doubles
        geoPoint = new GeoPoint(
                Double.valueOf(POIGeoPointStringArray[0]),
                Double.valueOf(POIGeoPointStringArray[1]));
        //create POI
        POI poi33 = new POI(t3.getName(),
                highlights[3],
                geoPoint,
                R.drawable.topic_3_highlight_3_image_id);
        //add to POIs array list
        pois.add(poi33);

        //create resource id for geo point of highlight
        resID = context.getResources().getIdentifier("topic_3_highlight_4_geopoint", "array", context.getPackageName());
        //get geo point data for highlight
        POIGeoPointStringArray = context.getResources().getStringArray(resID);
        // create Geo point and add to array (need to convert strings --> doubles
        geoPoint = new GeoPoint(
                Double.valueOf(POIGeoPointStringArray[0]),
                Double.valueOf(POIGeoPointStringArray[1]));
        //create POI
        POI poi34 = new POI(t3.getName(),
                highlights[4],
                geoPoint,
                R.drawable.topic_3_highlight_4_image_id);
        //add to POIs array list
        pois.add(poi34);

        //create resource id for geo point of highlight
        resID = context.getResources().getIdentifier("topic_3_highlight_5_geopoint", "array", context.getPackageName());
        //get geo point data for highlight
        POIGeoPointStringArray = context.getResources().getStringArray(resID);
        // create Geo point and add to array (need to convert strings --> doubles
        geoPoint = new GeoPoint(
                Double.valueOf(POIGeoPointStringArray[0]),
                Double.valueOf(POIGeoPointStringArray[1]));
        //create POI
        POI poi35 = new POI(t3.getName(),
                highlights[5],
                geoPoint,
                R.drawable.topic_3_highlight_5_image_id);
        //add to POIs array list
        pois.add(poi35);

//Topic 4, POIs 40, 41, 42.
        //Create a topic
        Topic t4 = new Topic(context.getResources().getString(topicNamesIDs[4]), 3, topicImageIDs[4],
                context.getResources().getString(R.string.topic_image_4_content_description),
                context.getResources().getString(topicDescriptionsIDs[4]));
        //Add topic to list
        topics.add(t4);

        //Create POIs for Topic.
        //Each topic has highlights with title, description and geo point.
        // Data is in strings.xml file and R.drawable folder.

        //create array list of highlights for topic 0. (each topic has multiple highlights)
        highlights = context.getResources().getStringArray(R.array.topic_4_highlights);

        //create resource id for geo point of highlight
        resID = context.getResources().getIdentifier("topic_4_highlight_0_geopoint", "array", context.getPackageName());
        //get geo point data for highlight
        POIGeoPointStringArray = context.getResources().getStringArray(resID);
        // create Geo point and add to array (need to convert strings --> doubles
        geoPoint = new GeoPoint(
                Double.valueOf(POIGeoPointStringArray[0]),
                Double.valueOf(POIGeoPointStringArray[1]));
        //create POI
        POI poi40 = new POI(t4.getName(),
                highlights[0],
                geoPoint,
                R.drawable.topic_4_highlight_0_image_id);
        //add to POIs array list
        pois.add(poi40);

        //create resource id for geo point of highlight
        resID = context.getResources().getIdentifier("topic_4_highlight_1_geopoint", "array", context.getPackageName());
        //get geo point data for highlight
        POIGeoPointStringArray = context.getResources().getStringArray(resID);
        // create Geo point and add to array (need to convert strings --> doubles
        geoPoint = new GeoPoint(
                Double.valueOf(POIGeoPointStringArray[0]),
                Double.valueOf(POIGeoPointStringArray[1]));
        //create POI
        POI poi41 = new POI(t4.getName(),
                highlights[1],
                geoPoint,
                R.drawable.topic_4_highlight_1_image_id);
        //add to POIs array list
        pois.add(poi41);

        //create resource id for geo point of highlight
        resID = context.getResources().getIdentifier("topic_4_highlight_2_geopoint", "array", context.getPackageName());
        //get geo point data for highlight
        POIGeoPointStringArray = context.getResources().getStringArray(resID);
        // create Geo point and add to array (need to convert strings --> doubles
        geoPoint = new GeoPoint(
                Double.valueOf(POIGeoPointStringArray[0]),
                Double.valueOf(POIGeoPointStringArray[1]));
        //create POI
        POI poi42 = new POI(t4.getName(),
                highlights[2],
                geoPoint,
                R.drawable.topic_4_highlight_2_image_id);
        //add to POIs array list
        pois.add(poi42);

//Topic 5, POIs 50, 51, 52, 53.
        //Create a topic
        Topic t5 = new Topic(context.getResources().getString(topicNamesIDs[5]), 4, topicImageIDs[5],
                context.getResources().getString(R.string.topic_image_5_content_description),
                context.getResources().getString(topicDescriptionsIDs[5]));
        //Add topic to list
        topics.add(t5);

        //Create POIs for Topic.
        //Each topic has highlights with title, description and geo point.
        // Data is in strings.xml file and R.drawable folder.

        //create array list of highlights for topic 0. (each topic has multiple highlights)
        highlights = context.getResources().getStringArray(R.array.topic_5_highlights);

        //create resource id for geo point of highlight
        resID = context.getResources().getIdentifier("topic_5_highlight_0_geopoint", "array", context.getPackageName());
        //get geo point data for highlight
        POIGeoPointStringArray = context.getResources().getStringArray(resID);
        // create Geo point and add to array (need to convert strings --> doubles
        geoPoint = new GeoPoint(
                Double.valueOf(POIGeoPointStringArray[0]),
                Double.valueOf(POIGeoPointStringArray[1]));
        //create POI
        POI poi50 = new POI(t5.getName(),
                highlights[0],
                geoPoint,
                R.drawable.topic_5_highlight_0_image_id);
        //add to POIs array list
        pois.add(poi50);

        //create resource id for geo point of highlight
        resID = context.getResources().getIdentifier("topic_5_highlight_1_geopoint", "array", context.getPackageName());
        //get geo point data for highlight
        POIGeoPointStringArray = context.getResources().getStringArray(resID);
        // create Geo point and add to array (need to convert strings --> doubles
        geoPoint = new GeoPoint(
                Double.valueOf(POIGeoPointStringArray[0]),
                Double.valueOf(POIGeoPointStringArray[1]));
        //create POI
        POI poi51 = new POI(t5.getName(),
                highlights[1],
                geoPoint,
                R.drawable.topic_5_highlight_1_image_id);
        //add to POIs array list
        pois.add(poi51);

        //create resource id for geo point of highlight
        resID = context.getResources().getIdentifier("topic_5_highlight_2_geopoint", "array", context.getPackageName());
        //get geo point data for highlight
        POIGeoPointStringArray = context.getResources().getStringArray(resID);
        // create Geo point and add to array (need to convert strings --> doubles
        geoPoint = new GeoPoint(
                Double.valueOf(POIGeoPointStringArray[0]),
                Double.valueOf(POIGeoPointStringArray[1]));
        //create POI
        POI poi52 = new POI(t5.getName(),
                highlights[2],
                geoPoint,
                R.drawable.topic_5_highlight_2_image_id);
        //add to POIs array list
        pois.add(poi52);

        //create resource id for geo point of highlight
        resID = context.getResources().getIdentifier("topic_5_highlight_3_geopoint", "array", context.getPackageName());
        //get geo point data for highlight
        POIGeoPointStringArray = context.getResources().getStringArray(resID);
        // create Geo point and add to array (need to convert strings --> doubles
        geoPoint = new GeoPoint(
                Double.valueOf(POIGeoPointStringArray[0]),
                Double.valueOf(POIGeoPointStringArray[1]));
        //create POI
        POI poi53 = new POI(t5.getName(),
                highlights[3],
                geoPoint,
                R.drawable.topic_5_highlight_3_image_id);
        //add to POIs array list
        pois.add(poi53);

//Topic 6, POIs 60, 61, 62.
        //Create a topic
        Topic t6 = new Topic(context.getResources().getString(topicNamesIDs[6]), 3, topicImageIDs[6],
                context.getResources().getString(R.string.topic_image_6_content_description),
                context.getResources().getString(topicDescriptionsIDs[6]));
        //Add topic to list
        topics.add(t6);

        //Create POIs for Topic.
        //Each topic has highlights with title, description and geo point.
        // Data is in strings.xml file and R.drawable folder.

        //create array list of highlights for topic 0. (each topic has multiple highlights)
        highlights = context.getResources().getStringArray(R.array.topic_6_highlights);

        //create resource id for geo point of highlight
        resID = context.getResources().getIdentifier("topic_6_highlight_0_geopoint", "array", context.getPackageName());
        //get geo point data for highlight
        POIGeoPointStringArray = context.getResources().getStringArray(resID);
        // create Geo point and add to array (need to convert strings --> doubles
        geoPoint = new GeoPoint(
                Double.valueOf(POIGeoPointStringArray[0]),
                Double.valueOf(POIGeoPointStringArray[1]));
        //create POI
        POI poi60 = new POI(t6.getName(),
                highlights[0],
                geoPoint,
                R.drawable.topic_6_highlight_0_image_id);
        //add to POIs array list
        pois.add(poi60);

        //create resource id for geo point of highlight
        resID = context.getResources().getIdentifier("topic_6_highlight_1_geopoint", "array", context.getPackageName());
        //get geo point data for highlight
        POIGeoPointStringArray = context.getResources().getStringArray(resID);
        // create Geo point and add to array (need to convert strings --> doubles
        geoPoint = new GeoPoint(
                Double.valueOf(POIGeoPointStringArray[0]),
                Double.valueOf(POIGeoPointStringArray[1]));
        //create POI
        POI poi61 = new POI(t6.getName(),
                highlights[1],
                geoPoint,
                R.drawable.topic_6_highlight_1_image_id);
        //add to POIs array list
        pois.add(poi61);

        //create resource id for geo point of highlight
        resID = context.getResources().getIdentifier("topic_6_highlight_2_geopoint", "array", context.getPackageName());
        //get geo point data for highlight
        POIGeoPointStringArray = context.getResources().getStringArray(resID);
        // create Geo point and add to array (need to convert strings --> doubles
        geoPoint = new GeoPoint(
                Double.valueOf(POIGeoPointStringArray[0]),
                Double.valueOf(POIGeoPointStringArray[1]));
        //create POI
        POI poi62 = new POI(t6.getName(),
                highlights[2],
                geoPoint,
                R.drawable.topic_6_highlight_2_image_id);
        //add to POIs array list
        pois.add(poi62);

//Topic 7, POIs 70, 71, 72, 73, 74.
        //Create a topic
        Topic t7 = new Topic(context.getResources().getString(topicNamesIDs[7]), 5, topicImageIDs[7],
                context.getResources().getString(R.string.topic_image_7_content_description),
                context.getResources().getString(topicDescriptionsIDs[7]));
        //Add topic to list
        topics.add(t7);

        //Create POIs for Topic.
        //Each topic has highlights with title, description and geo point.
        // Data is in strings.xml file and R.drawable folder.

        //create array list of highlights for topic 0. (each topic has multiple highlights)
        highlights = context.getResources().getStringArray(R.array.topic_7_highlights);

        //create resource id for geo point of highlight
        resID = context.getResources().getIdentifier("topic_7_highlight_0_geopoint", "array", context.getPackageName());
        //get geo point data for highlight
        POIGeoPointStringArray = context.getResources().getStringArray(resID);
        // create Geo point and add to array (need to convert strings --> doubles
        geoPoint = new GeoPoint(
                Double.valueOf(POIGeoPointStringArray[0]),
                Double.valueOf(POIGeoPointStringArray[1]));
        //create POI
        POI poi70 = new POI(t7.getName(),
                highlights[0],
                geoPoint,
                R.drawable.topic_7_highlight_0_image_id);
        //add to POIs array list
        pois.add(poi70);

        //create resource id for geo point of highlight
        resID = context.getResources().getIdentifier("topic_7_highlight_1_geopoint", "array", context.getPackageName());
        //get geo point data for highlight
        POIGeoPointStringArray = context.getResources().getStringArray(resID);
        // create Geo point and add to array (need to convert strings --> doubles
        geoPoint = new GeoPoint(
                Double.valueOf(POIGeoPointStringArray[0]),
                Double.valueOf(POIGeoPointStringArray[1]));
        //create POI
        POI poi71 = new POI(t7.getName(),
                highlights[1],
                geoPoint,
                R.drawable.topic_7_highlight_1_image_id);
        //add to POIs array list
        pois.add(poi71);

        //create resource id for geo point of highlight
        resID = context.getResources().getIdentifier("topic_7_highlight_2_geopoint", "array", context.getPackageName());
        //get geo point data for highlight
        POIGeoPointStringArray = context.getResources().getStringArray(resID);
        // create Geo point and add to array (need to convert strings --> doubles
        geoPoint = new GeoPoint(
                Double.valueOf(POIGeoPointStringArray[0]),
                Double.valueOf(POIGeoPointStringArray[1]));
        //create POI
        POI poi72 = new POI(t7.getName(),
                highlights[2],
                geoPoint,
                R.drawable.topic_7_highlight_2_image_id);
        //add to POIs array list
        pois.add(poi72);

        //create resource id for geo point of highlight
        resID = context.getResources().getIdentifier("topic_7_highlight_3_geopoint", "array", context.getPackageName());
        //get geo point data for highlight
        POIGeoPointStringArray = context.getResources().getStringArray(resID);
        // create Geo point and add to array (need to convert strings --> doubles
        geoPoint = new GeoPoint(
                Double.valueOf(POIGeoPointStringArray[0]),
                Double.valueOf(POIGeoPointStringArray[1]));
        //create POI
        POI poi73 = new POI(t7.getName(),
                highlights[3],
                geoPoint,
                R.drawable.topic_7_highlight_3_image_id);
        //add to POIs array list
        pois.add(poi73);

        //create resource id for geo point of highlight
        resID = context.getResources().getIdentifier("topic_7_highlight_4_geopoint", "array", context.getPackageName());
        //get geo point data for highlight
        POIGeoPointStringArray = context.getResources().getStringArray(resID);
        // create Geo point and add to array (need to convert strings --> doubles
        geoPoint = new GeoPoint(
                Double.valueOf(POIGeoPointStringArray[0]),
                Double.valueOf(POIGeoPointStringArray[1]));
        //create POI
        POI poi74 = new POI(t7.getName(),
                highlights[4],
                geoPoint,
                R.drawable.topic_7_highlight_4_image_id);
        //add to POIs array list
        pois.add(poi74);
    }

}
