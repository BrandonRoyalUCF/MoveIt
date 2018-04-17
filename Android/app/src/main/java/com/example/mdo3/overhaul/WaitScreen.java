package com.example.mdo3.overhaul;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.util.Queue;
import android.view.View.OnClickListener;

// Still need to set driver searching functionality (tentative)
public class WaitScreen extends AppCompatActivity {

<<<<<<< HEAD
    Driver tmpDriver = null;

=======
    private Driver assignedDriver = null;
    private ServiceRequest sr = (ServiceRequest) getIntent().getSerializableExtra("ServiceRequest");
>>>>>>> myersWilliam

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_wait_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Create a queue of available drivers, take driver at the head of the queue
        Queue<Driver> driverQueue = findDrivers();
        assignedDriver = driverQueue.poll();

        //If there are no available drivers, cancel the request and return
        if(assignedDriver == null)
        {
            Toast.makeText(WaitScreen.this, "No available drivers!", Toast.LENGTH_SHORT).show();
            CancelSearch();
        }

        //If the driver cannot be assigned, cancel the request and return
        if(!assignDriver()) {
            System.out.println("FAILED TO ASSIGN DRIVER!");
            CancelSearch();
        }

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

<<<<<<< HEAD
        //begin wait for driver to accept
        // For now, create a dummy variable for the driver info.
        DataAccess da = new DataAccess();
        tmpDriver = da.getDriverById(0);
        //da.waitForAcceptance()

=======
>>>>>>> myersWilliam
        OnClickListener viewDriverListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                // Links to Driver Details screen
                Intent intent = new Intent(WaitScreen.this, DriverDetails.class);
                intent.putExtra("driver", tmpDriver);
                startActivity(intent);
            }
        };
        Button viewDriverBtn = (Button) findViewById(R.id.viewDriverButton);
        viewDriverBtn.setOnClickListener(viewDriverListener);
<<<<<<< HEAD
=======

        //Query Button
        View.OnClickListener queryListen = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        };
        Button queryBtn = (Button) findViewById(R.id.button_queryDB);
        queryBtn.setOnClickListener(queryListen);


        finish();
        Intent intent = new Intent(WaitScreen.this, ClientMainScreen.class);
        startActivity(intent);
    }

    // Find an available driver, assign to 'assignedDriver'
    private Queue<Driver> findDrivers() {
        DataAccess da = new DataAccess();
        Queue<Driver> driverQueue  = da.getPossibleDrivers();

        return driverQueue;
    }

    //Add 'assignedDriver' to the service request
    private boolean assignDriver() {
        DataAccess da = new DataAccess();
        return true;
    }
    // Cancels Driver Search
    public void CancelSearch()
    {
        finish();
        Intent intent = new Intent(WaitScreen.this, ClientMainScreen.class);
        startActivity(intent);
        //TODO: indicate in database that the service request was not completed
>>>>>>> myersWilliam
    }
}
