package com.example.mdo3.overhaul;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.net.Uri;

/**
 * Created by Wendelyn on 3/25/2018.
 */

public class DriverMainScreen extends Activity{
    // Set this variable based on whether or not a request is active, and thus whether or not to show the driver map.
    // Ideally this should be set whenever the user moves to this screen, thus the setting for this variable
    // should be within OnClickListeners that send the user here.
    public boolean requestActive;
    // Note that primitive booleans (lowercase B) will be false if left uninitialized.

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_main_screen);

        // TODO: Extra buttons for Pick-Up and Drop-Off
        // https://developers.google.com/maps/documentation/android-api/intents
        // https://stackoverflow.com/questions/45293200/android-studio-redirecting-to-the-phones-google-maps-app-with-a-start-and-end

        //Placeholder Button
        View.OnClickListener requestListen = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Just a placeholder so I can test that clicking on this button actually works.
                Toast.makeText(DriverMainScreen.this, "Just a placeholder!", Toast.LENGTH_SHORT).show();
            }
        };
        Button requestBtn = (Button) findViewById(R.id.button_placeholder);
        requestBtn.setOnClickListener(requestListen);

        //Settings Button
        View.OnClickListener settingsListen = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent myIntent = new Intent(DriverMainScreen.this, DriverSettings.class);
                DriverMainScreen.this.startActivity(myIntent);
            }
        };
        Button settingsBtn = (Button) findViewById(R.id.button_settings);
        settingsBtn.setOnClickListener(settingsListen);

        //Logout Button
        View.OnClickListener logoutListen = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent myIntent = new Intent(DriverMainScreen.this, HomeScreen.class);
                DriverMainScreen.this.startActivity(myIntent);
            }
        };
        Button logoutBtn = (Button) findViewById(R.id.button_logout);
        logoutBtn.setOnClickListener(logoutListen);

        requestActive = true; // Remove later, only for debugging right now.

        Button pickUpBtn = (Button) findViewById(R.id.button_pickup);
        Button dropOffBtn = (Button) findViewById(R.id.button_dropoff);
        if (requestActive){
            // Make sure the buttons are visible, and that they send the user to Google Maps
            // for the pick-up or drop-off.
            pickUpBtn.setVisibility(View.VISIBLE);
            View.OnClickListener pickUpListen = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Go to Google Maps with directions to pick-up address.
                    // TODO: Replace the string in the Uri.encode with whatever string was put in for the request's pick-up location.
                    Uri gmmIntentUri = Uri.parse("google.navigation:q=" + Uri.encode("University of Central Florida"));
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");
                    startActivity(mapIntent);
                }
            };
            pickUpBtn.setOnClickListener(pickUpListen);

            dropOffBtn.setVisibility(View.VISIBLE);
            View.OnClickListener dropOffListen = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Go to Google Maps with drop-off address.
                    // TODO: Replace the string in the Uri.encode with whatever string was put in for the request's drop-off location.
                    Uri gmmIntentUri = Uri.parse("google.navigation:q=" + Uri.encode("Epcot"));
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");
                    startActivity(mapIntent);
                }
            };
            dropOffBtn.setOnClickListener(dropOffListen);
        } else {
            // Hide the buttons and don't give them an OnClick action.
            pickUpBtn.setVisibility(View.INVISIBLE);
            dropOffBtn.setVisibility(View.INVISIBLE);
        }
    }
}
