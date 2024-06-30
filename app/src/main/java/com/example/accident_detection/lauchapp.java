package com.example.accident_detection;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.telephony.SmsManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
public class lauchapp extends AppCompatActivity implements OnMapReadyCallback {
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private static final int SMS_PERMISSION_REQUEST_CODE=2;
    private MapView mapView;
    private GoogleMap googleMap;
    TextView title_l;
    private FusedLocationProviderClient fusedLocationProviderClient;

    // Define the coordinates of the police stations
    private ArrayList<LatLng> policeStations = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lauchapp);
        Intent it= getIntent();
        String titlet = it.getStringExtra("type");
        title_l= findViewById(R.id.title);
        title_l.setText(titlet);

        // Add coordinates of the police stations
        policeStations.add(new LatLng(10.9912, 76.8424)); // Example coordinates, add all 10 police stations like this
        policeStations.add(new LatLng(11.0054, 76.9499));
        policeStations.add(new LatLng(10.9531,76.7879));
        policeStations.add(new LatLng(10.9884,76.9499));

        // Add coordinates for the rest of the police stations...

        // Initialize the map view
        mapView = findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);

        // Initialize FusedLocationProviderClient
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        // Check location permissions
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Request location permission
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_REQUEST_CODE);
        } else {
            // Permission already granted, proceed to load map
            loadMap();
        }


    }

    private void loadMap() {

        mapView.getMapAsync(this);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                loadMap();
            } else {
                Toast.makeText(this, "Location permission denied", Toast.LENGTH_SHORT).show();
            }
        }


    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        this.googleMap = googleMap;

        // Enable user's location
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Handle permissions here.
            return;
        }

        googleMap.setMyLocationEnabled(true);

        // Get user's current location
        fusedLocationProviderClient.getLastLocation()
                .addOnSuccessListener(this, location -> {
                    if (location != null) {
                        // Add a marker at user's location
                        LatLng currentLocation = new LatLng(location.getLatitude(), location.getLongitude());
                        MarkerOptions markerOptions = new MarkerOptions()
                                .position(currentLocation)
                                .title("Your Location");
                        googleMap.addMarker(markerOptions);
                        Double longi =location.getLongitude();
                        Double lat =  location.getLatitude();
                        String message = "&message="+"My current location:  https://maps.google.com/?q=" + lat + "%2C" +longi ;


                        //String message="sent location";
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if (checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                                // Permission already granted, send SMS
                                sendSMS(message);
                                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
                            } else {
                                // Request SMS permission
                                requestPermissions(new String[]{Manifest.permission.SEND_SMS}, 1);

                            }
                        }


                        // Zoom to user's location
                        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 12));

                        // Find the nearest police station
                        LatLng nearestPoliceStation = findNearestPoliceStation(location);

                        // Add marker for the nearest police station
                        if (nearestPoliceStation != null) {
                            googleMap.addMarker(new MarkerOptions()
                                    .position(nearestPoliceStation)
                                    .title("Nearest Police Station"));
                        }
                    }
                });
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }



    private LatLng findNearestPoliceStation(Location userLocation) {
        LatLng nearestStation = null;
        float minDistance = Float.MAX_VALUE;

        for (LatLng station : policeStations) {
            float[] distanceResults = new float[2];
            Location.distanceBetween(userLocation.getLatitude(), userLocation.getLongitude(),
                    station.latitude, station.longitude, distanceResults);
            float distance = distanceResults[0];
            if (distance < minDistance) {
                minDistance = distance;
                nearestStation = station;
            }
        }
        return nearestStation;
    }
    private void sendSMS(String SMS) {
        String phone = "9345892792";
        String SMS1 =SMS;
        try{
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phone,null,SMS1,null,null);
            Toast.makeText(this,"message is sent",Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this,"failed to send",Toast.LENGTH_SHORT).show();
        }
    }



        @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

}



