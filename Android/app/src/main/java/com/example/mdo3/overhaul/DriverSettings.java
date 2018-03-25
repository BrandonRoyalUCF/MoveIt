package com.example.mdo3.overhaul;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DriverSettings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_settings);

        //Save Button
        View.OnClickListener saveListen = new View.OnClickListener() {
            @Override
            // Need to eventually have it actually save data to the database
            public void onClick(View v) {
                finish();
                Intent myIntent = new Intent(DriverSettings.this, client_main_screen.class);
                DriverSettings.this.startActivity(myIntent);
            }
        };
        Button saveBtn = (Button) findViewById(R.id.button_save);
        saveBtn.setOnClickListener(saveListen);
    }

}
