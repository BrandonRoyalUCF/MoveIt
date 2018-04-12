package com.example.mdo3.overhaul;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import java.util.Queue;

// Still need to set driver searching functionality (tentative)
public class WaitScreen extends AppCompatActivity {

    private Driver assignedDriver = null;
    private Bundle bundle = getIntent().getExtras();
    private int serviceRequestID = bundle.getInt("serviceRequestID");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_wait_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        findDriver();
        if(assignedDriver == null) {
            //Unable to find driver
        }
        assignDriver();
    }

    // Returns an available driver
    private void findDriver() {
        DataAccess da = new DataAccess();
        Queue<Driver> driverQueue  = da.getPossibleDrivers();

        assignedDriver = driverQueue.poll();
        while(assignedDriver != null) {
            //Send request to driver
            /*
            if(driverAccepts) {
                return;
             */
            assignedDriver = driverQueue.poll();
        }
        return;
    }

    private boolean assignDriver() {
        DataAccess da = new DataAccess();
        //Find serviceRequest with id = 'serviceRequestID', add driver
    }

    // Cancels Driver Search
    public void CancelSearch(View view)
    {
        // Links back to client main screen
        Intent intent = new Intent(WaitScreen.this, ClientMainScreen.class);
        startActivity(intent);
    }

}
