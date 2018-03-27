package com.example.mdo3.overhaul;

import android.media.Image;

/**
 * Created by Royal on 3/26/2018.
 */

public class Item {

    private int transactionId;
    private String name;
    private String description;
    private float weight;
    private float height;
    private float length;
    private float width;
    private byte[] picture;

    public Item(int IdTransaction, String Name, String Description,
                float Weight, float Height, float Length, float Width, byte[] Picture)
    {
        this.transactionId = IdTransaction;
        this.name = Name;
        this.description = Description;
        this.weight = Weight;
        this.height = Height;
        this.length = Length;
        this.width = Width;
        this.picture = Picture;
    }
}
