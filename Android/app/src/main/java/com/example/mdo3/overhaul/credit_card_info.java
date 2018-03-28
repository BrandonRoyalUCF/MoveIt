package com.example.mdo3.overhaul;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Date;


public class credit_card_info extends AppCompatActivity
{

    private String[] items;
    private EditText view;
    private String ccNumber;
    private String ccDate;
    private String ccSCode;
    private String ccType;

    private EditText mccNumber;
    private EditText mccDate;
    private EditText mCCSCode;

    private Spinner dropdown;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_card_info);

        items = createSpinner();
        mccNumber  = (EditText) findViewById(R.id.cc_number);
        mccDate = (EditText) findViewById(R.id.cc_date);
        mCCSCode = (EditText) findViewById(R.id.cc_SCode);

        //Load credit card types into the spinner on activity_credit_card_info
        //get strings from strings.xml
        dropdown = (Spinner) findViewById(R.id.cc_types);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.cc_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private String[] createSpinner()
    {
        String[] types = getResources().getString(R.string.cc_types).split(",");
        return types;
    }

    public void submitBtn(View view)
    {
        ccNumber =  mccNumber.getText().toString();
        ccSCode =   mCCSCode.getText().toString();
        ccDate =    mccDate.getText().toString();
        ccType =    dropdown.getSelectedItem().toString();

        System.out.println("DEBUG: " + ccNumber);
        System.out.println("DEBUG: " + ccDate);
        System.out.println("DEBUG: " + ccSCode);
        System.out.println("DEBUG: " + ccType);
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
