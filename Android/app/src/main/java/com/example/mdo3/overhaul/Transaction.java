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
    private Item[] items;
    private Location location;

    public Transaction(int Id, int UserId, String Title, String Description, Timestamp DatePosted,
                       Timestamp DateClosed, boolean IsCompleted, Item[] Items, Location location)
    {
        this.id = Id;
        this.userId = UserId;
        this.title = Title;
        this.description = Description;
        this.datePosted = DatePosted;
        this.dateClosed = DateClosed;
        this.isCompleted = IsCompleted;
        this.items = Items;
        this.location = location;
    }

    public int getId() { return this.id; }
    public int getUserId() { return this.userId; }
    public String getTitle() { return this.title; }
    public String getDescription() { return this.description; }
    public Timestamp getDatePosted() { return this.datePosted; }
    public Timestamp getDateClosed() { return this.dateClosed; }
    public boolean isCompleted() { return this.isCompleted; }
    public Item[] getItems() { return this.items; }
    public Location getLocation() { return this.location; }

}
