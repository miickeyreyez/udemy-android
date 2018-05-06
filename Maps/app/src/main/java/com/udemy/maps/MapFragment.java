package com.udemy.maps;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnMarkerDragListener, View.OnClickListener{

    private View view;
    private GoogleMap googleMap;
    private MapView mapView;
    private List<Address> address;
    private Geocoder geocoder;
    private MarkerOptions markerOptions;
    private FloatingActionButton fab;

    public MapFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_map, container, false);
        fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(this);
        return view;
    }

    @Override
    public  void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mapView = (MapView)view.findViewById(R.id.map);
        if(mapView != null) {
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }

        //this.checkGps();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        //Delimitar el zoom en el mapa
        googleMap.setMinZoomPreference(15);
        googleMap.setMaxZoomPreference(18);

        // Add a marker in Sydney and move the camera
        //Zoom limit 21
        //Bearing: Orientación hacia el este
        //Tilt es para 3D, límite 90
        LatLng azteca = new LatLng(19.302861  , -99.150528);
        LatLng campNou = new LatLng(41.380896 , 2.12282);
        //googleMap.addMarker(new MarkerOptions().position(campNou).title("Camp Nou").draggable(true));
        //googleMap.addMarker(new MarkerOptions().position(azteca).title("Azteca"));

        CameraPosition camera = new CameraPosition.Builder().target(campNou)
                .zoom(18)
                .bearing(45)
                .tilt(90)
                .build();

        markerOptions = new MarkerOptions();
        markerOptions.position(campNou);
        markerOptions.title("Camp Nou");
        markerOptions.draggable(true);
        markerOptions.snippet("Estadio FC Barcelona");
        markerOptions.icon(BitmapDescriptorFactory.fromResource(android.R.drawable.star_on));

        googleMap.addMarker(markerOptions);

        CameraUpdate zoom = CameraUpdateFactory.zoomTo(15);

        googleMap.moveCamera(CameraUpdateFactory.newLatLng(campNou));
        //googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(camera));
        googleMap.animateCamera(zoom);

        googleMap.setOnMarkerDragListener(this);

        geocoder = new Geocoder(getContext(), Locale.getDefault());

    }

    private void checkGps() {
        try {
            int gpsSignal = Settings.Secure.getInt(getActivity().getContentResolver(),Settings.Secure.LOCATION_MODE);
            if (gpsSignal == 0) {
                //GPS desactivado
                /*Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);*/
                showAlertInfo();
            }
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void showAlertInfo() {
        new AlertDialog.Builder(getContext())
                .setTitle("GPS")
                .setMessage("GPS desactivado, ¿Desea activarlo?")
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }

    @Override
    public void onResume() {
        super.onResume();
        //checkGps();
    }

    @Override
    public void onMarkerDragStart(Marker marker) {
        marker.hideInfoWindow();
    }

    @Override
    public void onMarkerDrag(Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        double latitude = marker.getPosition().latitude;
        double longitude = marker.getPosition().longitude;

        try {
            address = geocoder.getFromLocation(latitude, longitude, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //AddressLine -> Solamente la primera linea
        Address add = address.get(0);
        String addressString = add.getAddressLine(0);
        String city = add.getLocality();
        String state = add.getAdminArea();
        String country = add.getCountryName();
        String zipCode = add.getPostalCode();
        String direccion = city + " " + state + " " + country + " " + zipCode;
        marker.setTitle(addressString);
        marker.setSnippet(direccion);
        marker.showInfoWindow();
        //Toast.makeText(getContext(), direccion ,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        this.checkGps();
    }
}
