package com.example.cardviewdemo;

import static android.speech.tts.TextToSpeech.QUEUE_FLUSH;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.speech.tts.TextToSpeech;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.google.android.material.chip.ChipGroup;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.FolderOverlay;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.ScaleBarOverlay;
import org.osmdroid.views.overlay.compass.CompassOverlay;
import org.osmdroid.views.overlay.compass.InternalCompassOrientationProvider;
import org.osmdroid.views.overlay.gestures.RotationGestureOverlay;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* Shows Topic details and list of highlights (POIs) in clickable chips
 * */
public class TopicDetailActivity extends AppCompatActivity {

    //energopoietai otan o xristis epileksi kati apo to topicsactivity

    // Get topics data list
    private String topicName; //initialized from intent extra
    MapView map = null;
    // variables for asking permissions
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    // the application context
    Context mContext;
    Button ttsButton;
    TextToSpeech t1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_detail);

        mContext = getApplicationContext();
        //load/initialize the osmdroid configuration, this can be done
        Configuration.getInstance().load(mContext, PreferenceManager.getDefaultSharedPreferences(mContext));
        //setting this before the layout is inflated is a good idea
        //it 'should' ensure that the map has a writable location for the map cache, even without permissions
        //if no tiles are displayed, you can try overriding the cache path using Configuration.createTourData().setCachePath
        //see also StorageUtils
        //note, the load method also sets the HTTP User Agent to your application's package name, abusing osm's tile servers will get you banned based on this string

        // Show support bar and back button enabled
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setHomeButtonEnabled(true);
        //change color
