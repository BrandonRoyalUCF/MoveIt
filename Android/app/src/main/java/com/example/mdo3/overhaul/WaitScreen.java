package com.example.mdo3.overhaul;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.util.Queue;

// Still need to set driver searching functionality (tentative)
public class WaitScreen extends AppCompatActivity {

    private Driver assignedDriver = null;
    private Bundle bundle = getIntent().getExtras();
    private int serviceRequestID = bundle.getInt("serviceRequestID");
    //private int requestCustomerID = bundle.getInt("userID");
    //private

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_wait_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(!findDriver()) {
            //Unable to find driver
            Toast.makeText(WaitScreen.this, "Unable to find driver!", Toast.LENGTH_SHORT).show();
        }
        if(!assignDriver()) {
            //Failed to assign drive
            Toast.makeText(WaitScreen.this, "Unable to assign driver!", Toast.LENGTH_SHORT).show();
        }
        finish();
        Intent intent = new Intent(WaitScreen.this, ClientMainScreen.class);
        startActivity(intent);
    }

    // Find an available driver, assign to 'assignedDriver'
    private boolean findDriver() {
        DataAccess da = new DataAccess();
        Queue<Driver> driverQueue  = da.getPossibleDrivers();

        assignedDriver = driverQueue.poll();
        while(assignedDriver != null) {
            /*
            Send request to driver
            if(driverAccepts) {
                return true;
             */
            assignedDriver = driverQueue.poll();
        }
        return false;
    }

    //Add 'assignedDriver' to the service request
    private boolean assignDriver() {
        DataAccess da = new DataAccess();
        //Find serviceRequest with id = 'serviceRequestID', add driver to request
        return true;
    }

    // Cancels Driver Search
    public void CancelSearch(View view)
    {
        // Links back to client main screen
        Intent intent = new Intent(WaitScreen.this, ClientMainScreen.class);
        startActivity(intent);
    }

}
