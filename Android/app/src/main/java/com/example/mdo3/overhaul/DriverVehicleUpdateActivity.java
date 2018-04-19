package com.example.mdo3.overhaul;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class DriverVehicleUpdateActivity extends AppCompatActivity {


    private TextView username;
    private TextView name;
    private TextView phoneNumber;
    private TextView dateReg;

    private EditText vComp;
    private EditText vModel;
    private EditText vYear;
    private EditText vTag;
    private EditText vLoad;

    private Driver driver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.driver_vehicle_update);

        Intent intent = getIntent();
        driver = (Driver) intent.getSerializableExtra("Driver");

    }

    public void submitBtn (View view)
    {
        //public Customer updateCustomerPaymentInfo(int idCustomer, String CardNumber, String ExpMonth, String ExpYear, String CVV)
        String comp = null;
        String model = null;
        String year = null;
        String tag = null;
        String load = null;

        System.out.println("DEBUG: Submit Button Pressed");

        comp = vComp.getText().toString();
        model = vModel.getText().toString();
        year = vYear.getText().toString();
        tag = vTag.getText().toString();
        load = vLoad.getText().toString();


        String x = (comp != null && !comp.isEmpty()) ? comp : driver.getVehicle().getMake();
        String y = (model != null && !model.isEmpty()) ? model : driver.getVehicle().getModel();
        String z = (year != null && !year.isEmpty()) ? year : Integer.toString(driver.getVehicle().getYear());
        String xx = (tag != null && !tag.isEmpty()) ? tag : driver.getVehicle().getLicensePlate();
        String yy = (load != null && !load.isEmpty()) ? load : Float.toString(driver.getVehicle().getLoadCapacity());

        DataAccess da = new DataAccess();

        Boolean res = da.updateDriverVehicleInfo(driver.getId(),
                x,
                y,
                z,
                xx,
                yy);
        System.out.println(res);
        Driver result = da.getDriverById(driver.getId());


        System.out.println("DEBUG: Done");
        System.out.println("DEBUG: Starting new activity");
        Intent intent = new Intent(DriverVehicleUpdateActivity.this, DriverMainScreen.class);
        if(result != null)
            intent.putExtra("Driver", result);
        else
            intent.putExtra("Driver", driver);
        startActivity(intent);
    }

    public void cancelBtn (View view)
    {
        Intent intent = new Intent (this, DriverMainScreen.class);
        intent.putExtra("Driver",driver);
        startActivity(intent);
    }
}