//        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimary)));

        // Retrieve string value from Intent
        topicName = getIntent().getStringExtra(getString(R.string.intent_extra_topic_name));

        // Set title
        setTitle(topicName);

        TopicData topicData = new TopicData(getApplicationContext());

        Topic topic = topicData.getTopic(topicName);
        List<POI> pois = topicData.getPOIsForTopic(topicName);

        inflateLayoutForTopic(topic);
        inflateLayoutForMap(pois);
    }


    //Override the onCreateOptionsMenu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    //Change the behavior for Up-button to act as Back-button
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return false;
    }

    //Function to inflate layout by position index
    //     EDW ARXIZIII NA VRISKI

    private void inflateLayoutForTopic(Topic topic) {

        //Get image view and set its resource ID
        ImageView itemImage = findViewById(R.id.topic_detail_image);
        itemImage.setImageResource(topic.getThumbnailResourceID());


        //For accessibility, add content description for each image view! Must first create
        itemImage.setContentDescription(topic.getImageContentDescription());

        //Get title view and set its value
        TextView topicNameTxtView = findViewById(R.id.topic_name);
        topicNameTxtView.setText(topic.getName());

        //Get num of POIs view and set its value
        TextView numPOIs = findViewById(R.id.numPOIs);
        numPOIs.setText(topic.getNumOfPOIs() + " " + mContext.getResources().getString(R.string.item_highlight_txt));

        //Get overview view and set its value
        TextView topicOverview = findViewById(R.id.topic_overview);
        topicOverview.setText(topic.getDescription());

        String stringToBeSpoken = topicNameTxtView.getText().toString() + ".THERE ARE "+
                numPOIs.getText().toString() + ". The overview of this point of interest is: " +
                topicOverview.getText().toString();

        t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (t1 != null && status != TextToSpeech.SUCCESS) {
                    t1.setLanguage(Locale.UK);
                }
            }



        });


        Button ttsButton = findViewById(R.id.button_tts);
        ttsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), stringToBeSpoken, Toast.LENGTH_LONG).show();


                t1.speak(stringToBeSpoken, TextToSpeech.QUEUE_FLUSH, null);

            }
            public void stopTextToSpeech() {
                if ( t1 != null) {
                    t1.shutdown();
                }
            }

        });



    }

    private void inflateLayoutForMap(List<POI> pois){
        //make map
        map = findViewById(R.id.map);
        map.setTileSource(TileSourceFactory.DEFAULT_TILE_SOURCE);
        //map.setTileSource(new XYTileSource("OSM", 16, 19, 256, ".png", new String[] {}));
        //created tiles with Maperitive, and placed them in Assets folder in "OSM" folder name
        //give start point to the map and add some functionality to it
        addMapFunctionality(pois);
        //add POIs
        addPOIsToMap(pois); //pass names of POIs in a string array
        //handle permissions first, to show user location (shown in callback
        // onRequestPermissionsResult)
        getLocationPermission();
    }

    private void getLocationPermission() {
        // declare the permissions to be checked / requested
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION};
        // check permissions (if already granted)
        if (ContextCompat.checkSelfPermission(this,
                permissions[0]) == PackageManager.PERMISSION_GRANTED) {
            //permission already granted (from previous session)
            setUserLocationEnabled();
        } else { // not granted (from previous interaction)
            // request permissions
            ActivityCompat.requestPermissions(this, permissions,
                    LOCATION_PERMISSION_REQUEST_CODE);
            // a code is passed, that will be checked in onRequestPermissionResult callback
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        // switch request code, to identify which which permission was requested this time..
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                setUserLocationEnabled();
            }
        }
    }

    private void setUserLocationEnabled(){
        GpsMyLocationProvider gpsMyLocationProvider = new GpsMyLocationProvider(mContext);
        gpsMyLocationProvider.setLocationUpdateMinDistance(100); // [m]  // Set the minimum distance for location updates
        gpsMyLocationProvider.setLocationUpdateMinTime(100000);   // [ms] // Set the minimum time interval for location updates
        MyLocationNewOverlay mLocationOverlay = new MyLocationNewOverlay(gpsMyLocationProvider, map);
        mLocationOverlay.enableMyLocation();
        map.getOverlays().add(mLocationOverlay);
    }

    //adds POIs
    private void addPOIsToMap(List<POI> pois) {
        FolderOverlay poiMarkers = new FolderOverlay(this);
        map.getOverlays().add(poiMarkers);

        for (POI poi : pois) {
            Marker poiMarker = new Marker(map);
            poiMarker.setTitle(poi.getPOIGroup());
            poiMarker.setSnippet(poi.getTitle());
            poiMarker.setPosition(poi.getGeopoint());
            poiMarker.setIcon(getResources().getDrawable(poi.getPinResourceID()));
            poiMarker.setImage(getResources().getDrawable(poi.getImageResourceID()));
            poiMarkers.add(poiMarker);
        }
        // refresh the map
        map.invalidate();
    }

    // helper method that adds functionality to the map
    private void addMapFunctionality(List<POI> pois) {
        // add multitouch for zoom in/out
        map.setMultiTouchControls(true);
        IMapController mapController = map.getController();
        // set zoom level
        mapController.setZoom(16);
        // set initial geopoint
        GeoPoint startPoint = computeCentralGeoPoint(pois);
        mapController.setCenter(startPoint);
        // add rotation functionality
        RotationGestureOverlay mRotationGestureOverlay = new RotationGestureOverlay(mContext, map);
        mRotationGestureOverlay.setEnabled(true);
        map.setMultiTouchControls(true);
        map.getOverlays().add(mRotationGestureOverlay);
        // add a scale bar
        final DisplayMetrics dm = getResources().getDisplayMetrics();
        ScaleBarOverlay mScaleBarOverlay = new ScaleBarOverlay(map);
        mScaleBarOverlay.setCentred(false);
        //play around with these values to get the location on screen in the right place for your application
        mScaleBarOverlay.setScaleBarOffset(dm.widthPixels / 2, 10);
        map.getOverlays().add(mScaleBarOverlay);
        //add compass
        CompassOverlay mCompassOverlay = new CompassOverlay(mContext, new InternalCompassOrientationProvider(mContext), map);
        mCompassOverlay.enableCompass();
        map.getOverlays().add(mCompassOverlay);
    }

    // computes the centre of the map to be shown, based on POI Geo points of a Poi group
    public static GeoPoint computeCentralGeoPoint(List<POI> pois) {
        //compute...
        double latTotal = 0;
        double lonTotal = 0;
        int totalGeoPoints = 0;
        // parse pois to find all geo points for this POI group
        for (POI poi : pois) {
            latTotal += poi.getGeopoint().getLatitude();
            lonTotal += poi.getGeopoint().getLongitude();
            totalGeoPoints += 1;
        }
        if (totalGeoPoints != 0) {
            return new GeoPoint(latTotal / totalGeoPoints, lonTotal / totalGeoPoints);
        }
        else
            return null;
    }


    public void onResume(){
        super.onResume();
        //this will refresh the osmdroid configuration on resuming.
        //if you make changes to the configuration, use
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(mContext);
        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(mContext));
//        map.onResume(); //needed for compass, my location overlays, v6.0.0 and up
    }

    public void onPause(){
        super.onPause();
        //this will refresh the osmdroid configuration on resuming.
        //if you make changes to the configuration, use
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(mContext);
        Configuration.getInstance().save(mContext, prefs);
//        map.onPause();  //needed for compass, my location overlays, v6.0.0 and up
    }

}