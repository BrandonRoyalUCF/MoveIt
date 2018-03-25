package com.example.mdo3.overhaul;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ClientSettings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_settings);

        //Add Credit Card Button
        View.OnClickListener addCCListen = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Just a placeholder so I can test that clicking on this button actually works.
                Toast.makeText(ClientSettings.this, "Not ready for credit cards yet!", Toast.LENGTH_SHORT).show();
            }
        };
        Button addCCBtn = (Button) findViewById(R.id.button_credit_card);
        addCCBtn.setOnClickListener(addCCListen);

        //Save Button
        View.OnClickListener saveListen = new View.OnClickListener() {
            @Override
            // Need to eventually have it actually save data to the database
            public void onClick(View v) {
                finish();
                Intent myIntent = new Intent(ClientSettings.this, ClientMainScreen.class);
                ClientSettings.this.startActivity(myIntent);
            }
        };
        Button saveBtn = (Button) findViewById(R.id.button_save);
        saveBtn.setOnClickListener(saveListen);
    }
}
