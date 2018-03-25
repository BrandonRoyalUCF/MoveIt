package com.example.mdo3.overhaul;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.content.Intent;

public class client_main_screen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_main_screen);

        //Request Button
        OnClickListener requestListen = new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent myIntent = new Intent(client_main_screen.this, JobRequest.class);
                client_main_screen.this.startActivity(myIntent);
            }
        };
        Button requestBtn = (Button) findViewById(R.id.button_request_driver);
        requestBtn.setOnClickListener(requestListen);

        //Settings Button
        OnClickListener settingsListen = new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent myIntent = new Intent(client_main_screen.this, ClientSettings.class);
                client_main_screen.this.startActivity(myIntent);
            }
        };
        Button settingstBtn = (Button) findViewById(R.id.button_settings);
        settingstBtn.setOnClickListener(settingsListen);

        //Logout Button
        OnClickListener logoutListen = new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent myIntent = new Intent(client_main_screen.this, HomeScreen.class);
                client_main_screen.this.startActivity(myIntent);
            }
        };
        Button logoutBtn = (Button) findViewById(R.id.button_logout);
        logoutBtn.setOnClickListener(logoutListen);
    }
}
