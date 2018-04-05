package com.example.mdo3.overhaul;

/**
 * Created by Royal on 4/5/2018.
 */

public class Vehicle {
    private int id;
    private int idDriver;
    private String make;
    private String model;
    private String licensePlate;
    private byte[] picture;

    public Vehicle(int Id, int IdDriver, String Make, String Model, String LicensePlate, byte[] Picture)
    {
        this.id = Id;
        this.idDriver = IdDriver;
        this.make = Make;
        this.model = Model;
        this.licensePlate = LicensePlate;
        this.picture = Picture;
    }

    public int getId() {return id;}
    public int getIdDriver() {return idDriver;}
    public String getMake() {return make;}
    public String getModel() {return model;}
    public String getLicensePlate() {return licensePlate;}
    public byte[] getPicture() {return picture;}
}
