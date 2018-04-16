package com.example.mdo3.overhaul;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.content.Intent;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.OnMapReadyCallback;
import android.os.Handler;
import android.widget.Toast;

public class ClientMainScreen extends Activity implements OnMapReadyCallback{
    // Set this variable based on whether or not a request is active, and thus whether or not to show the driver map.
    // Ideally this should be set whenever the user moves to this screen, thus the setting for this variable
    // should be within OnClickListeners that send the user here.
    public boolean requestActive;
    // Note that primitive booleans (lowercase B) will be false if left uninitialized.

    private final Handler mapHandler = new Handler(); // For handling the automatic map refresh.
    private GoogleMap driverMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent myIntent = getIntent();
        Customer myCustomer = (Customer)myIntent.getSerializableExtra("Customer");
        setContentView(R.layout.activity_client_main_screen);

        requestActive = true; // Remove this later, just for debugging at the moment.

        MapFragment mapView = (MapFragment) getFragmentManager().findFragmentById(R.id.map_View);
         if (requestActive){
             mapView.getView().setVisibility(View.VISIBLE);
             mapView.getMapAsync(this);
         } else {
             mapView.getView().setVisibility(View.INVISIBLE);
         }

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

        //Edit Account Button
        OnClickListener accountListen = new OnClickListener() {
            @Override
            public void onClick(View v) {
                // Just a placeholder for now
                Toast.makeText(ClientMainScreen.this, "Edit account info later!", Toast.LENGTH_SHORT).show();
            }
        };
        Button accountBtn = (Button) findViewById(R.id.button_edit_account);
        accountBtn.setOnClickListener(accountListen);

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

        mapAutoRefresh();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        if (requestActive) {
            googleMap.setMapType(1);
            googleMap.setMaxZoomPreference(12);
            googleMap.setMinZoomPreference(12);

            // TODO: Have the LatLng data set here for driver be set to the driver's location, and refreshed every 3 to 5 minutes.
            LatLng driver = new LatLng(-33.852, 151.211);
            googleMap.addMarker(new MarkerOptions().position(driver).title("Your Driver"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(driver));

            driverMap = googleMap;
        } else {
            // Do nothing. The map should be hidden while there are no active requests anyways.
        }

    }

    // The follow function refreshes the map to keep the customer up to date on the driver's location.
    private void mapAutoRefresh() {
        mapHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO: Have the LatLng data set here for driver be set to the driver's location, and refreshed every 3 to 5 minutes.
                LatLng driver = new LatLng(28.5383, -81.3792);
                driverMap.clear(); // Get rid of the old marker, otherwise they'll just stack up.
                driverMap.addMarker(new MarkerOptions().position(driver).title("Your Driver"));
                driverMap.moveCamera(CameraUpdateFactory.newLatLng(driver));

                mapAutoRefresh(); // Refresh again in a while.
            }
        }, 180000); // Refresh every 3 minutes. 180000 milliseconds = 3 minutes.
    }
}
