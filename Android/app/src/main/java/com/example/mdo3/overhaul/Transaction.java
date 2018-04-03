package com.example.mdo3.overhaul;

import java.sql.Time;
import java.sql.Timestamp;

/**
 * Created by Royal on 3/26/2018.
 */

public class Transaction {

    private int id;
    private int userId;
    private String title;
    private String description;
    private Timestamp datePosted;
    private Timestamp dateClosed;
    private boolean isCompleted;
    private boolean loadHelp;
    private boolean unloadHelp;
    private String pickupLocation;
    private String destination;
    private float weight;
    private float price;

    public Transaction(int UserId, String Title, String Description, Timestamp DatePosted,
                       boolean IsCompleted, boolean loadHelp, boolean unloadHelp,
                       String pickupLocation, String destination, float weight, float price)
    {
        this.userId = UserId;
        this.title = Title;
        this.description = Description;
        this.datePosted = DatePosted;
        this.isCompleted = IsCompleted;
        this.pickupLocation = pickupLocation;
        this.destination = destination;
        this.loadHelp = loadHelp;
        this.unloadHelp = unloadHelp;
        this.weight = weight;
        this.price = price;
    }

    public int getId() { return this.id; }
    public int getUserId() { return this.userId; }
    public String getTitle() { return this.title; }
    public String getDescription() { return this.description; }
    public Timestamp getDatePosted() { return this.datePosted; }
    public Timestamp getDateClosed() { return this.dateClosed; }
    public boolean isCompleted() { return this.isCompleted; }
    public String getPickupLocation() { return this.pickupLocation; }
    public String getDestination() { return this.destination; }
    public boolean needLoadHelp() { return this.loadHelp; }
    public boolean needUnloadHelp() { return this.unloadHelp; }
    public float getWeight() { return this.weight; }
    public float getPrice() { return this.price; }

}
