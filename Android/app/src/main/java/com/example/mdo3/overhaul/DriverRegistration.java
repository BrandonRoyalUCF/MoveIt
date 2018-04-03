package com.example.mdo3.overhaul;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Rohini on 4/2/2018.
 */

public class DriverRegistration extends Activity {
    private EditText et_name, et_email, et_password, et_cpassword, et_phone, et_address, et_vehicleNumber, et_vehicleName;
    Button rgbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_driver);
        et_name = (EditText) findViewById(R.id.name);
        et_email = (EditText) findViewById(R.id.email);
        et_password = (EditText) findViewById(R.id.password);
        et_cpassword = (EditText) findViewById(R.id.confirm_password);
        et_phone = (EditText) findViewById(R.id.phone);
        et_address = (EditText) findViewById(R.id.address);
        et_vehicleName = (EditText) findViewById(R.id.vehicle_name);
        et_vehicleNumber = (EditText) findViewById(R.id.vehicle_number);
        rgbtn = (Button)findViewById(R.id.register_user);
        rgbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                register();
            }

        });
    }

    public void register() {
        //initialize();

    }
}

