package com.example.mdo3.overhaul;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Timestamp;
import java.util.Date;

public class JobRequest extends AppCompatActivity {

    private int userId = 1;
    private EditText title;
    private EditText description;
    private Timestamp datePosted;
    private CheckBox mLoadHelp;
    private CheckBox mUnloadHelp;
    private boolean isCompleted;
    private EditText mPickupLocation;
    private EditText mDestination;
    private EditText mWeight;
    private EditText mPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_request_screen);
        title = (EditText) findViewById(R.id.editText3);
        description = (EditText) findViewById((R.id.editTextDescription));
        mLoadHelp = (CheckBox) findViewById(R.id.checkBoxLoad);
        mUnloadHelp = (CheckBox) findViewById(R.id.checkBoxUnload);
        mPickupLocation = (EditText) findViewById(R.id.editTextPickup);
        mDestination = (EditText) findViewById(R.id.editTextDestination);
        mWeight = (EditText) findViewById(R.id.editTextWeight);
        mPrice = (EditText) findViewById(R.id.editTextPrice);

        //Upload Images Button
        View.OnClickListener uploadListen = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Just a placeholder so I can test that clicking on this button actually works.
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 0);
                Toast.makeText(JobRequest.this, "Not ready for images yet!", Toast.LENGTH_SHORT).show();
            }
        };


        Button uploadBtn = (Button) findViewById(R.id.button_upload);
        uploadBtn.setOnClickListener(uploadListen);

        //Send Request Button
        View.OnClickListener sendListen = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Just a placeholder so I can test that clicking on this button actually works.
                String sTitle = title.getText().toString();
                String sDescription = description.getText().toString();

                // Get load and unload checkboxes

                boolean loadHelp = mLoadHelp.isChecked();
                boolean unloadHelp = mUnloadHelp.isChecked();
                Date date = new Date();
                datePosted = new Timestamp(date.getTime());

                // Get Locations
                String sPickupLocation = mPickupLocation.getText().toString();
                String sDestination = mDestination.getText().toString();

                // Get price and weight floats
                String sWeight = mWeight.getText().toString();
                float weight = Float.parseFloat(sWeight);
                String sPrice = mPrice.getText().toString();
                float price = Float.parseFloat(sPrice);

                isCompleted = false;


                Toast.makeText(JobRequest.this, "Not ready for requests yet!", Toast.LENGTH_SHORT).show();

                //insert a new service request into the database
                DataAccess da = new DataAccess();
                Integer serviceRequestId = da.insertServiceRequest(userId, sTitle, sDescription, weight, datePosted, price, loadHelp, unloadHelp, null, sPickupLocation, sDestination);
                if(serviceRequestId == null)
                    System.out.println("INSERT FAILED ******************");

                finish();
                Intent myIntent = new Intent(JobRequest.this, WaitScreen.class);
                myIntent.putExtra("serviceRequestID", serviceRequestId);
                JobRequest.this.startActivity(myIntent);
            }
        };
        Button sendBtn = (Button) findViewById(R.id.button_send);
        sendBtn.setOnClickListener(sendListen);

        //Cancel Button
        View.OnClickListener cancelListen = new View.OnClickListener() {
            @Override
            // Need to eventually have it actually save data to the database
            public void onClick(View v) {
                finish();
                Intent myIntent = new Intent(JobRequest.this, ClientMainScreen.class);
                JobRequest.this.startActivity(myIntent);
            }
        };
        Button cancelBtn = (Button) findViewById(R.id.button_cancel);
        cancelBtn.setOnClickListener(cancelListen);

        mWeight.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus)
                {
                    String sWeight = mWeight.getText().toString();
                    if (!sWeight.isEmpty()) {
                        float weight = Float.parseFloat(sWeight);
                        float price = weight * 0.10f;
                        String sPrice = String.valueOf(price);
                        mPrice.setText(sPrice);
                    }
                }
            }
        });

        mDestination.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus)
                {
                    if (!mPickupLocation.getText().toString().isEmpty() && !mDestination.getText().toString().isEmpty())
                    {
                        // This value will be the distance between the start and end
                        // once we get Google Maps API in
                        float distance = 4.0f;
                        float mpg = 20.0f;
                        float gasPrice = 4.0f;
                        float price = (distance / mpg) * gasPrice;
                        String sPrice = mPrice.getText().toString();
                        float oldPrice = Float.parseFloat(sPrice);
                        price += oldPrice;
                        sPrice = String.valueOf(price);
                        mPrice.setText(sPrice);
                    }
                }
            }
        });

        mLoadHelp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String sPrice = mPrice.getText().toString();
                float price = Float.parseFloat(sPrice);
                if (isChecked)
                    price += 5.0;
                else
                    price -= 5.0;
                sPrice = String.valueOf(price);
                mPrice.setText(sPrice);
            }
        });

        mUnloadHelp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String sPrice = mPrice.getText().toString();
                float price = Float.parseFloat(sPrice);
                if (isChecked)
                    price += 5.0;
                else
                    price -= 5.0;
                sPrice = String.valueOf(price);
                mPrice.setText(sPrice);
            }
        });
    }
}
