package com.example.mdo3.overhaul;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.content.Intent;

public class ClientMainScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_main_screen);

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
}
