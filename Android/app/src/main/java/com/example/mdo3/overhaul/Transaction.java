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

    public Transaction(int Id, int UserId, String Title, String Description, Timestamp DatePosted,
                       Timestamp DateClosed, boolean IsCompleted)
    {
        this.id = Id;
        this.userId = UserId;
        this.title = Title;
        this.description = Description;
        this.datePosted = DatePosted;
        this.dateClosed = DateClosed;
        this.isCompleted = IsCompleted;
    }

    public int getId() { return this.id; }
    public int getUserId() { return this.userId; }
    public String getTitle() { return this.title; }
    public String getDescription() { return this.description; }
    public Timestamp getDatePosted() { return this.datePosted; }
    public Timestamp getDateClosed() { return this.dateClosed; }
    public boolean isCompleted() { return this.isCompleted; }

}
