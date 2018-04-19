package com.example.mdo3.overhaul;

import android.app.Activity;
import android.location.*;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.OnMapReadyCallback;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;

public class ClientMainScreen extends Activity implements OnMapReadyCallback{
    // Set this variable based on whether or not a request is active, and thus whether or not to show the driver map.
    // Ideally this should be set whenever the user moves to this screen, thus the setting for this variable
    // should be within OnClickListeners that send the user here.
    public boolean requestActive;
    // Note that primitive booleans (lowercase B) will be false if left uninitialized.

    private final Handler mapHandler = new Handler(); // For handling the automatic map refresh.
    private GoogleMap driverMap;
    public LatLng currentDriver;
    private boolean trackLoop = false; // For testing/faking purposes.
    private boolean insertFailed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent myIntent = getIntent();
        Customer myCustomer = (Customer)myIntent.getSerializableExtra("Customer");
        insertFailed = myIntent.getBooleanExtra("InsertError", false);

        setContentView(R.layout.activity_client_main_screen);
        TextView errorText = (TextView) findViewById(R.id.errorText);

        if (insertFailed)
        {
            errorText.setVisibility(View.VISIBLE);
        }
        else
        {
            errorText.setVisibility(View.INVISIBLE);
        }

        // Checks if the currently logged in driver is part of an active request.
        DataAccess checkRequest = new DataAccess();
        if(checkRequest != null && myCustomer != null)
            requestActive = checkRequest.checkForActiveSRById(myCustomer.getId());

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
                myIntent.putExtra("Customer", myCustomer);
                ClientMainScreen.this.startActivity(myIntent);
            }
        };
        Button requestBtn = (Button) findViewById(R.id.button_request_driver);
        requestBtn.setOnClickListener(requestListen);

        //Edit Account Button
        OnClickListener accountListen = new OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //Edit Customer Credit Card Information
                //Send flag to credit_card_info to know the user is editing information
                //passing customer previous information
                Intent intent = new Intent(ClientMainScreen.this, UpdateCustomerInformation.class);
                intent.putExtra("Customer", myCustomer);
                startActivity(intent);
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

        if (requestActive){ mapAutoRefresh(); } // If request is not active, then don't bother with the refresh loops.
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        if (requestActive) {
            googleMap.setMapType(1);
            googleMap.setMaxZoomPreference(12);
            googleMap.setMinZoomPreference(12);

            // TODO: Have the LatLng data set here for driver be set to the driver's location, and refreshed every 3 to 5 minutes.
            // For now it is set to the user's current location.
            LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
            try {
                android.location.Location location = lm.getLastKnownLocation(Context.LOCATION_SERVICE);
                currentDriver = new LatLng(location.getLatitude(), location.getLongitude());
            } catch (SecurityException ex) {
                // This is in case the user has not given the app Location permissions. Set to UCF by default.
                currentDriver = new LatLng(28.600706, -81.197837);
            } catch (Exception ex) {
                // In case some other exception is thrown.
                currentDriver = new LatLng(28.600706, -81.197837);
            }

            googleMap.addMarker(new MarkerOptions().position(currentDriver).title("Your Driver"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(currentDriver));

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
                // TODO: Have the LatLng data set here for driver be set to the driver's location.
                // For now this will just change it by a couple of degrees every refresh.

                if (!trackLoop) {
                    currentDriver = new LatLng(currentDriver.latitude+0.3,currentDriver.longitude);
                    trackLoop = true;
                } else {
                    currentDriver = new LatLng(currentDriver.latitude-0.3,currentDriver.longitude);
                    trackLoop = false;
                }

                driverMap.clear(); // Get rid of the old marker, otherwise they'll just stack up.
                driverMap.addMarker(new MarkerOptions().position(currentDriver).title("Your Driver"));
                driverMap.moveCamera(CameraUpdateFactory.newLatLng(currentDriver));

                mapAutoRefresh(); // Refresh again in a while.
            }
        }, 180000); // Refresh every 3 minutes. 180000 milliseconds = 3 minutes.
    }

    private void editAccount()
    {

    }
}
