package com.example.mdo3.overhaul;

/**
 * Created by Royal on 3/27/2018.
 */

public class Driver {

    private int id;
    private int idUser;
    private String driverLicenseNumber;
    private String carMake;
    private String carModel;
    private String licensePlateNumber;
    private float rating;

    public Driver(int Id, int IdUser, String DriverLicenseNumber, String CarMake, String CarModel, String LicensePlateNumber, float Rating)
    {
        this.id = Id;
        this.idUser  = IdUser;
        this.driverLicenseNumber = DriverLicenseNumber;
        this.carMake = CarMake;
        this.carModel = CarModel;
        this.licensePlateNumber = LicensePlateNumber;
        this.rating = Rating;
    }

    public int getId() {return this.id;}
    public int getIdUser() {return this.idUser;}
    public String getDriverLicenseNumber() {return this.driverLicenseNumber;}
    public String getCarMake() {return this.carMake;}
    public String getCarModel() {return this.carModel;}
    public String getLicensePlateNumber() {return this.licensePlateNumber;}
    public float getRating() {return this.rating;}
}
