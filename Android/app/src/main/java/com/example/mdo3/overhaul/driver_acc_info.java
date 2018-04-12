package com.example.mdo3.overhaul;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

public class driver_acc_info extends AppCompatActivity
{

    private EditText mAccNum;
    private EditText mAccNum2;
    private EditText mAccRout;
    private EditText mAccBank;

    private String AccNum;
    private String AccNum2;
    private String AccRout;
    private String AccBank;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_acc_info);

        mAccBank = (EditText) findViewById(R.id.acc_bank);
        mAccRout = (EditText) findViewById(R.id.acc_routing);
        mAccNum2 = (EditText) findViewById(R.id.acc_number2);
        mAccNum = (EditText) findViewById(R.id.acc_number);

        //Create back arrow on the tool bar to send back to homescreen
        Toolbar toolbar = (Toolbar) findViewById(R.id.acc_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    public void submitBtn(View view)
    {
        String user = "user";
        String license = "123-123-123-123";
        String carMake = "Tesla";
        String carModel = "Model X";
        int carYear = 2018;
        String licensePlate = "I<3U";
        float load = 1000;

        Date date = new Date();
        Timestamp ts = new Timestamp(date.getTime());

        AccNum = mAccNum.getText().toString();
        AccNum2 = mAccNum2.getText().toString();
        AccRout = mAccRout.getText().toString();
        AccBank = mAccBank.getText().toString();

        if(!AccNum.equalsIgnoreCase(AccNum2))
        {
            mAccNum2.setError("Account number doesn't match");
        }

        //(String UserName, String PassWord, String Name, String PhoneNumber, String DriverLicenseNumber, Timestamp DateRegistered,
        //String CarMake, String CarModel, int CarYear, String LicensePlateNumber, float LoadCapacity,
        //String BankAccountNumber, String RoutingNumber, String BillingName)
        DataAccess da = new DataAccess();
        Intent pastIntent = getIntent();
        Boolean result = da.insertDriver(pastIntent.getStringExtra("email"),
                pastIntent.getStringExtra("password"),
                pastIntent.getStringExtra("name"),
                pastIntent.getStringExtra("phone"),
                pastIntent.getStringExtra("licenseNumber"),
                ts,
                pastIntent.getStringExtra("VehicleCompany"),
                pastIntent.getStringExtra("vehicleModel"),
                Integer.parseInt(pastIntent.getStringExtra("vehicleYear")),
                pastIntent.getStringExtra("licenseNumber"),
                Integer.parseInt(pastIntent.getStringExtra("loadCapacity")),
                AccNum,
                AccRout,
                pastIntent.getStringExtra("name"));

        System.out.println("DEBUG : "  + AccNum);
        System.out.println("DEBUG : "  + AccNum2);
        System.out.println("DEBUG : "  + AccRout);
        System.out.println("DEBUG : "  + AccBank);


        if(result) {
            Toast.makeText(this, "Signup successful !", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, DriverMainScreen.class);
            startActivity(intent);
        }
        else
        {

        }
    }

    public void cancelBtn(View view)
    {
        Intent intent = new Intent(this, HomeScreen.class);
        startActivity(intent);
    }

    @Override
    public boolean onSupportNavigateUp()
    {
        onBackPressed();
        Intent intent = new Intent(this, HomeScreen.class);
        startActivity(intent);
        return true;
    }
}
