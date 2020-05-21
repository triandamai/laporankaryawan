package com.myapp;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private static final int CODE_MAP = 100;
    private GoogleMap mMap;
    SupportMapFragment mapFragment;

    private TextView resutText;
    private ProgressBar progressBar;
    private Button button;
    private String locality = "..";
    private String country = "..";
    private double lat = 0;
    private double lng = 0;
    private List<Address> addressList = new ArrayList<>();
    private boolean isPicked = false;
    private Toolbar toolbar;
    private MaterialAlertDialogBuilder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        resutText = findViewById(R.id.tv_alamat);
        toolbar = findViewById(R.id.toolbar);
        builder = new MaterialAlertDialogBuilder(MapsActivity.this, R.style.dialog);
        builder.create();
        setActionBar(toolbar);
        getActionBar().setTitle("Pilih Lokasi");

        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        centerToMyLocation();
        // Enable the zoom controls for the map
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.setOnMapClickListener(latLng -> addMarker(latLng));

    }

    private void centerToMyLocation() {

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        mMap.setMyLocationEnabled(true);
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String provider = locationManager.getBestProvider(criteria, true);
        Location location = locationManager.getLastKnownLocation(provider);


        if (location != null) {
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            lat = location.getLatitude();
            lng = location.getLongitude();

            LatLng coordinate = new LatLng(latitude, longitude);
            CameraUpdate yourLocation = CameraUpdateFactory.newLatLngZoom(coordinate, 19);
            mMap.animateCamera(yourLocation);
            addMarker(coordinate);
        }
    }

    private void addMarker(LatLng latLng) {

        Geocoder geocoder = new Geocoder(MapsActivity.this);

        try {
            addressList = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
            if (addressList != null && addressList.size() > 0) {
                locality = addressList.get(0).getAddressLine(0);
                country = addressList.get(0).getCountryName();

                if (!locality.isEmpty() && !country.isEmpty() && locality != null && country != null) {
                    resutText.setText(locality.toString() + "" + country.toString());
                    mMap.clear();
                    mMap.addMarker(new MarkerOptions().position(latLng).title(locality + "  " + country));

                    isPicked = true;
                } else {

                    isPicked = false;
                }
            } else {

                isPicked = false;
            }

        } catch (IOException e) {

            e.printStackTrace();
            isPicked = false;
        }

        CameraUpdate yourLocation = CameraUpdateFactory.newLatLngZoom(latLng, 19);
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(latLng)
                .zoom(16)
                .bearing(70)
                .tilt(25)
                .build();
        mMap.animateCamera(yourLocation);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.maps, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_geolocate:
                if (isPicked) {
                    Intent intent = new Intent();
                    intent.putExtra("alamat", locality.toString() + "" + country.toString());
                    intent.putExtra("lat", lat);
                    intent.putExtra("lng", lng);
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                } else {
                    Snackbar.make(resutText, "Silahkan Pilih Lokasi ", Snackbar.LENGTH_LONG).show();
                }
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public void onBackPressed() {
        if (isPicked) {
            builder.setTitle("Info");
            builder.setMessage("Anda Belum Memilih Alamat");
            builder.setPositiveButton("Batal Memilih", (dialog, which) -> {
                dialog.dismiss();
                MapsActivity.super.onBackPressed();
                finish();
            });
            builder.setNegativeButton("Tetap dihalaman ini", (dialog, which) -> dialog.dismiss());
            builder.show();

        } else {
            Snackbar.make(resutText, "Silahkan Pilih Lokasi ", Snackbar.LENGTH_LONG).show();
        }
    }
}
