package com.example.mdo3.overhaul;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;


public class DriverDetails extends AppCompatActivity {

    private TextView tv_name;
    private TextView tv_rating;
    private ImageView iv_picture = null;
    private byte[] picture = new byte[0];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_driver_details);

        tv_name = (TextView) findViewById(R.id.viewDriverName);
        tv_rating = (TextView) findViewById(R.id.viewDriverRating);
        iv_picture = (ImageView) findViewById(R.id.viewDriverPicture);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initDetails();



    }

    public void initDetails(){
        tv_name.setText("Your Driver");
        tv_rating.setText(Double.toString(4.321));
        Bitmap bmp = BitmapFactory.decodeByteArray(picture,0,picture.length);
        if (bmp != null)
            iv_picture.setImageBitmap(bmp);
    }

}
