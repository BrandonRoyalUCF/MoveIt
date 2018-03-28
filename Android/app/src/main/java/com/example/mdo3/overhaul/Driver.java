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

    public Driver(int Id, int IdUser, String DriverLicenseNumber, String CarMake, String CarModel, String LicensePlateNumber)
    {
        this.id = Id;
        this.idUser  = IdUser;
        this.driverLicenseNumber = DriverLicenseNumber;
        this.carMake = CarMake;
        this.carModel = CarModel;
        this.licensePlateNumber = LicensePlateNumber;
    }

    public int getId() {return id;}
    public int getIdUser() {return idUser;}
    public String getDriverLicenseNumber() {return driverLicenseNumber;}
    public String getCarMake() {return carMake;}
    public String getCarModel() {return carModel;}
    public String getLicensePlateNumber() {return licensePlateNumber;}
}
