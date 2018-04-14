package com.example.mdo3.overhaul;

import java.io.Serializable;

/**
 * Created by Royal on 4/5/2018.
 */

public class Vehicle implements Serializable{
    private int id;
    private int idDriver;
    private String make;
    private String model;
    private int year;
    private String licensePlate;
    private float loadCapacity;
    private byte[] picture;

    public Vehicle(int Id, int IdDriver, String Make, String Model, int Year, String LicensePlate, float LoadCapacity, byte[] Picture)
    {
        this.id = Id;
        this.idDriver = IdDriver;
        this.make = Make;
        this.model = Model;
        this.year = Year;
        this.licensePlate = LicensePlate;
        this.loadCapacity = LoadCapacity;
        this.picture = Picture;
    }

    public int getId() {return this.id;}
    public int getIdDriver() {return this.idDriver;}
    public String getMake() {return this.make;}
    public String getModel() {return this.model;}
    public int getYear() {return this.year;}
    public String getLicensePlate() {return this.licensePlate;}
    private float getLoadCapacity() {return this.loadCapacity;}
    public byte[] getPicture() {return this.picture;}
}
