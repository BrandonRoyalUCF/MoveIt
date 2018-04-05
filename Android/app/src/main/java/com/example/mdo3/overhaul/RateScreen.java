package com.example.mdo3.overhaul;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;

public class RateScreen extends AppCompatActivity {

    private RatingBar ratingObject;
    private float mRating;
    private EditText commentsObject;
    private String mComments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ratingObject = (RatingBar) findViewById(R.id.ratingBar);
        commentsObject = (EditText) findViewById(R.id.comments);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    // Submit Button  - database stuff left
    public void submitButton(View view)
    {
        mRating = ratingObject.getRating();
        mComments = commentsObject.getText().toString();

        // Links back to client main screen
        Intent intent = new Intent(RateScreen.this, ClientMainScreen.class);
        startActivity(intent);

    }
    // Cancel Button - database stuff left
    public void cancelButton(View view)
    {
        // Links back to client main screen
        Intent intent = new Intent(RateScreen.this, ClientMainScreen.class);
        startActivity(intent);
    }

}
