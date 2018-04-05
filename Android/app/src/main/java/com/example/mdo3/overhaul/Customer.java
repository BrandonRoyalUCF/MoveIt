package com.example.mdo3.overhaul;
import java.sql.Timestamp;

/**
 * Created by Royal on 4/5/2018.
 */

public class Customer {

    private int id;
    private int idUser;
    private boolean isActive;
    private Timestamp dateRegistered;
    private float rating;

    public Customer(int Id, int IdUser, Timestamp DateRegistered, boolean IsActive, float Rating)
    {
        this.id = Id;
        this.idUser = IdUser;
        this.isActive = IsActive;
        this.dateRegistered = DateRegistered;
        this.rating = Rating;
    }

    public int getId() {return this.id;}
    public int getIdUser() {return this.idUser;}
    public Timestamp getDateRegistered() {return this.dateRegistered;}
    public boolean getIsActive() {return this.isActive;}
    public float getRating() {return this.rating;}
}
