package com.example.mdo3.overhaul;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

// Still need to set driver searching functionality (tentative)
public class WaitScreen extends AppCompatActivity {

    Driver tmpDriver = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_wait_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent myIntent = getIntent();
        Customer myCustomer = (Customer)myIntent.getSerializableExtra("Customer");
        ServiceRequest idServiceRequest = (ServiceRequest)myIntent.getSerializableExtra("ServiceRequest");

        OnClickListener cancelListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                // Links back to client main screen
                Intent intent = new Intent(WaitScreen.this, ClientMainScreen.class);
                startActivity(intent);
            }
        };

        Button cancelSearchBtn = (Button) findViewById(R.id.cancelButton);
        cancelSearchBtn.setOnClickListener(cancelListener);

        //begin wait for driver to accept
        // For now, create a dummy variable for the driver info.
        DataAccess da = new DataAccess();
        tmpDriver = da.getDriverById(0);
        //da.waitForAcceptance()

        OnClickListener viewDriverListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                // Links to Driver Details screen
                Intent intent = new Intent(WaitScreen.this, DriverDetails.class);
                intent.putExtra("tempDriver", tmpDriver);
                startActivity(intent);
            }
        };

        Button viewDriverBtn = (Button) findViewById(R.id.viewDriverButton);
        viewDriverBtn.setOnClickListener(viewDriverListener);
    }
}
