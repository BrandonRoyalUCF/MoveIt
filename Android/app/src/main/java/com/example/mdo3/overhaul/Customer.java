package com.example.mdo3.overhaul;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by Royal on 4/5/2018.
 */

public class Customer implements Serializable{

    private int id;
    private String userName;
    private String name;
    private String phoneNumber;
    private boolean isActive;
    private Timestamp dateRegistered;


    public Customer(int Id, String UserName, String Name, String PhoneNumber, Timestamp DateRegistered, boolean IsActive)
    {
        this.id = Id;
        this.userName = UserName;
        this.name = Name;
        this.phoneNumber = PhoneNumber;
        this.isActive = IsActive;
        this.dateRegistered = DateRegistered;
    }

    public int getId() {return this.id;}
    public String getUserName() {return this.userName;}
    public Timestamp getDateRegistered() {return this.dateRegistered;}
    public boolean getIsActive() {return this.isActive;}
    public String getPhoneNumber() {return this.phoneNumber;}
    public String getName() {return this.name;}
}
