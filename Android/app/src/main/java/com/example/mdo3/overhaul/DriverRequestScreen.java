package com.example.mdo3.overhaul;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DriverRequestScreen extends AppCompatActivity {

    //ServiceRequest sr = (ServiceRequest) getIntent().getSerializableExtra("serviceRequest");

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

        TextView jobDescriptionText = (TextView) this.findViewById(R.id.text_jobDescription);
        String jobDescription = createJobDescriptionText();
        jobDescriptionText.setText(jobDescription);

    }

    private String createJobDescriptionText() {
        /*
        int weight = sr.getWeight();
        String pickupLocation = sr.getStartLocation();
        String dropoffLocation = sr.getEndLocation();
        String description = sr.getDescription(); */

        int weight = 50;
        String pickupLocation = "123 Main Street";
        String dropoffLocation = "456 Other Avenue";
        String description = "Moving lol. Need to move some stuff. Pls help.";

        String jobDescriptionText = "Weight: " + weight + " lbs\nPickup Location: " + pickupLocation + "\nDropoffLocation: " + dropoffLocation + "\nDescription: " + description + "\n";
        return jobDescriptionText;
    }
}
