package com.udemy.maps;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //Delimitar el zoom en el mapa
        mMap.setMinZoomPreference(15);
        mMap.setMaxZoomPreference(18);

        // Add a marker in Sydney and move the camera
        //Zoom limit 21
        //Bearing: Orientación hacia el este
        //Tilt es para 3D, límite 90
        LatLng azteca = new LatLng(19.302861  , -99.150528);
        LatLng campNou = new LatLng(41.380896 , 2.12282);
        mMap.addMarker(new MarkerOptions().position(campNou).title("Camp Nou").draggable(true));
        mMap.addMarker(new MarkerOptions().position(azteca).title("Azteca"));

        CameraPosition camera = new CameraPosition.Builder().target(campNou)
                .zoom(18)
                .bearing(45)
                .tilt(90)
                .build();

        //mMap.moveCamera(CameraUpdateFactory.newLatLng(campNou));
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(camera));

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                Toast.makeText(getApplicationContext(),"Lat: " + latLng.latitude + " Lon: " + latLng.longitude,Toast.LENGTH_SHORT).show();
            }
        });

        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                Toast.makeText(getApplicationContext(),"Long - Lat: " + latLng.latitude + " Long: " + latLng.longitude,Toast.LENGTH_SHORT).show();
            }
        });

        mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener(){

            @Override
            public void onMarkerDragStart(Marker marker) {

            }

            @Override
            public void onMarkerDrag(Marker marker) {

            }

            @Override
            public void onMarkerDragEnd(Marker marker) {
                Toast.makeText(getApplicationContext(),"Marker - Lat: " + marker.getPosition().latitude + " Lon: " + marker.getPosition().longitude,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
