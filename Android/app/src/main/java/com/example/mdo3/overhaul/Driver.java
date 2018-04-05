package com.example.mdo3.overhaul;

import java.sql.Timestamp;

/**
 * Created by Royal on 3/27/2018.
 */

public class Driver {

    private int id;
    private int idUser;
    private String driverLicenseNumber;
    private String insuranceNumber;
    private Timestamp dateRegistered;
    private float rating;
    private Vehicle vehicle;

    public Driver(int Id, int IdUser, String DriverLicenseNumber, String InsuranceNumber, Timestamp DateRegistered, float Rating, Vehicle vehicle)
    {
        this.id = Id;
        this.idUser  = IdUser;
        this.driverLicenseNumber = DriverLicenseNumber;
        this.insuranceNumber = InsuranceNumber;
        this.dateRegistered = DateRegistered;
        this.rating = Rating;
        this.vehicle = vehicle;
    }

    public int getId() {return this.id;}
    public int getIdUser() {return this.idUser;}
    public String getDriverLicenseNumber() {return this.driverLicenseNumber;}
    public String getInsuranceNumber() {return this.insuranceNumber;}
    public Timestamp getDateRegistered() {return this.dateRegistered;}
    public float getRating() {return this.rating;}
    public Vehicle getVehicle() {return this.vehicle;}
}
