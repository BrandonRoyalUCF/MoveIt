package com.example.mdo3.overhaul;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Wendelyn on 3/25/2018.
 */

public class DriverMainScreen extends Activity{
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
    }
}
