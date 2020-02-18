package com.example.location;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button showLocation;
    Button getGeoLocation;
    TextView locationTextView;
    TextView geoLocationTextView;

    private Geocoder geocoder;
    private List<Address> addresses;
    private double latitude;
    private double longitude;
    private FusedLocationProviderClient fusedLocationProviderClient;
    public static final int PERMESSION_ID = 100;
    public static final String API_KEY = "AIzaSyDlOjB_5wrraGf0--9BGeA0zpK7NSc2Yuk";


    private LocationCallback mLocationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            Location mLastLocation = locationResult.getLastLocation();
            latitude = mLastLocation.getLatitude();
            longitude = mLastLocation.getLongitude();
            locationTextView.setText("Lat: " + mLastLocation.getLatitude() + "\n");
            locationTextView.setText(locationTextView.getText() + "Long: " + mLastLocation.getLongitude() + "\n");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showLocation = findViewById(R.id.btnShowLoc);
        locationTextView = findViewById(R.id.locTextView);
        geoLocationTextView = findViewById(R.id.geoTextView);
        getGeoLocation = findViewById(R.id.getGeo);

        geocoder = new Geocoder(this);
        addresses = new ArrayList<>();


        showLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFreshLoc();
            }
        });

        getGeoLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    addresses = geocoder.getFromLocation(latitude, longitude, 1);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (!addresses.isEmpty()) {
                    Log.d("test", addresses.get(0).toString());
                    Toast.makeText(MainActivity.this, addresses.get(0).getAddressLine(0), Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void getFreshLoc() {
        if (checkPermessions()) {
            if (isLocationEnabled()) {
                //request new locaion
                LocationRequest mLocationRequest = new LocationRequest();
                mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
                mLocationRequest.setInterval(0);
                mLocationRequest.setFastestInterval(0);
                //mLocationRequest.setNumUpdates(1);

                fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
                fusedLocationProviderClient.requestLocationUpdates(
                        mLocationRequest, mLocationCallback,
                        Looper.myLooper()
                );

            } else {
                Toast.makeText(this, "Turn on location", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        } else {
            requestPermissions();
        }
    }

    private boolean checkPermessions() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        return false;
    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION},
                PERMESSION_ID);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMESSION_ID) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            }
        }
    }

    private boolean isLocationEnabled() {
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

}


