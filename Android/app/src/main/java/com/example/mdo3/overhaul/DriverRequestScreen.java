package com.example.mdo3.overhaul;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DriverRequestScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_request_screen);

        //Decline Button
        View.OnClickListener declineListen = new View.OnClickListener() {
            @Override
            // Need to eventually have it actually save data to the database
            public void onClick(View v) {
                finish();
                Intent myIntent = new Intent(DriverRequestScreen.this, DriverMainScreen.class);
                DriverRequestScreen.this.startActivity(myIntent);
            }
        };
        Button declineBtn = (Button) findViewById(R.id.button_decline);
        declineBtn.setOnClickListener(declineListen);

        //Accept Button
        View.OnClickListener acceptListen = new View.OnClickListener() {
            @Override
            // Need to eventually have it actually save data to the database
            public void onClick(View v) {
                finish();
                Intent myIntent = new Intent(DriverRequestScreen.this, DriverMainScreen.class);
                DriverRequestScreen.this.startActivity(myIntent);
            }
        };
        Button acceptBtn = (Button) findViewById(R.id.button_accept);
        acceptBtn.setOnClickListener(acceptListen);
    }
}
