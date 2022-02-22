package com.example.mylogin;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.text.Layout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.mylogin.databinding.ActivityMapsBinding;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PointOfInterest;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.net.PlacesClient;

import java.util.List;

public class MapsActivity extends AppCompatActivity implements GoogleMap.OnPoiClickListener{


    SupportMapFragment supportMapFragment;
    FusedLocationProviderClient client;

    private ActivityMapsBinding binding;
    private GoogleMap temp;
    private PlacesClient placesClient;
    private static final int M_MAX_ENTRIES = 5;
    private String[] likelyPlaceNames;
    private String[] likelyPlaceAddresses;
    private List[] likelyPlaceAttributions;
    private LatLng[] likelyPlaceLatLngs;
    private String key = "AIzaSyCG1STojkyeKsRXgKAGq0pliHs1xNRLqtk";


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_maps);

        Places.initialize(getApplicationContext(), key);
        placesClient = Places.createClient(this);


      Object mPlaceDetectionClient = Places.createClient(this);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        supportMapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.google_map);
        //Initialize Fuse Location
        client = LocationServices.getFusedLocationProviderClient(this);

        //Check permissions
        if (ActivityCompat.checkSelfPermission(MapsActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            //When permission granted
            //Call Method
            getCurrentLocation();
        }
            else{
            ActivityCompat.requestPermissions(MapsActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }
    }

    private void getCurrentLocation() {

        Task<Location> task;

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

            return;
        }
        task = client.getLastLocation();






        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                //When success
                if (location != null) {
                    //Sync map
                    supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(GoogleMap googleMap) {
                            //Initialize lat , lng
                            LatLng latlng = new LatLng(location.getLatitude(), location.getLongitude());
                            //Create marker options
                            MarkerOptions options = new MarkerOptions().position(latlng).title("I am here");
                            //Zoom map
                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latlng, 10));
                            //Add marker on map
                            googleMap.addMarker(options);
                            setMapLongClick(googleMap);
                            setPoiClick(googleMap);

                        }
                    });
                }

            }
        });

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 44) {
            if (grantResults.length > 0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                //When permission granted
                //Call method
                getCurrentLocation();
            }
        }
    }

    private void setPoiClick(final GoogleMap map)
    {
         temp = map;
        map.setOnPoiClickListener(new GoogleMap.OnPoiClickListener()
        {


            @Override
            public void onPoiClick(PointOfInterest pointOfInterest)
            {
                Marker poiMarker = temp.addMarker(new MarkerOptions()

                        .position(pointOfInterest.latLng)
                        .title(pointOfInterest.name));
                poiMarker.showInfoWindow();
            }
        });

    }

    private void setMapLongClick(final GoogleMap map) {
        map.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {

            @Override
            public void onMapLongClick(LatLng latLng)
            {
                map.addMarker(new MarkerOptions().position(latLng));
            }
        });
    }

    @Override
    public void onPoiClick(PointOfInterest pointOfInterest)
    {
        Marker poiMarker = temp.addMarker(new MarkerOptions()

                .position(pointOfInterest.latLng)
                .title(pointOfInterest.name));
        poiMarker.showInfoWindow();


        };


    }




