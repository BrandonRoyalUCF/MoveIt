package com.example.mdo3.overhaul;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class JobRequest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_request_screen);

        //Upload Images Button
        View.OnClickListener uploadListen = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Just a placeholder so I can test that clicking on this button actually works.
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
                Toast.makeText(JobRequest.this, "Not ready for requests yet!", Toast.LENGTH_SHORT).show();
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
