package com.example.mdo3.overhaul;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.content.Intent;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.OnMapReadyCallback;
import android.graphics.drawable.Drawable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.InputStream;
import java.net.URL;

public class ClientMainScreen extends Activity implements OnMapReadyCallback{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_main_screen);

        MapFragment mapView = (MapFragment) getFragmentManager().findFragmentById(R.id.map_View);
        mapView.getMapAsync(this);

        //Request Button
        OnClickListener requestListen = new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent myIntent = new Intent(ClientMainScreen.this, JobRequest.class);
                ClientMainScreen.this.startActivity(myIntent);
            }
        };
        Button requestBtn = (Button) findViewById(R.id.button_request_driver);
        requestBtn.setOnClickListener(requestListen);

        //Settings Button
        OnClickListener settingsListen = new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent myIntent = new Intent(ClientMainScreen.this, ClientSettings.class);
                ClientMainScreen.this.startActivity(myIntent);
            }
        };
        Button settingsBtn = (Button) findViewById(R.id.button_settings);
        settingsBtn.setOnClickListener(settingsListen);

        //Logout Button
        OnClickListener logoutListen = new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent myIntent = new Intent(ClientMainScreen.this, HomeScreen.class);
                ClientMainScreen.this.startActivity(myIntent);
            }
        };
        Button logoutBtn = (Button) findViewById(R.id.button_logout);
        logoutBtn.setOnClickListener(logoutListen);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        /*googleMap.getUiSettings().setScrollGesturesEnabled(false);
        googleMap.getUiSettings().setZoomGesturesEnabled(false);
        googleMap.setMapType(1);
        googleMap.setMaxZoomPreference(12);
        googleMap.setMinZoomPreference(12); */

        // TODO: Have the LatLng data set here for driver be set to the driver's location, and refreshed every 3 to 5 minutes.
        LatLng driver = new LatLng(-33.852, 151.211);
        googleMap.addMarker(new MarkerOptions().position(driver).title("Your Driver"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(driver));
    }

    // TODO: Refresh map function
    // https://stackoverflow.com/questions/17113526/android-auto-refresh-after-a-certain-time

}
