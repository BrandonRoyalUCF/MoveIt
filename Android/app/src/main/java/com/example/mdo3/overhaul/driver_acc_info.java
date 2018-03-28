package com.example.mdo3.overhaul;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

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
        AccNum = mAccNum.getText().toString();
        AccNum2 = mAccNum2.getText().toString();
        AccRout = mAccRout.getText().toString();
        AccBank = mAccBank.getText().toString();

        System.out.println("DEBUG : "  + AccNum);
        System.out.println("DEBUG : "  + AccNum2);
        System.out.println("DEBUG : "  + AccRout);
        System.out.println("DEBUG : "  + AccBank);
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
