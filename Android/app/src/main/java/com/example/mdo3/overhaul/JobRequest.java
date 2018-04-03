package com.example.mdo3.overhaul;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.sql.Timestamp;
import java.util.Date;

public class JobRequest extends AppCompatActivity {

    private int id;
    private int userId;
    private EditText title;
    private EditText description;
    private Timestamp datePosted;
    private boolean mLoadHelp;
    private boolean mUnloadHelp;
    private boolean isCompleted;
    private EditText mPickupLocation;
    private EditText mDestination;
    private EditText mWeight;
    private EditText mPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_request_screen);

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
                title = (EditText) findViewById(R.id.editText3);
                description = (EditText) findViewById((R.id.editTextDescription));
                String sTitle = title.getText().toString();
                String sDescription = description.getText().toString();

                // Get load and unload checkboxes
                CheckBox loadHelp = (CheckBox) findViewById(R.id.checkBoxLoad);
                mLoadHelp = loadHelp.isChecked();
                CheckBox unloadHelp = (CheckBox) findViewById(R.id.checkBoxUnload);
                mUnloadHelp = unloadHelp.isChecked();
                Date date = new Date();
                datePosted = new Timestamp(date.getTime());

                // Get Locations
                mPickupLocation = (EditText) findViewById(R.id.editTextPickup);
                mDestination = (EditText) findViewById(R.id.editTextDestination);
                String sPickupLocation = mPickupLocation.getText().toString();
                String sDestination = mDestination.getText().toString();

                // Get price and weight floats
                mWeight = (EditText) findViewById(R.id.editTextWeight);
                mPrice = (EditText) findViewById(R.id.editTextPrice);
                String sWeight = mWeight.getText().toString();
                float weight = Float.parseFloat(sWeight);
                String sPrice = mPrice.getText().toString();
                float price = Float.parseFloat(sPrice);

                isCompleted = false;


                Toast.makeText(JobRequest.this, "Not ready for requests yet!", Toast.LENGTH_SHORT).show();
                Transaction trans = new Transaction(userId, sTitle, sDescription, datePosted,
                                                    isCompleted, mLoadHelp, mUnloadHelp, sPickupLocation,
                                                    sDestination, weight, price);
                DataAcess DA = new DataAcess();
                DA.insertTransactionAndItems(trans);
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
    }
}
