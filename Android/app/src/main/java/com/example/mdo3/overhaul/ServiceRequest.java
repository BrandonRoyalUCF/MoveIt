package com.example.mdo3.overhaul;

import java.sql.Time;
import java.sql.Timestamp;

/**
 * Created by Royal on 3/26/2018.
 */

public class ServiceRequest {

    private int id;
    private int idCustomer;
    private Integer idDriverWhoCompleted;
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
    private float distanceInMiles;

    public ServiceRequest(int Id, int IdCustomer, Integer IdDriverWhoCompleted, String Title, String Description, Timestamp DatePosted, Timestamp DateClosed,
                       boolean IsCompleted, boolean loadHelp, boolean unloadHelp,
                       String pickupLocation, String destination, float weight, float price, float distanceInMiles)
    {
        this.id = Id;
        this.idCustomer = IdCustomer;
        this.idDriverWhoCompleted = IdDriverWhoCompleted;
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
    public int getIdCustomer() { return this.idCustomer; }
    public int getIdDriverWhoCompleted() { return  this.idDriverWhoCompleted;}
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
    public float getDistanceInMiles() {return this.distanceInMiles;}

}
