package com.example.its.citymap;

import android.content.Intent;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    String city = "";
    MapFragment mf;
    GoogleMap map;
    private GoogleApiClient mGoogleApiClient;
    private Location mLocation;
    private double myLat=0;
    private double myLon=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        Intent I = getIntent();
        city = I.getStringExtra("city");
        mf = (MapFragment) getFragmentManager().findFragmentById(R.id.the_map);
        mf.getMapAsync(this);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

    }

    @Override
    public void onMapReady(GoogleMap map) {
        double lat, lon;

        this.map = map;
        switch (city) {
            case "My Location":
                lat = myLat;
                lon = myLon;
                break;
            case "Dublin":
                lat = 53.347860;
                lon = -6.272487;
                break;
            case "Kerry":
                lat = 52.264007;
                lon = -9.686990;
                break;
            case "Belfast":
                lat = 54.602755;
                lon = -5.945180;
                break;
            case "Cork":
                lat = 51.892171;
                lon = -8.475068;
                break;
            case "Galway":
                lat = 53.276533;
                lon = -9.069362;
                break;
            case "Wexford":
                lat = 52.336521;
                lon = -6.462855;
                break;
            default:
                lat = 0;
                lon = 0;
                break;
        }

        map.addMarker(new MarkerOptions()
                        .position(new LatLng(lat, lon))
                        .title("")
        );

        map.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(lat, lon)));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lon), 10));

    }

    @Override
    public void onConnected(Bundle bundle) {
        mLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLocation != null) {
            myLat = mLocation.getLatitude();
            myLon = mLocation.getLongitude();
        } else {
            Toast.makeText(this, "Location not Detected", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i("alert", "Connection Suspended");
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.i("TAG", "Connection failed. Error: " + connectionResult.getErrorCode());
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }
}
